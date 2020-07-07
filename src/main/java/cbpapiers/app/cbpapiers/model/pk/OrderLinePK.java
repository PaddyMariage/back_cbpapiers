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
public class OrderLinePK implements Serializable {

    @Column(name="id_article")
    private int idArticle;

    @Column(name="id_order")
    private int idOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLinePK that = (OrderLinePK) o;
        return idArticle == that.idArticle &&
                idOrder == that.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, idOrder);
    }

}
