package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Revision;

public interface IDAORevision extends JpaRepository<Revision, Integer>{
//	Pas surt que ce DOA soit utile...
}
