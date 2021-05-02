package fr.formation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Compte;


public interface IDAOCompte extends JpaRepository<Compte, Integer>{

	public Optional<Compte> findByLogin(String login);

	
}
