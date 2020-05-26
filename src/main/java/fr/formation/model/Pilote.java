package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;

@Entity
@Table(name = "pilote")
public class Pilote {
	
	//Attributs
	@Id
	@Column(name = "licence")
	@JsonView(Views.Common.class)
	private int licence;

	@Column(name = "nom", nullable = false, length = 25)
	@JsonView({Views.Pilote.class, Views.Vol.class})
	private String nom;
	
	@Column(name = "prenom", nullable = false, length = 25)
	@JsonView(Views.Pilote.class)
	private String prenom;

	@ManyToMany/*(mappedBy = "idAvion")*/
	//@JoinColumn(name = "id_avion")
	@JoinTable(name = "habilitation_pilote_avion",
	uniqueConstraints = @UniqueConstraint(columnNames = { "licence_pilote", "id_avion"}),
	joinColumns = @JoinColumn(name = "licence_pilote", referencedColumnName = "licence"),
	inverseJoinColumns = @JoinColumn(name = "id_avion", referencedColumnName = "id"))
	@JsonView(Views.Pilote.class)
	private List<Avion> listeAvion;

	
	
	//Getters & Setters
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
	public int getLicence() {
		return licence;
	}
	public void setLicence(int licence) {
		this.licence = licence;
	}
	public List<Avion> getListeAvion() {
		return listeAvion;
	}
	public void setListeAvion(List<Avion> listeAvion) {
		this.listeAvion = listeAvion;
	}
	
	//toString
	@Override
	public String toString() {
		return "Pilote [nom=" + nom + ", prenom=" + prenom + ", licence=" + licence
				+ ", listeAvion=" + listeAvion + "]";
	}
	
	//Constructeurs
	public Pilote(int licence, String nom, String prenom, List<Avion> listeAvion) {
		this.nom = nom;
		this.prenom = prenom;
		this.licence = licence;
		this.listeAvion = listeAvion;
	}

	public Pilote() {}
}
