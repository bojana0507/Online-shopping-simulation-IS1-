/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsis3;

import entitites.Narudzbina;
import entitites.Stavka;
import entitites.Transakcija;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import pomocna.PlatiPomocna;

public class Podsis3Main {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "serverqueue")
    private static Queue queue0;
    
    @Resource(lookup = "podsis3queue")
    private static Queue queue;

   public static void main(String[] args) {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("podsis3PU");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
    
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(queue);
        JMSProducer producer = context.createProducer();
        System.out.println("Zapoceo rad.");
        try {
            while (true) {
                Message message = consumer.receive();
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    String header = objectMessage.getStringProperty("header");
                    ObjectMessage response = context.createObjectMessage();
                    switch (header) {
                        case "11":
                            if (placanje((PlatiPomocna)objectMessage.getObject(), em, transaction))
                                response.setObject("Uspesno placanje.");
                            else
                                response.setObject("Neuspesno placanje.");
                            break;
                        case "17":
                            List<Narudzbina> nar = dohvNarudzbine((String)objectMessage.getObject(),em);
                            response.setObject((Serializable) nar);
                            break;
                        case "18":
                            List<Narudzbina> narSve = dohvNarudzbineSVe(em);
                            response.setObject((Serializable) narSve);
                            break;
                        case "19":
                            List<Transakcija> tr = dohvTrans(em);
                            response.setObject((Serializable) tr);
                            break;
                        default:
                            System.out.println("Unknown header: " + header);
                    }
                    producer.send(queue0, response);
                    System.out.println("Poslata potvrda.");
                } else {
                    System.out.println("Received message is not an ObjectMessage");
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) consumer.close();
            em.close();
            entityManagerFactory.close();
        }
   }

    private static boolean placanje(PlatiPomocna platiPomocna, EntityManager em, EntityTransaction transaction) {
        
        try {
            transaction.begin();

            Narudzbina nar = new Narudzbina();
            nar.setAdresa(platiPomocna.adresa);
            nar.setGrad(platiPomocna.grad);
            nar.setVremeKreiranja(Timestamp.from(Instant.now()));
            em.persist(nar);
            em.flush();
            BigDecimal c = new BigDecimal(BigInteger.ZERO);
            for (int i = 0; i < platiPomocna.idArt.size(); i++){
                Stavka nova = new Stavka();
                nova.setArtikalId(platiPomocna.idArt.get(i));
                nova.setCenaArtikla(platiPomocna.cena.get(i));
                nova.setKolicinaArt(platiPomocna.kolicina.get(i));
                c = c.add(platiPomocna.cena.get(i).multiply(new BigDecimal(platiPomocna.kolicina.get(i))));
                nova.setNarudzbinaId(nar);
                em.persist(nova);
            }
            nar.setUkupnaCena(c);
            em.persist(nar);
            em.flush();
            Transakcija t = new Transakcija();
            t.setNarudzbinaId(nar);
            t.setPlacenaSuma(c);
            t.setVremePlacanja(Timestamp.from(Instant.now()));
            em.persist(t);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        
    }

    private static List<Narudzbina> dohvNarudzbine(String string, EntityManager em) {
        return em.createNamedQuery("Narudzbina.findAll").getResultList();
    }

    private static List<Narudzbina> dohvNarudzbineSVe(EntityManager em) {
        return em.createNamedQuery("Narudzbina.findAll").getResultList();    }

    private static List<Transakcija> dohvTrans(EntityManager em) {
        return em.createNamedQuery("Transakcija.findAll").getResultList();
    }
}
