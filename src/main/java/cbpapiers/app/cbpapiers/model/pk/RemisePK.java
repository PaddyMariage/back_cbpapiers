package cbpapiers.app.cbpapiers.model.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RemisePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int idClient;

    @Column
    private int idRemise;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(int idRemise) {
        this.idRemise = idRemise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemisePK)) return false;
        RemisePK remisePK = (RemisePK) o;
        return idClient == remisePK.idClient &&
                idRemise == remisePK.idRemise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idRemise);
    }
}
