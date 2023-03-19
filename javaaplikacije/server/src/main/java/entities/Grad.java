package entities;

import java.io.Serializable;
import java.util.List;

public class Grad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String naziv;

    private String drzava;

    private String postanskiBroj;

    private List<Korisnik> korisnikList;

    public Grad() {
    }

    public Grad(Integer id) {
        this.id = id;
    }

    public Grad(Integer id, String naziv, String drzava, String postanskiBroj) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.postanskiBroj = postanskiBroj;
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

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
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
        if (!(object instanceof Grad)) {
            return false;
        }
        Grad other = (Grad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID grada: ");
        sb.append(id);
        sb.append("\nNaziv grada: ");
        sb.append(naziv);
        sb.append("\nDrzava grada: ");
        sb.append(drzava);
        sb.append("\nPostanski broj grada: ");
        sb.append(postanskiBroj);
        sb.append("\n");
        return sb.toString();
    }
    
}
