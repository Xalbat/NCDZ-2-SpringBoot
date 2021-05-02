package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Payement;

public interface IDAOPayement extends JpaRepository<Payement, Integer> {



}
