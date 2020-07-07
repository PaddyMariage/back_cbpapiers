package cbpapiers.app.cbpapiers.model.pk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class DiscountPK implements Serializable {

    @Column(name="id_customer")
    private String idCustomer;

    @Column(name="id_article")
    private String idArticle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountPK that = (DiscountPK) o;
        return idCustomer == that.idCustomer &&
                idArticle == that.idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, idArticle);
    }
}
