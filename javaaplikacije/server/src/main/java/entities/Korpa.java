/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private String korisnikId;
    
    private BigDecimal ukupnaCena;
    
    private List<ArtikalKorpa> artikalKorpaList;

    public Korpa() {
    }

    public Korpa(String korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Korpa(String korisnikId, BigDecimal ukupnaCena) {
        this.korisnikId = korisnikId;
        this.ukupnaCena = ukupnaCena;
    }

    public String getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(String korisnikId) {
        this.korisnikId = korisnikId;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public List<ArtikalKorpa> getArtikalKorpaList() {
        return artikalKorpaList;
    }

    public void setArtikalKorpaList(List<ArtikalKorpa> artikalKorpaList) {
        this.artikalKorpaList = artikalKorpaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnikId != null ? korisnikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.korisnikId == null && other.korisnikId != null) || (this.korisnikId != null && !this.korisnikId.equals(other.korisnikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Korpa[ korisnikId=" + korisnikId + " ]";
    }

}
