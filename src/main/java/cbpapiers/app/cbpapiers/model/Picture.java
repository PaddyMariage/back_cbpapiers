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
@Table(name = "Picture")
public class Picture {

    @Id
    private String id;

    // todo check which Java type translates to SQL BLOB type
    private byte[] image;

    @OneToOne
    @MapsId
    private Customer customer;
}
