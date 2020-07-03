package cbpapiers.app.cbpapiers.model.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommandePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int idClient;

    @Column
    private int idCommande;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandePK)) return false;
        CommandePK that = (CommandePK) o;
        return idClient == that.idClient &&
                idCommande == that.idCommande;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idCommande);
    }
}
