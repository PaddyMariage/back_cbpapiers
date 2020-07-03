package cbpapiers.app.cbpapiers.model.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListeArticlePersoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int idClient;

    @Column
    private int idArticle;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListeArticlePersoPK)) return false;
        ListeArticlePersoPK that = (ListeArticlePersoPK) o;
        return idClient == that.idClient &&
                idArticle == that.idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idArticle);
    }
}
