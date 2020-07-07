package cbpapiers.app.cbpapiers.model;

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
public class InfoCustomer {

    @Id
    private String id;

    @Column(name = "CT_EMail")
    private String email;

    private String password;

    private String role;

    @OneToOne
    @MapsId
    private Customer customer;

}
