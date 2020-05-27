package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.enumerator.EtatParachute;
import fr.formation.enumerator.Proprietaire;
import fr.formation.enumerator.TypeSecurite;
import fr.formation.projection.Views;

@Entity
@Table(name = "parachute")
public class Parachute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonView(Views.Common.class)
	private int idParachute;
	
	@Column(name = "nom_harnais", nullable = false, length = 25)
	@JsonView(Views.Common.class)
	private String nomHarnais;
	
	@Column(name = "type_securite", nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	private TypeSecurite typeSecurite;
	
	@Column(name = "nom_voile_principale", nullable = false, length = 25)
	@JsonView(Views.Common.class)
	private String nomVoilePricipale;
	
	@Column(name = "taille_voile_principale", nullable = false)
	@JsonView(Views.Common.class)
	private int tailleVoilePricipale;
	
	@Column(name = "nom_voile_secours", nullable = false, length = 25)
	@JsonView(Views.Common.class)
	private String nomVoileSecours;
	
	@Column(name = "taille_voile_secours", nullable = false)
	@JsonView(Views.Common.class)
	private int tailleVoileSecours;
	
	@OneToOne
	@JoinColumn(name = "id_revision")
	@NotNull
	@JsonView({Views.Common.class, Views.Vol.class})
	private Revision revision;
	
	@Column(name = "proprietaire", nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	private Proprietaire proprietaire;
	
	@Column(name = "etat_parachute", length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	private EtatParachute etatParachute;

	@Column(name = "Declanch_secours_auto")
	@JsonView(Views.Common.class)
	private int secoursAuto;
	
	@Column(name = "Declanch_secours_manuel")
	@JsonView(Views.Common.class)
	private int secoursManuel;
	
	
	public Parachute() {}

	// -----------   Getter Setter -------------- //
	
	public int getIdParachute() {
		return idParachute;
	}
	public void setIdParachute(int id) {
		this.idParachute = id;
	}
	public String getNomHarnais() {
		return nomHarnais;
	}
	public void setNomHarnais(String nomHarnais) {
		this.nomHarnais = nomHarnais;
	}
	public TypeSecurite getNomSecurite() {
		return typeSecurite;
	}
	public void setNomSecurite(TypeSecurite nomSecurite) {
		this.typeSecurite = nomSecurite;
	}
	public String getNomVoilePricipale() {
		return nomVoilePricipale;
	}
	public void setNomVoilePricipale(String nomVoilePricipale) {
		this.nomVoilePricipale = nomVoilePricipale;
	}
	public int getTailleVoilePricipale() {
		return tailleVoilePricipale;
	}
	public void setTailleVoilePricipale(int tailleVoilePricipale) {
		this.tailleVoilePricipale = tailleVoilePricipale;
	}
	public String getNomVoileSecours() {
		return nomVoileSecours;
	}
	public void setNomVoileSecours(String nomVoileSecours) {
		this.nomVoileSecours = nomVoileSecours;
	}
	public int getTailleVoileSecours() {
		return tailleVoileSecours;
	}
	public void setTailleVoileSecours(int tailleVoileSecours) {
		this.tailleVoileSecours = tailleVoileSecours;
	}
	public Revision getRevision() {
		return revision;
	}
	public void setRevision(Revision revision) {
		this.revision = revision;
	}
	public Proprietaire getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}
	public EtatParachute getEtatParachute() {
		return etatParachute;
	}
	public void setEtatParachute(EtatParachute etatParachute) {
		this.etatParachute = etatParachute;
	}
	
	
}
