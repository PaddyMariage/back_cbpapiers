package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import com.fasterxml.jackson.annotation.JsonView;
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
    @MapsId("AR_REF")
    @JoinColumn(name = "AR_Ref")
    private Article article;

    @ManyToOne
    @MapsId("CT_NUM")
    @JoinColumn(name = "CT_NUM")
    private Customer customer;

    @Column(name = "AR_Remise")
    private double discount;

    @Column(name = "AC_PrixVen")
    private double clientPrice;

}
