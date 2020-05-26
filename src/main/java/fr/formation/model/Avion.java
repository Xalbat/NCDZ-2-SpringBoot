package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.enumerator.EtatAvion;
import fr.formation.enumerator.SituationAvion;
import fr.formation.projection.Views;

@Entity
@Table(name = "avion")
public class Avion {

	//Attributs
	@Id
	@Column(name = "id")
	@JsonView(Views.Common.class)
	private int idAvion;
	
	@Column(name = "matricule", length = 10)
	@JsonView(Views.Common.class)
	@NotNull
	private String matricule;

	@Column(name = "modele", length = 25)
	@JsonView(Views.Common.class)
	private String modele;

	@Column(name = "altitude")
	@Positive
	@JsonView(Views.Avion.class)
	private int altitudeMax;

	@Column(name = "capacite", nullable = false)
	@Positive
	@NotNull
	@JsonView(Views.Avion.class)
	private int capacite;

	@Column(name = "rotation_max", nullable = false)
	@Positive
	@NotNull
	@JsonView(Views.Avion.class)
	private int rotationMax;

	@Column(name = "rotation")
	@JsonView(Views.Avion.class)
	@Positive
	private int rotation;

	@Column(name = "temps_montee")
	@JsonView(Views.Avion.class)
	@Positive
	private int tempsMontee;

	@Column(name = "etat", length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView({Views.Avion.class, Views.Pilote.class})
	private EtatAvion etat;

	@Column(name = "situation", length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Avion.class)
	private SituationAvion situation;

	//Relation n°3 inverse
	@OneToOne
	@JoinColumn
	@JsonView(Views.Avion.class)
	private Vol vol;

	//Constructeurs
	public Avion() {}

	public Avion(int idAvion, String matricule, int altitudeMax, int capacite, int rotationMax, int rotation, int tempsMontee,
			EtatAvion etat, SituationAvion situation) {
		super();
		this.idAvion = idAvion;
		this.matricule = matricule;
		this.altitudeMax = altitudeMax;
		this.capacite = capacite;
		this.rotationMax = rotationMax;
		this.rotation = rotation;
		this.tempsMontee = tempsMontee;
		this.etat = etat;
		this.situation = situation;
	}

	//Getters Setters
	public int getIdAvion() {
		return idAvion;
	}
	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricul(String matricule) {
		this.matricule = matricule;
	}

	public int getAltitudeMax() {
		return altitudeMax;
	}
	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public int getRotationMax() {
		return rotationMax;
	}
	public void setRotationMax(int rotationMax) {
		this.rotationMax = rotationMax;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getTempsMontee() {
		return tempsMontee;
	}
	public void setTempsMontee(int tempsMontee) {
		this.tempsMontee = tempsMontee;
	}
	public EtatAvion getEtat() {
		return etat;
	}
	public void setEtat(EtatAvion etat) {
		this.etat = etat;
	}
	public SituationAvion getSituation() {
		return situation;
	}
	public void setSituation(SituationAvion situation) {
		this.situation = situation;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public Vol getVol() {
		return vol;
	}
	public void setVol(Vol vol) {
		this.vol = vol;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	//toString
	@Override
	public String toString() {
		return "Avion [id=" + idAvion + ", altitudeMax=" + altitudeMax + ", capacite=" + capacite + ", rotationMax="
				+ rotationMax + ", rotation=" + rotation + "]";
	}



}
