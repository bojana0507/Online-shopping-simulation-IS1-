/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsis2;

import entities.Artikal;
import entities.ArtikalKorpa;
import entities.Kategorija;
import entities.Korpa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

/**
 *
 * @author bojan
 */
public class Podsis2Main {
        
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "serverqueue")
    private static Queue queue0;

    @Resource(lookup = "podsis2queue")
    private static Queue queue;

   public static void main(String[] args) {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("podsis2PU");
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
                        case "5":
                            if (kreirajKategoriju((Kategorija)objectMessage.getObject(), em, transaction))
                                response.setObject("Kreirana kategorija.");
                            else
                                response.setObject("Neuspesno kreiranje.");
                            break;
                        case "6":
                            if (kreirajArtikal((Artikal)objectMessage.getObject(), em, transaction))
                                response.setObject("Kreiran artikal.");
                            else
                                response.setObject("Neuspesno kreiranje.");
                            break;
                        case "7":
                            if (menjajCenu((Artikal)objectMessage.getObject(), em, transaction))
                                response.setObject("Promenjena cena artikla.");
                            else
                                response.setObject("Neuspesna promena cene.");
                            break;
                        case "8":
                            if (postaviPopust((Artikal)objectMessage.getObject(), em, transaction))
                                response.setObject("Postavljen popust na artikal.");
                            else
                                response.setObject("Neuspesno postavljanje popusta.");
                            break;
                        case "9":
                            if (dodajArtikalUKorpu((ArtikalKorpa)objectMessage.getObject(), em, transaction))
                                response.setObject("Artikal u zeljenoj kolicini dodat u korpu.");
                            else
                                response.setObject("Neuspesno dodavanje u korpu.");
                            break;
                        case "10":
                            if (brisiArtikalIzKorpe((ArtikalKorpa)objectMessage.getObject(), em, transaction))
                                response.setObject("Artikal u zeljenoj kolicini obrisan iz korpe.");
                            else
                                response.setObject("Neuspesno postavljanje popusta.");
                            break;
                        case "14":
                            List<Kategorija> kategorije = dohvKategorije(em);
                            response.setObject((Serializable) kategorije);
                            break;
                        case "15":
                            String username = (String)objectMessage.getObject();
                            List<Artikal> artikli = dohvArtikleProdavca(username, em);
                            response.setObject((Serializable) artikli);
                            break;
                        case "16":
                            username = (String)objectMessage.getObject();
                            List<ArtikalKorpa> sadrzajKorpe = dohvArtikleIzKorpe(username, em);
                            response.setObject((Serializable) sadrzajKorpe);
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

    private static boolean kreirajKategoriju(Kategorija kategorija, EntityManager em, EntityTransaction transaction) {
       try {
            transaction.begin();

            Kategorija k = new Kategorija();
            k.setNaziv(kategorija.getNaziv());
            Kategorija nadkat = null;
            if (kategorija.getNadkategorijaId() != null) {
                nadkat = em.find(Kategorija.class, kategorija.getNadkategorijaId().getId());
            }
            k.setNadkategorijaId(nadkat);
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

    private static boolean kreirajArtikal(Artikal a, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();
            
            Artikal artikal = new Artikal();
            artikal.setNaziv(a.getNaziv());
            Kategorija kat = em.find(Kategorija.class, a.getKategorija().getId());
            artikal.setKategorija(kat);
            artikal.setCena(a.getCena());
            artikal.setOpis(a.getOpis());
            artikal.setPopust(a.getPopust());
            artikal.setProdavacId(a.getProdavacId());
            em.persist(artikal);
            
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

    private static boolean menjajCenu(Artikal artikal, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            Artikal a = em.find(Artikal.class, artikal.getId());
            
            if (!artikal.getNaziv().equals(a.getProdavacId())) {
                throw new Exception("Nije prodavac.");
            }
            
            a.setCena(artikal.getCena());
            
            em.persist(a);
            
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

    private static boolean postaviPopust(Artikal artikal, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            Artikal a = em.find(Artikal.class, artikal.getId());
            if (!artikal.getNaziv().equals(a.getProdavacId())) {
                throw new Exception("Nije prodavac.");
            }

            a.setPopust(artikal.getPopust());
            em.persist(a);
            
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

    private static boolean dodajArtikalUKorpu(ArtikalKorpa artikalKorpa, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            BigDecimal uCena = new BigDecimal(0);
            BigDecimal cenaArt = em.find(Artikal.class, artikalKorpa.getArtikalKorpaPK().getArtikalId()).getCena();
            BigDecimal popust = new BigDecimal(em.find(Artikal.class, artikalKorpa.getArtikalKorpaPK().getArtikalId()).getPopust());
            popust = popust.multiply(new BigDecimal(0.01));
            popust = new BigDecimal(BigInteger.ONE).subtract(popust);
            cenaArt = cenaArt.multiply(popust);
            uCena = uCena.add(cenaArt.multiply(new BigDecimal(artikalKorpa.getKolicina())));
            
            ArtikalKorpa artkor = em.find(ArtikalKorpa.class, artikalKorpa.getArtikalKorpaPK());
            Korpa korpa = em.find(Korpa.class, artikalKorpa.getArtikalKorpaPK().getKorpaId());

            if (artkor != null)
                artkor.setKolicina(artkor.getKolicina() + artikalKorpa.getKolicina());
            else {
                if (korpa == null)
                    korpa = new Korpa(artikalKorpa.getArtikalKorpaPK().getKorpaId(), uCena);
                artkor = new ArtikalKorpa(artikalKorpa.getArtikalKorpaPK(), artikalKorpa.getKolicina());
            }
            korpa.setUkupnaCena(uCena);
            
            em.persist(korpa);
            em.persist(artkor);
            
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

    private static boolean brisiArtikalIzKorpe(ArtikalKorpa artikalKorpa, EntityManager em, EntityTransaction transaction) {
        try {
            transaction.begin();

            ArtikalKorpa artkor = em.find(ArtikalKorpa.class, artikalKorpa.getArtikalKorpaPK());
            if (artkor == null)
                return false;
            if (artkor.getKolicina() - artikalKorpa.getKolicina() < 0)
                return false;
            
            Korpa korpa = em.find(Korpa.class, artikalKorpa.getArtikalKorpaPK().getKorpaId());
            artkor.setKolicina(artkor.getKolicina() - artikalKorpa.getKolicina());
            
            BigDecimal uCena = korpa.getUkupnaCena();
            BigDecimal cenaArt = em.find(Artikal.class, artikalKorpa.getArtikalKorpaPK().getArtikalId()).getCena();
            BigDecimal popust = new BigDecimal(em.find(Artikal.class, artikalKorpa.getArtikalKorpaPK().getArtikalId()).getPopust());
            popust = popust.multiply(new BigDecimal(0.01));
            popust = new BigDecimal(BigInteger.ONE).subtract(popust);
            cenaArt = cenaArt.multiply(popust);
            uCena = uCena.subtract(cenaArt.multiply(new BigDecimal(artikalKorpa.getKolicina())));

            korpa.setUkupnaCena(uCena);
            
            em.persist(korpa);
            em.persist(artkor);
            
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

    private static List<Kategorija> dohvKategorije(EntityManager em) {
        return em.createNamedQuery("Kategorija.findAll").getResultList();
    }

    private static List<Artikal> dohvArtikleProdavca(String username, EntityManager em) {
        return em.createNamedQuery("Artikal.findByProdavacId").setParameter("prodavacId", username).getResultList();
    }

    private static List<ArtikalKorpa> dohvArtikleIzKorpe(String username, EntityManager em) {
        return em.createNamedQuery("ArtikalKorpa.findByKorpaId").setParameter("korpaId", username).getResultList();
    }
    
}
