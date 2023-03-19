/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "recenzija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recenzija.findAll", query = "SELECT r FROM Recenzija r"),
    @NamedQuery(name = "Recenzija.findById", query = "SELECT r FROM Recenzija r WHERE r.id = :id"),
    @NamedQuery(name = "Recenzija.findByArtikalId", query = "SELECT r FROM Recenzija r WHERE r.artikalId = :artikalId"),
    @NamedQuery(name = "Recenzija.findByOcena", query = "SELECT r FROM Recenzija r WHERE r.ocena = :ocena"),
    @NamedQuery(name = "Recenzija.findByOpis", query = "SELECT r FROM Recenzija r WHERE r.opis = :opis")})
public class Recenzija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "artikal_id")
    private int artikalId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocena")
    private int ocena;
    @Size(max = 255)
    @Column(name = "opis")
    private String opis;

    public Recenzija() {
    }

    public Recenzija(Integer id) {
        this.id = id;
    }

    public Recenzija(Integer id, int artikalId, int ocena) {
        this.id = id;
        this.artikalId = artikalId;
        this.ocena = ocena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(int artikalId) {
        this.artikalId = artikalId;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
        if (!(object instanceof Recenzija)) {
            return false;
        }
        Recenzija other = (Recenzija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitites.Recenzija[ id=" + id + " ]";
    }

}
