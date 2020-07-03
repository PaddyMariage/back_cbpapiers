package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.ListeArticlePersoPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ListeArticlePerso {

    @EmbeddedId
    private ListeArticlePersoPK listeArticlePersoPK;

    private int position;

    public ListeArticlePersoPK getListeArticlePersoPK() {
        return listeArticlePersoPK;
    }

    public void setListeArticlePersoPK(ListeArticlePersoPK listeArticlePersoPK) {
        this.listeArticlePersoPK = listeArticlePersoPK;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
