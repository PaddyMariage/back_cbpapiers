package cbpapiers.app.cbpapiers.model;


import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "F_DOCLIGNE")
public class OrderLine {

    @EmbeddedId
    @JsonView(MyJsonView.OrderDetails.class)
    private OrderLinePK orderLinePK;

    @Transient
    @JsonView(MyJsonView.OrderDetails.class)
    private String CT_Num;

    @Transient
    @JsonView(MyJsonView.OrderDetails.class)
    private String DO_Piece;

    @Transient
    @JsonView(MyJsonView.OrderDetails.class)
    private String AR_Ref;

    @JsonView(MyJsonView.OrderDetails.class)
    private int quantity;

    @Transient
    @JsonView(MyJsonView.OrderDetails.class)
    private String DL_Qte;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @MapsId("AR_Ref")
    @JoinColumn(name = "AR_Ref")
    @JsonView(MyJsonView.OrderDetails.class)
    private Article article;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @MapsId("DO_PIECE")
    @JoinColumn(name = "DO_PIECE")
    private Order order;

    @Override
    public String toString() {
        return "OrderLine{" +
                "CT_Num='" + CT_Num + '\'' +
                ", DO_Piece='" + DO_Piece + '\'' +
                ", AR_Ref='" + AR_Ref + '\'' +
                ", DL_Qte='" + DL_Qte + '\'' +
                ", DO_Date='" + DO_Date + '\'' +
                '}';
    }

    @Transient
    @JsonView(MyJsonView.OrderDetails.class)
    private String DO_Date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(getCT_Num(), orderLine.getCT_Num()) &&
                Objects.equals(getDO_Piece(), orderLine.getDO_Piece()) &&
                Objects.equals(getAR_Ref(), orderLine.getAR_Ref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCT_Num(), getDO_Piece(), getAR_Ref());
    }
}
