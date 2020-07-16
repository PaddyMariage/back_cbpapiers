package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(MyJsonView.Customer.class)
    private String name;

    private String password;

    private boolean isAdmin;

    private boolean isActive;

    @Column(name = "CT_Adresse")
    private String address;

    @Column(name = "CT_Telephone")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;

    @Column(name = "CT_EMail")
    private String email;

    //ajout d'un fectheager nécessaire pour récupérer l'historique des commandes d'un client
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerPicture customerPicture;

    @OneToMany(mappedBy = "customer")
    Set<Discount> discount;

    @OneToMany(mappedBy = "customer")
    Set<TopArticleCustomer> topArticleCustomer;

    @OneToMany(mappedBy = "customer")
    Set<CustomerFile> customerFiles;
}
