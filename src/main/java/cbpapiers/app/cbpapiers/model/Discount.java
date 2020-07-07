package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Discount {

    @EmbeddedId
    private DiscountPK discountPK;

    @ManyToOne
    @MapsId("id_article")
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @MapsId("id_customer")
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private double discount;

}
