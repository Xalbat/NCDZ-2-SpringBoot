package fr.formation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOCompte;
import fr.formation.exception.CompteNotFoundException;
import fr.formation.model.Compte;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/compte")
public class CompteApiController {
	
	@Autowired
	private IDAOCompte daoCompte;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	
	@PostMapping("/login")
	@JsonView(Views.Common.class)
	public Compte login(@RequestBody Compte compte) {
		System.out.println("yousk2");
		Compte bddCompte = this.daoCompte.findByLogin(compte.getLogin()).orElseThrow(CompteNotFoundException::new);
		if (!passwordEncoder.matches(compte.getPassword(), bddCompte.getPassword())) {
			throw new CompteNotFoundException();
		}

		return bddCompte;
	}

	


}
