package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CLIENT_ORDER")
public class Order {

    //on génère l'id simplement en attendant de pouvoir implémenter un custom generator
    @Id
    @GeneratedValue(generator = "orderIdGenerator")
    @GenericGenerator(name = "orderIdGenerator", strategy = "cbpapiers.app.cbpapiers.model.orderIdGenerator.OrderIdGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "prefix", value = "MOBI")}
    )
    @Column(name = "DO_PIECE")
    @JsonView({MyJsonView.Order.class, MyJsonView.OrderDetails.class})
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "CT_NUM", nullable = false)
    @JsonView({MyJsonView.Order.class, MyJsonView.OrderDetails.class})
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonView({MyJsonView.Order.class, MyJsonView.OrderDetails.class})
    Set<OrderLine> orderLines;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonView({MyJsonView.Order.class, MyJsonView.OrderDetails.class})
    private Date dateCommande;
}
