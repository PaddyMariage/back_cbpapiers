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
@Table(name = 'ARTICLE_DETAILS')
public class ArticleDetails {

    @Id
    private String id;

    @OneToOne
    @MapsId
    private Article article;

    @Column(name = "article_details")
    private String description;
}
