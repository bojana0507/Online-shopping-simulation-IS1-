/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitites;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "stavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavka.findAll", query = "SELECT s FROM Stavka s"),
    @NamedQuery(name = "Stavka.findById", query = "SELECT s FROM Stavka s WHERE s.id = :id"),
    @NamedQuery(name = "Stavka.findByArtikalId", query = "SELECT s FROM Stavka s WHERE s.artikalId = :artikalId"),
    @NamedQuery(name = "Stavka.findByKolicinaArt", query = "SELECT s FROM Stavka s WHERE s.kolicinaArt = :kolicinaArt"),
    @NamedQuery(name = "Stavka.findByCenaArtikla", query = "SELECT s FROM Stavka s WHERE s.cenaArtikla = :cenaArtikla")})
public class Stavka implements Serializable {

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
    @Column(name = "kolicina_art")
    private int kolicinaArt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena_artikla")
    private BigDecimal cenaArtikla;
    @JoinColumn(name = "narudzbina_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Narudzbina narudzbinaId;

    public Stavka() {
    }

    public Stavka(Integer id) {
        this.id = id;
    }

    public Stavka(Integer id, int artikalId, int kolicinaArt, BigDecimal cenaArtikla) {
        this.id = id;
        this.artikalId = artikalId;
        this.kolicinaArt = kolicinaArt;
        this.cenaArtikla = cenaArtikla;
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

    public int getKolicinaArt() {
        return kolicinaArt;
    }

    public void setKolicinaArt(int kolicinaArt) {
        this.kolicinaArt = kolicinaArt;
    }

    public BigDecimal getCenaArtikla() {
        return cenaArtikla;
    }

    public void setCenaArtikla(BigDecimal cenaArtikla) {
        this.cenaArtikla = cenaArtikla;
    }

    public Narudzbina getNarudzbinaId() {
        return narudzbinaId;
    }

    public void setNarudzbinaId(Narudzbina narudzbinaId) {
        this.narudzbinaId = narudzbinaId;
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
        if (!(object instanceof Stavka)) {
            return false;
        }
        Stavka other = (Stavka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitites.Stavka[ id=" + id + " ]";
    }

}
