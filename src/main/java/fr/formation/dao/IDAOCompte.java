package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import fr.formation.model.Compte;

//@PreAuthorize("isAnonymous()")
public interface IDAOCompte extends JpaRepository<Compte, Integer>{

	public Compte findByLogin(String login);
	
}
