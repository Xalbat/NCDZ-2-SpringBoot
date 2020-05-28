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

import fr.formation.dao.IDAOPayement;
import fr.formation.model.Payement;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payement")
public class PayementApiController {
	
	@Autowired
	private IDAOPayement daoPayement;


	
	
	/** Retourne toute la liste des parachutes avec des informations sommaires
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@GetMapping
	@JsonView(Views.Payement.class)
	public List<Payement> findAll() {
		return daoPayement.findAll();
	}

	
	/** Retourne un parachute avec des information détaillées
	 * @param idParachute
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@GetMapping("/{id}")
	@JsonView(Views.Payement.class)
	public Payement findById(@PathVariable int id) {
		return daoPayement.findById(id).orElse(new Payement());
	}

	
	
	/** Ajoute un Parachute à la BDD
	 * @param parachute
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETAIRE')")
	@PostMapping
	@JsonView(Views.Payement.class)
	public Payement add(@Valid @RequestBody Payement payement, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception();
		}
		return daoPayement.save(payement);
	}

	
	/** Modifie un parachute de la BDD
	 * @param idParachute
	 * @param parachute
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	@JsonView(Views.Payement.class)
	public Payement update(@PathVariable int id, @RequestBody Payement payement) {
		payement.setId(id);
		this.daoPayement.save(payement);
		return payement;
	}

	
	/** Supprime un parachute de la BDD
	 * @param idParachute
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}/supp") 
	@JsonView(Views.Payement.class)
	public boolean delete(@PathVariable int id) {
		try {
			this.daoPayement.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
