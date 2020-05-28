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

import fr.formation.dao.IDAOParachute;
import fr.formation.dao.IDAORevision;
import fr.formation.model.Parachute;
import fr.formation.model.Revision;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/parachute")
public class ParachuteApiController {
/*
	Gère à la fois les parachutes du coté des secretaires et de la demande des parachutistes
 */
	
	
	@Autowired
	private IDAOParachute daoParachute;
	@Autowired
	private IDAORevision daoRevision;

//	fonction de check de la vérification des sac ?
	
	
	/** Retourne toute la liste des parachutes avec des informations sommaires
	 * @return
	 */
	@GetMapping
	@JsonView(Views.Parachute.class)
	public List<Parachute> findAll() {
		return daoParachute.findAll();
	}

	
	/** Retourne un parachute avec des information détaillées
	 * @param idParachute
	 * @return
	 */
	@GetMapping("/{idParachute}")
	@JsonView(Views.Parachute.class)
	public Parachute findById(@PathVariable int idParachute) {
		return daoParachute.findById(idParachute).orElse(new Parachute());
	}


	/** Ajoute un Parachute à la BDD
	 * @param parachute
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@PostMapping
	@JsonView(Views.Parachute.class)
	public Parachute add(@Valid @RequestBody Parachute parachute, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		daoRevision.save(parachute.getRevision());
		return daoParachute.save(parachute);
	}

	
	/** Modifie un parachute de la BDD
	 * @param idParachute
	 * @param parachute
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@PutMapping("/{idParachute}")
	@JsonView(Views.Parachute.class)
	public Parachute update(@PathVariable int idParachute, @RequestBody Parachute parachute) {
		parachute.setIdParachute(idParachute);
		daoRevision.save(parachute.getRevision());
		this.daoParachute.save(parachute);
		return parachute;
	}

	
	/** Supprime un parachute de la BDD
	 * @param idParachute
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{idParachute}") 
	@JsonView(Views.Parachute.class)
	public boolean delete(@PathVariable int idParachute) {
		try {
			this.daoParachute.deleteById(idParachute);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
