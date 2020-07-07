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
public class TopArticleCustomerPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="id_customer")
    private int idCustomer;

    @Column(name="id_article")
    private int idArticle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopArticleCustomerPK that = (TopArticleCustomerPK) o;
        return idCustomer == that.idCustomer &&
                idArticle == that.idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, idArticle);
    }
}
