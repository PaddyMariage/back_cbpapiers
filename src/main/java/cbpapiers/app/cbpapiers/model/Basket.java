package cbpapiers.app.cbpapiers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "CLIENT_BASKET")
public class Basket {

    @Id
    @Column(name = "DO_PIECE")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "CT_NUM", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    Set<OrderLine> orderLines;

}
