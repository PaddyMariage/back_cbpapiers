package cbpapiers.app.cbpapiers.model;

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
@Table(name = "F_COMPTET")
public class Customer {

    @Id
    @Column(name = "CT_NUM")
    private String id;

    @Column(name = "CT_Intitule")
    private String name;

    @Column(name = "CT_Adresse")
    private String adress;

    @Column(name = "CT_Telephone")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private InfoCustomer infoCustomer;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Picture picture;

    @OneToMany(mappedBy = "customer")
    Set<Discount> discount;

    @OneToMany(mappedBy = "customer")
    Set<TopArticleCustomer> topArticleCustomer;
}
