package fr.formation.projection;
import org.springframework.data.rest.core.config.Projection;

import fr.formation.model.Avion;
import fr.formation.projection.Views.Compte;

@Projection(name = "avionProjection", types = { Avion.class, Compte.class})
public interface AvionProjection {

}
