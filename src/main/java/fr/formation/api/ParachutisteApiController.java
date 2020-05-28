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

import fr.formation.dao.IDAOParachutiste;
import fr.formation.model.Parachutiste;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/parachutiste")
public class ParachutisteApiController {
/*
	Gère à la fois les parachutistes du coté des secretaires et de la demande des parachutistes
 */
	
	
	@Autowired
	private IDAOParachutiste daoParachutiste;


//	fonction de check de la vérification des sac ?
	
	
	/** Retourne toute la liste des parachutiste des des informations sommaires
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Parachutiste.class)
	public List<Parachutiste> findAll() {
		return daoParachutiste.findAll();
	}

	
	/** Retourne un parachutiste avec des information déaillées
	 * @param numeroLicence
	 * @return
	 */
	@GetMapping("/{numeroLicence}")
	@JsonView(Views.Parachutiste.class)
	public Parachutiste findById(@PathVariable int numeroLicence) {
		return daoParachutiste.findById(numeroLicence).orElse(new Parachutiste());
	}


	/** Ajoute un Parachutiste à la BDD
	 * @param parachutiste
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@PostMapping
	@JsonView(Views.Parachutiste.class)
	public Parachutiste add(@Valid @RequestBody Parachutiste parachutiste, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoParachutiste.save(parachutiste);
	}

	/** Modifie un parachutiste de la BDD
	 * @param numeroLicence
	 * @param parachutiste
	 * @return
	 */
	//Les parachutiste doivent pouvoir modifier leur parachute, donc ils faut qu'il ai les droits de modification
	@PutMapping("/{numeroLicence}")
	@JsonView(Views.Parachutiste.class)
	public Parachutiste update(@PathVariable int numeroLicence, @RequestBody Parachutiste parachutiste) {
		parachutiste.setNumeroLicence(numeroLicence);
		this.daoParachutiste.save(parachutiste);
		return parachutiste;
	}

	
	/** Supprime un parachutiste de la BDD
	 * @param numeroLicence
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{numeroLicence}") 
	@JsonView(Views.Parachutiste.class)
	public boolean delete(@PathVariable int numeroLicence) {
		try {
			this.daoParachutiste.deleteById(numeroLicence);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
