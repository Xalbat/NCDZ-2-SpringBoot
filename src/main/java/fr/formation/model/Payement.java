package fr.formation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.enumerator.MoyenPayement;
import fr.formation.projection.Views;

@Entity
@Table(name = "payement")
public class Payement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonView(Views.Common.class)
	private int id;
	
	@Column(name = "valeur", nullable = false)
	private double valeur;
	
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
	private LocalDate date;
	
	@Column(name = "moyen_payement", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	private MoyenPayement moyenPayement;
	
	@ManyToOne
	@JoinColumn(name = "id_parachutiste", nullable = false)
	@JsonView(Views.Common.class)
	private Parachutiste parachutiste;
}
