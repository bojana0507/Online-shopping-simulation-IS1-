package main;

import com.google.gson.Gson;
import entities.Artikal;
import entities.ArtikalKorpa;
import entities.ArtikalKorpaPK;
import entities.Grad;
import entities.Kategorija;
import entities.Korisnik;
import entitites.Narudzbina;
import entitites.Transakcija;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.QueryHint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import pomocna.PlatiPomocna;

@Path("upit")
public class Upiti {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;
        
    @Resource(lookup = "serverqueue")
    private Queue queue0;
    
    @Resource(lookup = "podsis1queue")
    private Queue queue1;
    
    @Resource(lookup = "podsis2queue")
    private Queue queue2;
    
    @Resource(lookup = "podsis3queue")
    private Queue queue3;

    @GET
    @Path("1")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit1(
        @QueryParam("naziv") String naziv,
        @QueryParam("drzava") String drzava,
        @QueryParam("pb") String pb){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        Grad g = new Grad();
        g.setDrzava(drzava);
        g.setNaziv(naziv);
        g.setPostanskiBroj(pb);
        System.out.println("Zahtev spreman: " + g.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(g);
            req.setStringProperty("header", "1");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("2")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit2(
        @QueryParam("korisnickoime") String korime,
        @QueryParam("sifra") String sifra,
        @QueryParam("ime") String ime,
        @QueryParam("prezime") String prezime,
        @QueryParam("adresa") String adresa,
        @QueryParam("novac") BigDecimal novac,
        @QueryParam("gradID") Integer gradID){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        Korisnik k = new Korisnik();
        k.setKorisnickoime(korime);
        k.setSifra(sifra);
        k.setAdresa(adresa);
        k.setIme(ime);
        k.setPrezime(prezime);
        k.setGradId(findGradByID(gradID, context, producer, consumer));
        k.setNovac(novac);
        System.out.println("Zahtev spreman: " + k.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(k);
            req.setStringProperty("header", "2");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("3")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit3(
        @QueryParam("korisnickoime") String korime,
        @QueryParam("novac") BigDecimal novac){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        Korisnik k = new Korisnik();
        k.setKorisnickoime(korime);
        k.setNovac(novac);
        System.out.println("Zahtev spreman: " + k.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(k);
            req.setStringProperty("header", "3");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("4")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit4(
        @QueryParam("korisnickoime") String korime,
        @QueryParam("adresa") String adresa,
        @QueryParam("gradID") Integer grad){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        Korisnik k = new Korisnik();
        k.setKorisnickoime(korime);
        k.setAdresa(adresa);
        Grad g = new Grad();
        g.setId(grad);
        k.setGradId(g);
        System.out.println("Zahtev spreman: " + k.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(k);
            req.setStringProperty("header", "4");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("12")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit12(){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setStringProperty("header", "12");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska.";
        try {
            List<Grad> gradovi = (List<Grad>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Grad grad : gradovi) {
                sb.append(grad);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("13")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit13(){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setStringProperty("header", "13");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Korisnik> korisnici = (List<Korisnik>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Korisnik korisnik : korisnici) {
                sb.append(korisnik);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("5")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit5(
        @QueryParam("naziv") String naziv,
        @QueryParam("nadkategorijaId") int nadkatId){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        if (nadkatId != 0){
            Kategorija nadkat = new Kategorija();
            nadkat.setId(nadkatId);
            k.setNadkategorijaId(nadkat);
        }
        else {
            k.setNadkategorijaId(null);
        }
        
        System.out.println("Zahtev spreman: " + k.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(k);
            req.setStringProperty("header", "5");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("6")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit6(
        @QueryParam("naziv") String naziv,
        @QueryParam("opis") String opis,
        @QueryParam("cena") BigDecimal cena,
        @QueryParam("popust") int popust,
        @QueryParam("kategorijaID") int kategorijaID,
        @QueryParam("prodavacID") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }
            
        
        Artikal a = new Artikal();
        a.setNaziv(naziv);
        a.setOpis(opis);
        a.setCena(cena);
        Kategorija kat = new Kategorija(kategorijaID);
        a.setKategorija(kat);
        a.setPopust(popust);
        a.setProdavacId(korime);
        
        System.out.println("Zahtev spreman: " + a.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(a);
            req.setStringProperty("header", "6");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("7")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit7(
        @QueryParam("artikalID") int idArt,
        @QueryParam("novaCena") BigDecimal novaCena,
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }

        Artikal a = new Artikal();
        a.setId(idArt);
        a.setCena(novaCena);
        a.setNaziv(korime);
        
        System.out.println("Zahtev spreman: " + a.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(a);
            req.setStringProperty("header", "7");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("8")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit8(
        @QueryParam("artikalID") int idArt,
        @QueryParam("popust") int procenat,
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }

        Artikal a = new Artikal();
        a.setId(idArt);
        a.setPopust(procenat);
        a.setNaziv(korime);
        
        System.out.println("Zahtev spreman: " + a.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(a);
            req.setStringProperty("header", "8");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("9")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit9(
        @QueryParam("artikalID") int idArt,
        @QueryParam("kolicina") int kolicina,
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }

        ArtikalKorpa ak = new ArtikalKorpa();
        ak.setArtikalKorpaPK(new ArtikalKorpaPK(korime, idArt));
        ak.setKolicina(kolicina);
        
        System.out.println("Zahtev spreman: " + ak.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(ak);
            req.setStringProperty("header", "9");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("10")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit10(
        @QueryParam("artikalID") int idArt,
        @QueryParam("kolicina") int kolicina,
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }

        ArtikalKorpa ak = new ArtikalKorpa();
        ak.setArtikalKorpaPK(new ArtikalKorpaPK(korime, idArt));
        ak.setKolicina(kolicina);
        
        System.out.println("Zahtev spreman: " + ak.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(ak);
            req.setStringProperty("header", "10");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("11")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit11(
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }

        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(korime);
            req.setStringProperty("header", "16");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
        Message resp = consumer.receive();
        List<ArtikalKorpa> la = null;
        try {
            la = (List<ArtikalKorpa>)((ObjectMessage)resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Zahtev spreman.");
        req = context.createObjectMessage();
        try {
            req.setObject(korime);
            req.setStringProperty("header", "11.2");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
        resp = consumer.receive();
        Korisnik k = null;
        try {
            k = (Korisnik)((ObjectMessage)resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PlatiPomocna pomocna = new PlatiPomocna();
        pomocna.idArt = new ArrayList<>();
        pomocna.kolicina = new ArrayList<>();
        pomocna.cena = new ArrayList<>();
        for (ArtikalKorpa ak : la) {
            pomocna.idArt.add(ak.getArtikalKorpaPK().getArtikalId());
            pomocna.kolicina.add(ak.getKolicina());
            pomocna.cena.add(ak.getArtikal().getCena());
        }
        pomocna.adresa = k.getAdresa();
        pomocna.grad = k.getGradId().getId();
        
        req = context.createObjectMessage();
        try {
            req.setObject(pomocna);
            req.setStringProperty("header", "11");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue3, req);
        System.out.println("Poslat zahtev.");
 
        resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("14")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit14(){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setStringProperty("header", "14");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Kategorija> kategorije = (List<Kategorija>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Kategorija k : kategorije) {
                sb.append(k);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("15")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit15(
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }
        
        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(korime);
            req.setStringProperty("header", "15");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Artikal> art = (List<Artikal>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Artikal a : art) {
                sb.append(a);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("16")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit16(
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }
        
        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(korime);
            req.setStringProperty("header", "16");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue2, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            System.out.println("\nmain.Upiti.upit16(): " + ((ObjectMessage) resp).getObject());
            List<ArtikalKorpa> art = (List<ArtikalKorpa>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (ArtikalKorpa a : art) {
                sb.append(a);
                sb.append("\n");
            }
            if (art.size()>0) {
                sb.append("\nUkupna cena korpe: ");
                sb.append(art.get(0).getKorpa().getUkupnaCena());
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    
    @GET
    @Path("17")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit17(
        @QueryParam("korime") String korime,
        @QueryParam("sifra") String sifra){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);

        if (!proveriPostojanjeKorisnika(context, consumer, producer, korime, sifra)){
            String json = new Gson().toJson("Ne postoji korisnik");
            consumer.close();
            context.close();
            return json;
        }
        
        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(korime);
            req.setStringProperty("header", "17");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue3, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Narudzbina> art = (List<Narudzbina>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Narudzbina n : art) {
                sb.append(n);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    @GET
    @Path("18")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit18(){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);
        
        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setStringProperty("header", "18");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue3, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Narudzbina> art = (List<Narudzbina>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Narudzbina n : art) {
                sb.append(n);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    @GET
    @Path("19")
    @Produces(MediaType.APPLICATION_JSON)
    public String upit19(){
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue0);
        
        System.out.println("Zahtev spreman.");
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setStringProperty("header", "19");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue3, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            List<Transakcija> art = (List<Transakcija>) ((ObjectMessage) resp).getObject();
            StringBuilder sb = new StringBuilder();
            for (Transakcija n : art) {
                sb.append(n);
                sb.append("\n");
            }
            response = sb.toString();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        String json = new Gson().toJson(response);
        consumer.close();
        context.close();
        return json;
    }
    

    private Grad findGradByID(int id, JMSContext context, JMSProducer producer, JMSConsumer consumer) {
        System.out.println("Zahtev spreman: " + id);
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(id);
            req.setStringProperty("header", "2.1");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        Grad response = null;
        try {
            System.out.println(((ObjectMessage) resp).getObject());
            response = (Grad)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        return response;
    }

    private boolean proveriPostojanjeKorisnika(JMSContext context, JMSConsumer consumer, JMSProducer producer, String korime, String sifra) {
        Korisnik k = new Korisnik();
        k.setKorisnickoime(korime);
        k.setSifra(sifra);
        
        System.out.println("Zahtev spreman: " + k.toString());
        ObjectMessage req = context.createObjectMessage();
        try {
            req.setObject(k);
            req.setStringProperty("header", "6.1");
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        producer.send(queue1, req);
        System.out.println("Poslat zahtev.");
 
        Message resp = consumer.receive();
        String response = "Greska";
        try {
            response = (String)((ObjectMessage) resp).getObject();
        } catch (JMSException ex) {
            Logger.getLogger(Upiti.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if (response.equals("ok")) return true;
        else return false;
    }
    
}