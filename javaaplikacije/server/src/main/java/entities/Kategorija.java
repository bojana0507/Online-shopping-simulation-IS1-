/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;

public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
   
    private String naziv;
    
    private List<Artikal> artikalList;

    private List<Kategorija> kategorijaList;

    private Kategorija nadkategorijaId;

    public Kategorija() {
    }

    public Kategorija(Integer id) {
        this.id = id;
    }

    public Kategorija(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    public List<Artikal> getArtikalList() {
        return artikalList;
    }

    public void setArtikalList(List<Artikal> artikalList) {
        this.artikalList = artikalList;
    }

    public List<Kategorija> getKategorijaList() {
        return kategorijaList;
    }

    public void setKategorijaList(List<Kategorija> kategorijaList) {
        this.kategorijaList = kategorijaList;
    }

    public Kategorija getNadkategorijaId() {
        return nadkategorijaId;
    }

    public void setNadkategorijaId(Kategorija nadkategorijaId) {
        this.nadkategorijaId = nadkategorijaId;
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
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID kategorije: ");
        sb.append(this.id);
        sb.append("\nNaziv: ");
        sb.append(this.naziv);
        sb.append("\nNadkategorija: [");
        sb.append(this.nadkategorijaId);
        sb.append("]\n");
        return sb.toString();
    }

}
