package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer>{
//	Pas surt que ce DOA soit utile...
}
