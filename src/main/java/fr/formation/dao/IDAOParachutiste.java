package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Parachutiste;

public interface IDAOParachutiste extends JpaRepository<Parachutiste, Long> {
	
}
