package dev.fatum.www.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author LEONARD
 */
@Entity
@Table(name = "PERSONNE")
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findById", query = "SELECT p FROM Personne p WHERE p.id = :id"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByEntrepriseId", query = "SELECT p FROM Personne p WHERE p.entreprise.id = :id")})
public class Personne implements Serializable {
    
	private static final long serialVersionUID = 895180102852661783L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Column(name = "CIVILITE")
    protected String civilite;
    @Basic(optional=false)
    @Size(min = 3, max = 50, message = "{personne.nom}")
    @Column(name = "NOM")
    protected String nom;
    @Basic(optional=false)
    @Size(min = 3, max = 100, message = "{personne.prenom}")
    @Column(name = "PRENOM")
    protected String prenom;
    @JoinColumn(name = "COORDONNEES_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Coordonnees coordonnees;
    @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Entreprise entreprise;
    
    public Personne() {}

	public Personne(Integer id, 
            String nom,
            String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

    public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Personne[ id=" + id + " ]";
    }

}
