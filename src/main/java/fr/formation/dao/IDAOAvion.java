package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Avion;

public interface IDAOAvion extends JpaRepository<Avion, Integer> {
	
//	@Query("SELECT COUNT(m) FROM MonsterEntity m")
//	public Integer countNombreMonstre();
	
	

}
