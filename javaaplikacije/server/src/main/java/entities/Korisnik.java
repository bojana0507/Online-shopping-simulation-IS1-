 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    private String korisnickoime;
  
    private String sifra;
    
    private String ime;
   
    private String prezime;
    
    private String adresa;

    private BigDecimal novac;
    
    private Grad gradId;

    public Korisnik() {
    }

    public Korisnik(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public Korisnik(String korisnickoime, String sifra, String ime, String prezime, String adresa, BigDecimal novac) {
        this.korisnickoime = korisnickoime;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.novac = novac;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public BigDecimal getNovac() {
        return novac;
    }

    public void setNovac(BigDecimal novac) {
        this.novac = novac;
    }

    public Grad getGradId() {
        return gradId;
    }

    public void setGradId(Grad gradId) {
        this.gradId = gradId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnickoime != null ? korisnickoime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnickoime == null && other.korisnickoime != null) || (this.korisnickoime != null && !this.korisnickoime.equals(other.korisnickoime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Korisnicko ime: ");
        sb.append(korisnickoime);
        sb.append("\nIme: ");
        sb.append(ime);
        sb.append("\nPrezime: ");
        sb.append(prezime);
        sb.append("\nAdresa: ");
        sb.append(adresa);
        sb.append("\nNovac: ");
        sb.append(novac);
        sb.append("\nGrad:\n[");
        sb.append(gradId);
        sb.append("]\n");
        return sb.toString();
    }
    
}
