package dev.fatum.www.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author LEONARD
 */
@Entity
@Table(name = "PROFIL")
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p"),
    @NamedQuery(name = "Profil.findById", query = "SELECT p FROM Profil p WHERE p.id = :id"),
    @NamedQuery(name = "Profil.findByNom", query = "SELECT p FROM Profil p WHERE p.nom = :nom"),
    @NamedQuery(name = "Profil.findByEmail", query = "SELECT p FROM Profil p WHERE p.email = :email"),
    @NamedQuery(name = "Profil.emailDuplicated", query = "SELECT p FROM Profil p WHERE p.email = :email AND p.id != :id")})
public class Profil implements Serializable {

	private static final long serialVersionUID = 3734409829741348491L;

    @JoinTable(name = "PROFIL_GROUPES", joinColumns = {
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "NOM", referencedColumnName = "NOM")})
    @ManyToMany
    protected List<Groupes> groupesList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = true)
    @Size(min = 3, max = 50, message = "{profil.nom}")
    @Column(name = "NOM")
    protected String nom;
    @Pattern(regexp = "^\\S+@[\\w-.]+\\.\\w+$", message = "{profil.email}")
    @Size(min = 6, max = 50, message = "{profil.longueuEmail}")
    @Basic(optional = false)
    @Column(name = "EMAIL")
    protected String email;
    @Basic(optional = false)
    @Size(min = 6, max = 100, message = "{profil.mdp}")
    @Column(name = "MDP")
    protected String mdp;

	@Basic(optional = true)
    @Column(name = "EXPIRATION")
    @Temporal(TemporalType.DATE)
    protected Date expiration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profil")
    private List<Entreprise> entrepriseList;
    
    public Profil() {
        this.groupesList = new ArrayList<Groupes>();
    }
    
    public Profil(Integer id) {
        this.id = id;
        this.groupesList = new ArrayList<Groupes>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
    public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public List<Groupes> getGroupesList() {
		return groupesList;
	}

	public void setGroupesList(List<Groupes> groupesList) {
		this.groupesList = groupesList;
	}

	public List<Entreprise> getEntrepriseList() {
		return entrepriseList;
	}

	public void setEntrepriseList(List<Entreprise> entrepriseList) {
		this.entrepriseList = entrepriseList;
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
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Profil[ id=" + id + " ]";
    }
}
