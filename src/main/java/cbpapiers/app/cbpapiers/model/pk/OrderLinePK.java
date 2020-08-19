package cbpapiers.app.cbpapiers.model.pk;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
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

    @Column(name="AR_Ref")
    @JsonView({MyJsonView.OrderDetails.class, MyJsonView.Order.class})
    private String idArticle;

    @Column(name="DO_PIECE")
    @JsonView({MyJsonView.OrderDetails.class, MyJsonView.Order.class})
    private String idOrder;

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
