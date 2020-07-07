package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderLine {

    @EmbeddedId
    private OrderLinePK orderLinePK;

    @ManyToOne
    @MapsId("id_article")
    private Article article;

    @ManyToOne
    @MapsId("id_order")
    private Order order;

    private int quantity;
}
