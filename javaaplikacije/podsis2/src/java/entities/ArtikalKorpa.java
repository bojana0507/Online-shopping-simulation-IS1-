/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "artikal_korpa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtikalKorpa.findAll", query = "SELECT a FROM ArtikalKorpa a"),
    @NamedQuery(name = "ArtikalKorpa.findByKorpaId", query = "SELECT a FROM ArtikalKorpa a WHERE a.artikalKorpaPK.korpaId = :korpaId"),
    @NamedQuery(name = "ArtikalKorpa.findByArtikalId", query = "SELECT a FROM ArtikalKorpa a WHERE a.artikalKorpaPK.artikalId = :artikalId"),
    @NamedQuery(name = "ArtikalKorpa.findByKolicina", query = "SELECT a FROM ArtikalKorpa a WHERE a.kolicina = :kolicina")})
public class ArtikalKorpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtikalKorpaPK artikalKorpaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @JoinColumn(name = "artikal_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikal artikal;
    @JoinColumn(name = "korpa_id", referencedColumnName = "korisnik_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
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
        return "entities.ArtikalKorpa[ artikalKorpaPK=" + artikalKorpaPK + " ]";
    }

}
