package cbpapiers.app.cbpapiers.model.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LigneCommandePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int idCommande;

    @Column
    private int idLigneCommande;

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(int idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LigneCommandePK)) return false;
        LigneCommandePK that = (LigneCommandePK) o;
        return idCommande == that.idCommande &&
                idLigneCommande == that.idLigneCommande;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, idLigneCommande);
    }
}
