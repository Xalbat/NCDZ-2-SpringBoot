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

import fr.formation.dao.IDAOVol;
import fr.formation.model.Vol;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/vol")
public class VolApiController {
/*
	Gère à la fois les Vols et les Sauts du coté de la secretaire
 */
	
	
	@Autowired
	private IDAOVol daoVol;


	/** Sélectione tout les vols disponible sans filtres
	 * faible informations
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Vol.class)
	public List<Vol> findAll() {
		return daoVol.findAll();
	}


	/** Sélectione un vol avec son id
	 * @param idVol
	 * @return
	 */
	@GetMapping("/{idVol}")
	@JsonView(Views.Vol.class)
	public Vol findById(@PathVariable int idVol) {
		return daoVol.findById(idVol).orElse(new Vol());
	}


	/** Ajoute un vol à la BDD
	 * @param vol
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@JsonView(Views.Vol.class)
	public Vol add(@Valid @RequestBody Vol vol, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoVol.save(vol);
	}


	/** Modification d'un vol existant
	 * @param idVol
	 * @param vol
	 * @return
	 */
	@PutMapping("/{idVol}")
	@JsonView(Views.Vol.class)
	public Vol update(@PathVariable int idVol, @RequestBody Vol vol) {
		vol.setIdVol(idVol);
		System.out.println(vol.getIdVol());
		this.daoVol.save(vol);
		return vol;
	}


	/** Supression d'un vol existant
	 * @param idVol
	 * @return
	 */
	@DeleteMapping("/{idVol}/supp") 
	@JsonView(Views.Vol.class)
	public boolean delete(@PathVariable int idVol) {
		try {
			this.daoVol.deleteById(idVol);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



}
