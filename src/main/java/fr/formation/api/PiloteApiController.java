package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.formation.dao.IDAOPilote;
import fr.formation.model.Pilote;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pilote")
public class PiloteApiController {
/*
	Gère les pilotes du coté de l'administrateur
 */

	@Autowired
	private IDAOPilote daoPilote;


	/** Sélectione tout les pilotes disponible sans filtres
	 * faible informations
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Common.class)
	public List<Pilote> findAll() {
		return daoPilote.findAll();
	}


	/** Sélectione un pilote avec son id
	 * @param licence
	 * @return
	 */
	@GetMapping("/{licence}")
	@JsonView(Views.Pilote.class)
	public Pilote findById(@PathVariable int licence) {
		return daoPilote.findById(licence).orElse(new Pilote());
	}


	/** Ajoute un nouveau pilote à la BDD
	 * @param pilote
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@JsonView(Views.Pilote.class)
	public Pilote add(@Valid @RequestBody Pilote pilote, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoPilote.save(pilote);
	}


	/** Modification d'un pilote existant
	 * @param licence
	 * @param pilote
	 * @return
	 */
	@PutMapping("/{licence}")
	@JsonView(Views.Pilote.class)
	public Pilote update(@PathVariable int licence, @RequestBody Pilote pilote) {
		pilote.setLicence(licence);
		this.daoPilote.save(pilote);
		return pilote;
	}


	/** Supression d'un pilote existant
	 * @param licence
	 * @return
	 */
	@DeleteMapping("/{licence}/supp") 
	@JsonView(Views.Pilote.class)
	public boolean delete(@PathVariable int licence) {
		try {
			this.daoPilote.deleteById(licence);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



}
