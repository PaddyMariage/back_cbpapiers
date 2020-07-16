package cbpapiers.app.cbpapiers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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

    @Id
    @Column(name = "DO_PIECE")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "CT_NUM", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<OrderLine> orderLines;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateCommande;
}
