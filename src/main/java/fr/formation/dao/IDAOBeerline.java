package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.BeerLine;

public interface IDAOBeerline extends JpaRepository<BeerLine, Integer>{
//	Pas surt que ce DOA soit utile...
}
