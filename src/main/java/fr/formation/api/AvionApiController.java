package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOAvion;
import fr.formation.model.Avion;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/avion")
public class AvionApiController {
/*
	Gère les avions du coté de l'administrateur
 */
	
	
	@Autowired
	private IDAOAvion daoAvion;



	/** Sélectione tout les Avions disponible sans filtres
	 * faible informations
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Common.class)
	public List<Avion> findAll() {
		return daoAvion.findAll();
	}


	/** Sélectione un Avion avec son id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@JsonView(Views.Avion.class)
	public Avion findById(@PathVariable int id) {
		return daoAvion.findById(id).orElse(new Avion());
	}


	/** Modification d'un avion existant 
	 * @param id
	 * @param avion
	 * @return
	 */
	@PutMapping("/{id}")
	@JsonView(Views.Avion.class)
	public Avion update(@PathVariable int id, @RequestBody Avion avion) {
		avion.setIdAvion(id);
		this.daoAvion.save(avion);
		return avion;
	}


	/** Ajoute un avion à la BDD
	 * @param avion
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@JsonView(Views.Avion.class)
	public Avion add(@Valid @RequestBody Avion avion, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoAvion.save(avion);
	}


	/** Supression d'un avion existant
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}/supp") 
	@JsonView(Views.Avion.class)
	public boolean delete(@PathVariable int id) {
		try {
			this.daoAvion.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



}
