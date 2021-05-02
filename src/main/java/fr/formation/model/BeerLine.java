package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.formation.model.Parachutiste;
import fr.formation.model.Saut;


@Entity
@Table(name = "beerLine")
public class BeerLine {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBeerLine;
	
	@ManyToOne
	@JoinColumn(name = "id_saut")
	@NotNull
	private Saut saut;
	
	@ManyToOne
	@JoinColumn(name = "id_parachutiste")
	@NotNull
	private Parachutiste parachutiste;
	

	//___________________________________________________________
	

	public int getIdBeerLine() {
		return idBeerLine;
	}
	public void setIdBeerLine(int idBeerLine) {
		this.idBeerLine = idBeerLine;
	}
	public Saut getVol() {
		return this.saut;
	}
	public void setVol(Saut saut) {
		this.saut = saut;
	}
	public Parachutiste getParachutiste() {
		return this.parachutiste;
	}
	public void setParachutiste(Parachutiste parachutiste) {
		this.parachutiste = parachutiste;
	}
	
	
	
	
}
