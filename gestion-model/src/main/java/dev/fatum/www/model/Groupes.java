package dev.fatum.www.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author LEONARD
 */
@Entity
@Table(name = "GROUPES")
@NamedQueries({
    @NamedQuery(name = "Groupes.findAll", query = "SELECT g FROM Groupes g"),
    @NamedQuery(name = "Groupes.findById", query = "SELECT g FROM Groupes g WHERE g.id = :id"),
    @NamedQuery(name = "Groupes.findByNom", query = "SELECT g FROM Groupes g WHERE g.nom = :nom"),
    @NamedQuery(name = "Groupes.findByDescription", query = "SELECT g FROM Groupes g WHERE g.description = :description"),
    @NamedQuery(name = "Groupes.nomDuplicated", query = "SELECT g FROM Groupes g WHERE g.nom = :nom AND g.id != :id")})
public class Groupes implements Serializable {
    
    private static final long serialVersionUID = 1205082528194257031L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message="{groupes.nom}")
    @Column(name = "NOM")
    private String nom;
    @Size(max = 300, message="{groupes.description}")
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy = "groupesList")
    @XmlTransient
    private List<Profil> profilList;

    public Groupes() {
    }

    public Groupes(Integer id) {
        this.id = id;
    }

    public Groupes(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    public List<Profil> getProfilList() {
        return profilList;
    }

    public void setProfilList(List<Profil> profilList) {
        this.profilList = profilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Groupes)) {
            return false;
        }
        Groupes other = (Groupes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Groupes[ id=" + id + " ]";
    }
    
}
