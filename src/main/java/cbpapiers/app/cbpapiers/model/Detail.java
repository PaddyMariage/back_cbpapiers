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
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String description;

    @OneToOne
    @MapsId
    private Article article;
}
