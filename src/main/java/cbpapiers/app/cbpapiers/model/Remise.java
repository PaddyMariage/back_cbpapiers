package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.RemisePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Remise {

    @EmbeddedId
    private RemisePK remisePK;

    private double remise;

    public RemisePK getRemisePK() {
        return remisePK;
    }

    public void setRemisePK(RemisePK remisePK) {
        this.remisePK = remisePK;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }
}
