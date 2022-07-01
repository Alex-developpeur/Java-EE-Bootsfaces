package dev.fatum.www.model;

import java.util.ArrayList;

import javax.persistence.Entity;

/**
 * @author LEONARD
 */
@Entity
public class Utilisateur extends Profil {

	private static final long serialVersionUID = 3734409829741348491L;

    public Utilisateur() {
        this.groupesList = new ArrayList<Groupes>();
    }
    
    public Utilisateur(Integer id) {
        this.id = id;
        this.groupesList = new ArrayList<Groupes>();
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Utilisateur[ id=" + id + " ]";
    }
}
