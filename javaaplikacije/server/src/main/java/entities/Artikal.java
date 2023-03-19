/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String naziv;
    
    private String opis;
    
    private BigDecimal cena;
    
    private int popust;
    
    private String prodavacId;
    
    private List<ArtikalKorpa> artikalKorpaList;
    
    private Kategorija kategorija;

    public Artikal() {
    }

    public Artikal(Integer id) {
        this.id = id;
    }

    public Artikal(Integer id, String naziv, String opis, BigDecimal cena, int popust, String prodavacId) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.popust = popust;
        this.prodavacId = prodavacId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public String getProdavacId() {
        return prodavacId;
    }

    public void setProdavacId(String prodavacId) {
        this.prodavacId = prodavacId;
    }

    public List<ArtikalKorpa> getArtikalKorpaList() {
        return artikalKorpaList;
    }

    public void setArtikalKorpaList(List<ArtikalKorpa> artikalKorpaList) {
        this.artikalKorpaList = artikalKorpaList;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID artikla: ");
        sb.append(this.id);
        sb.append("\nNaziv: ");
        sb.append(this.naziv);
        sb.append("\nCena: ");
        sb.append(this.cena);
        sb.append("\nPopust: ");
        sb.append(this.popust);
        sb.append("\nKategorija: ");
        sb.append(this.kategorija.getNaziv());
        sb.append("\nOpis: ");
        sb.append(this.opis);
        sb.append("\nProdavac: ");
        sb.append(this.prodavacId);
        sb.append("\n");
        return sb.toString();
    }

}
