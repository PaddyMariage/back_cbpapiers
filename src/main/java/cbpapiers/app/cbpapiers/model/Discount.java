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
@Table(name = "F_ARTCLIENT")
public class Discount {

    @EmbeddedId
    private DiscountPK discountPK;

    @ManyToOne
    @MapsId("idArticle")
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @MapsId("idCustomer")
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "AR_remise")
    private double discount;
}
