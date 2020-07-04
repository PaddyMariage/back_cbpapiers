package cbpapiers.app.cbpapiers.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    private String id;

    private String nom;

    private String adresse;

    private Ville ville;

    @OneToMany(mappedBy = "commandePK.idClient")
    private List<Commande> listeCommande;
}
