package podsis1;

import entities.Grad;
import entities.Korisnik;
import java.io.Serializable;
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

public class Podsis1Main {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "serverqueue")
    private static Queue queue0;
    
    @Resource(lookup = "podsis1queue")
    private static Queue queue;

   public static void main(String[] args) {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("podsis1PU");
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
                        case "1":
                            if (kreirajGrad((Grad)objectMessage.getObject(), em, transaction))
                                response.setObject("Kreiran grad.");
                            else
                                response.setObject("Neuspesno kreiranje.");
                            break;
                        case "2":
                            if (kreirajKorisnika((Korisnik)objectMessage.getObject(), em, transaction))
                                response.setObject("Kreiran korisnik.");
                            else
                                response.setObject("Neuspesno kreiranje.");
                            break;
                        case "2.1":
                            Grad g = findGrad((Integer)objectMessage.getObject(), em);
                            response.setObject(g);
                            break;
                        case "3":
                            if (dodajNovac((Korisnik)objectMessage.getObject(), em, transaction))
                                response.setObject("Dodat novac korisniku.");
                            else
                                response.setObject("Neuspesno dodavanje novca.");
                            break;
                        case "4":
                            if (promeniAdrGrad((Korisnik)objectMessage.getObject(), em, transaction))
                                response.setObject("Promenjena adresa i grad.");
                            else
                                response.setObject("Neuspesna promena adrese.");
                            break;
                        case "6.1":
                            if (proveriKorisnika((Korisnik)objectMessage.getObject(), em))
                                response.setObject("ok");
                            else
                                response.setObject("Ne postoji korisnik.");
                            break;
                        case "11.2":
                            Korisnik k = dohvKorisnika((String)objectMessage.getObject(),em);
                            response.setObject((Serializable) k);
                            break;
                        case "12":
                            List<Grad> gradovi = dohvGradove(em);
                            response.setObject((Serializable) gradovi);
                            break;
                        case "13":
                            List<Korisnik> korisnici = dohvKorisnike(em);
                            response.setObject((Serializable) korisnici);
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
    
   public static boolean kreirajGrad(Grad grad, EntityManager em, EntityTransaction transaction){
       try {
            transaction.begin();

            Grad g = new Grad();
            g.setDrzava(grad.getDrzava());
            g.setNaziv(grad.getNaziv());
            g.setPostanskiBroj(grad.getPostanskiBroj());
            em.persist(g);
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

    private static boolean kreirajKorisnika(Korisnik k, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            Korisnik korisnik = new Korisnik(
                    k.getKorisnickoime(), 
                    k.getSifra(), 
                    k.getIme(), 
                    k.getPrezime(), 
                    k.getAdresa(), 
                    k.getNovac());
            korisnik.setGradId(k.getGradId());
            em.persist(korisnik);
            
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

    private static Grad findGrad(Integer integer, EntityManager em) {
        return em.find(Grad.class, integer);
    }

    private static boolean dodajNovac(Korisnik korisnik, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            Korisnik k = em.find(Korisnik.class, korisnik.getKorisnickoime());
            k.setNovac(k.getNovac().add(korisnik.getNovac()));
            em.persist(k);
            
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

    private static boolean promeniAdrGrad(Korisnik korisnik, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            Korisnik k = em.find(Korisnik.class, korisnik.getKorisnickoime());
            k.setAdresa(korisnik.getAdresa());
            Grad g = em.find(Grad.class, korisnik.getGradId().getId());
            k.setGradId(g);
            em.persist(k);
            
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

    private static List<Grad> dohvGradove(EntityManager em) {
        return em.createNamedQuery("Grad.findAll").getResultList();
    }

    private static List<Korisnik> dohvKorisnike(EntityManager em) {
        return em.createNamedQuery("Korisnik.findAll").getResultList();
    }

    private static boolean proveriKorisnika(Korisnik korisnik, EntityManager em) {
        Korisnik k = em.find(Korisnik.class, korisnik.getKorisnickoime());
        if (k == null) return false;
        return k.getSifra().equals(korisnik.getSifra());
    }

    private static Korisnik dohvKorisnika(String korisnik, EntityManager em) {
        return em.find(Korisnik.class, korisnik);
    }
}
