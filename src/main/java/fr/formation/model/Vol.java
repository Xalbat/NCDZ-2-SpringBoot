package fr.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.enumerator.SituationVol;
import fr.formation.projection.Views;

@Entity
@Table(name="vol")
public class Vol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView(Views.Common.class)
	private int idVol;
	
	@ManyToOne
	@JoinColumn(name = "id_pilote")
	@JsonView(Views.Vol.class)
	private Pilote pilote;
	
	//Relation n°3 directe
	@OneToOne
	@JoinColumn(name = "id_avion")
	@JsonView({Views.Vol.class, Views.Parachutiste.class})
	private Avion avion;
	
	@Column(name = "situation_vol", length = 15)
	@Enumerated(EnumType.STRING)
	@JsonView({Views.Common.class, Views.Saut.class})
	private SituationVol situationVol;
	
	@OneToOne
	@JoinColumn(name = "id_respo_vol")
	@JsonView({Views.Vol.class, Views.Saut.class})
	private Parachutiste respoVol;
	
	@ManyToOne
	@JoinColumn(name = "id_respo_sol")
	@JsonView({Views.Vol.class, Views.Saut.class})
	private Parachutiste respoSol;
	
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
	private LocalDate date;

	//Relation n°1 inverse
	@OneToMany(mappedBy = "vol")
	@JsonView(Views.Vol.class)
	private List<Saut> listSaut;
	
	
	//_______________________________________________________
	//	Getters & Setters
	public int getIdVol() {
		return idVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}

	public Pilote getPilote() {
		return pilote;
	}

	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public SituationVol getSituationVol() {
		return situationVol;
	}

	public void setSituationVol(SituationVol situationVol) {
		this.situationVol = situationVol;
	}

	public Parachutiste getRespoVol() {
		return respoVol;
	}

	public void setRespoVol(Parachutiste respoVol) {
		this.respoVol = respoVol;
	}

	public Parachutiste getRespoSol() {
		return respoSol;
	}

	public void setRespoSol(Parachutiste respoSol) {
		this.respoSol = respoSol;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Saut> getListSaut() {
		return listSaut;
	}

	public void setListSaut(List<Saut> listSaut) {
		this.listSaut = listSaut;
	}
	
	
	
	

}
