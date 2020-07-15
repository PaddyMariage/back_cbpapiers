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
@Table(name = "CUSTOMER_PICTURE")
public class CustomerPicture {

    @Id
    private String idCustomer;

    @MapsId
    @OneToOne
    @JoinColumn(name = "CT_NUM")
    private Customer customer;

    // todo check which Java type translates to SQL BLOB type
    private byte[] image;

}
