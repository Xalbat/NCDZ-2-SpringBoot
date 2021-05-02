package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Pilote;

public interface IDAOPilote extends JpaRepository<Pilote, Integer> {

}
