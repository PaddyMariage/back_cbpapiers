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
@Table(name = "ARTICLE_DETAILS")
public class ArticleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "articleDetails",fetch = FetchType.EAGER)
    @JsonView(MyJsonView.ArticleDetails.class)
    private Set<Article> article;

    @Column(name = "AR_Details")
    @JsonView(MyJsonView.ArticleDetails.class)
    private String description;
}
