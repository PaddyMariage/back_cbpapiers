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
@Table(name = 'F_COMPTET_INFO')
public class InfoCustomer {

    @Id
    private String id;

    @OneToOne
    @MapsId
    private Customer customer;

    @Column(name = "CT_EMail")
    private String email;

    private String password;

    private boolean isAdmin;

    private boolean isActive;
}
