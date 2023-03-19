/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;

public class ArtikalKorpa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected ArtikalKorpaPK artikalKorpaPK;
    
    private int kolicina;
    
    private Artikal artikal;
    
    private Korpa korpa;

    public ArtikalKorpa() {
    }

    public ArtikalKorpa(ArtikalKorpaPK artikalKorpaPK) {
        this.artikalKorpaPK = artikalKorpaPK;
    }

    public ArtikalKorpa(ArtikalKorpaPK artikalKorpaPK, int kolicina) {
        this.artikalKorpaPK = artikalKorpaPK;
        this.kolicina = kolicina;
    }

    public ArtikalKorpa(String korpaId, int artikalId) {
        this.artikalKorpaPK = new ArtikalKorpaPK(korpaId, artikalId);
    }

    public ArtikalKorpaPK getArtikalKorpaPK() {
        return artikalKorpaPK;
    }

    public void setArtikalKorpaPK(ArtikalKorpaPK artikalKorpaPK) {
        this.artikalKorpaPK = artikalKorpaPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikalKorpaPK != null ? artikalKorpaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikalKorpa)) {
            return false;
        }
        ArtikalKorpa other = (ArtikalKorpa) object;
        if ((this.artikalKorpaPK == null && other.artikalKorpaPK != null) || (this.artikalKorpaPK != null && !this.artikalKorpaPK.equals(other.artikalKorpaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\nNaziv artikla: ");
        if (this.artikal != null) sb.append(this.artikal.getNaziv());
        sb.append("\nKolicina: ");
        sb.append(this.kolicina);
        sb.append("]\n");
        return sb.toString();
    }

}
