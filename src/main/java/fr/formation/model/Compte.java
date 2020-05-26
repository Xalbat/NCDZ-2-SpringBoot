package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compte")
public class Compte {
	
	//Attributs
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idCompte;
	
	@Column(name = "login", length = 25, nullable = false)
	protected String login;

	@Column(name = "password", length = 250, nullable = false)
	protected String password;
	
	@Column(name="type_compte", length = 20, insertable = false, updatable = false)
	protected String typeCompte;

	
	// Constructeur 
	
	public Compte() {

	}
	
	public Compte(String login, String password, String typeCompte) {
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	
	//Getter et Setter
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	
	
	
}
