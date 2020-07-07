package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.CommandePK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @EmbeddedId
    private CommandePK commandePK;

    @Column(name = "DO_PIECE")
    private String numCommande;

    @ManyToOne
    @JoinColumn(name="id_customer", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    Set<OrderLine> orderLines;


}
