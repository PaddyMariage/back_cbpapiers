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
    @JsonView(MyJsonView.Customer.class)
    private String id;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_Intitule")
    private String name;

    private String password;

    private boolean isAdmin;

    private boolean isActive;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_Adresse")
    private String address;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_Pays")
    private String country;

    @ManyToOne
    @JsonView(MyJsonView.Customer.class)
    @JoinColumn(name = "id_city")
    private City city;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_Telephone")
    private String phoneNumber;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_EMail")
    private String email;

    //ajout d'un fectheager nécessaire pour récupérer l'historique des commandes d'un client
    @JsonView(MyJsonView.Customer.class)
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @JsonView(MyJsonView.Customer.class)
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerPicture customerPicture;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<Discount> discount;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<TopArticleCustomer> topArticleCustomer;

    @JsonView(MyJsonView.Customer.class)
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<CustomerFile> customerFiles;


}
