package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;



@Entity
@Table(name = "saut")
public class Saut {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int idSaut;
	
	//Relation n°1 directe
	@ManyToOne
	@JoinColumn(name = "id_vol")
	@JsonView({Views.Saut.class, Views.Parachutiste.class})
	private Vol vol;
	
	@Column(name = "altitude")
	@NotNull
	@JsonView(Views.Common.class)
	private int altitude;
	
	
	//Relation n°2 directe
	/*@OneToMany
	@JoinColumn(name = "list_parachutiste")*/
	@ManyToMany
	@JoinTable(name = "liste_parachutiste_saut",
	uniqueConstraints = @UniqueConstraint(columnNames = { "id_saut", "id_parachutiste"}),
	joinColumns = @JoinColumn(name = "id_saut", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_parachutiste", referencedColumnName = "numero_licence"))
	@JsonView(Views.Saut.class)
	private List<Parachutiste> listParachutiste;
	
	@Column
	@NotNull
	@JsonView(Views.Saut.class)
	private boolean tandem;

	//___________________________________________________________
	
	public int getIdSaut() {
		return this.idSaut;
	}
	public void setIdSaut(int idSaut) {
		this.idSaut = idSaut;
	}
	public Vol getVol() {
		return this.vol;
	}
	public void setVol(Vol vol) {
		this.vol = vol;
	}
	public int getAltitude() {
		return this.altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public List<Parachutiste> getListParachutiste() {
		return this.listParachutiste;
	}
	public void setListParachutiste(List<Parachutiste> listParachutiste) {
		this.listParachutiste = listParachutiste;
	}
	public boolean isTandem() {
		return this.tandem;
	}
	public void setTandem(boolean tandem) {
		this.tandem = tandem;
	}
	
	
	
}
