package cbpapiers.app.cbpapiers.model;


import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "F_DOCLIGNE")
public class OrderLine {

    @EmbeddedId
    @JsonView(MyJsonView.OrderDetails.class)
    private OrderLinePK orderLinePK;

    @JsonView(MyJsonView.OrderDetails.class)
    private int quantity;

    @ManyToOne
    @MapsId("AR_Ref")
    @JoinColumn(name = "AR_Ref")
    @JsonView(MyJsonView.OrderDetails.class)
    private Article article;

    @ManyToOne
    @MapsId("DO_PIECE")
    @JoinColumn(name = "DO_PIECE")
    private Order order;



}
