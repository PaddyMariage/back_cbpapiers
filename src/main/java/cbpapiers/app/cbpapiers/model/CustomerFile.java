package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "CUSTOMER_FILES")
public class CustomerFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MyJsonView.Customer.class)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CT_NUM")
    private Customer customer;

    // todo check which Java type translates to SQL BLOB type
    @JsonView(MyJsonView.Customer.class)
    private byte[] customerFile;
}
