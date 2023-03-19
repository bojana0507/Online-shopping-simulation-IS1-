/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;

public class ArtikalKorpaPK implements Serializable {

    private String korpaId;

    private int artikalId;

    public ArtikalKorpaPK() {
    }

    public ArtikalKorpaPK(String korpaId, int artikalId) {
        this.korpaId = korpaId;
        this.artikalId = artikalId;
    }

    public String getKorpaId() {
        return korpaId;
    }

    public void setKorpaId(String korpaId) {
        this.korpaId = korpaId;
    }

    public int getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(int artikalId) {
        this.artikalId = artikalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korpaId != null ? korpaId.hashCode() : 0);
        hash += (int) artikalId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikalKorpaPK)) {
            return false;
        }
        ArtikalKorpaPK other = (ArtikalKorpaPK) object;
        if ((this.korpaId == null && other.korpaId != null) || (this.korpaId != null && !this.korpaId.equals(other.korpaId))) {
            return false;
        }
        if (this.artikalId != other.artikalId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ArtikalKorpaPK[ korpaId=" + korpaId + ", artikalId=" + artikalId + " ]";
    }

}
