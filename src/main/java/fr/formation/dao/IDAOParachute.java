package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Parachute;

public interface IDAOParachute extends JpaRepository<Parachute, Integer> {

}
