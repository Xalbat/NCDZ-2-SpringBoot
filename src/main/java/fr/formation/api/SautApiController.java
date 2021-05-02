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

import fr.formation.dao.IDAOBeerline;
import fr.formation.dao.IDAOSaut;
import fr.formation.model.BeerLine;
import fr.formation.model.Saut;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/saut")
public class SautApiController {
/*
	Gère à la fois les Vols et les Sauts du coté de la secretaire
 */
	
	
	@Autowired
	private IDAOSaut daoSaut;
	@Autowired
	private IDAOBeerline daoBeerLine;


	/** Sélectione tout les sauts disponible sans filtres
	 * faible informations
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Saut.class)
	public List<Saut> findAll() {
		return daoSaut.findAll();
	}
	
	/** Sélectione tout les sauts disponible sans filtres
	 * faible informations
	 * @return
	 */
	@GetMapping("/bl")
	@JsonView(Views.Common.class)
	public List<BeerLine> findAllBeerLine() {
		return daoBeerLine.findAll();
	}

	/** Sélectione un saut avec son id
	 * @param idSaut
	 * @return
	 */
	@GetMapping("/{idSaut}")
	@JsonView(Views.Vol.class)
	public Saut findById(@PathVariable int idSaut) {
		return daoSaut.findById(idSaut).orElse(new Saut());
	}


	/** Ajoute un saut à la BDD
	 * @param saut
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@JsonView(Views.Saut.class)
	public Saut add(@Valid @RequestBody Saut saut, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoSaut.save(saut);
	}


	/** Modification d'un saut existant
	 * @param idSaut
	 * @param saut
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@PutMapping("/{idSaut}")
	@JsonView(Views.Saut.class)
	public Saut update(@PathVariable int idSaut, @RequestBody Saut saut) {
		saut.setIdSaut(idSaut);
		this.daoSaut.save(saut);
		return saut;
	}


	/** Supression d'un vol existant
	 * @param idSaut
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@DeleteMapping("/{idSaut}/supp") 
	@JsonView(Views.Saut.class)
	public boolean delete(@PathVariable int idSaut) {
		try {
			this.daoSaut.deleteById(idSaut);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
