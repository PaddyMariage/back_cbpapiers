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
@Table(name = "F_ARTICLE")
public class Article {

    @Id
    @Column(name = "AR_Ref")
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String reference;

    @Transient
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String AR_Ref;

    @JsonView({MyJsonView.Article.class, MyJsonView.ArticleDetails.class})
    @Column(name = "AR_PrixVen")
    private double unitPrice;

    @Transient
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String AR_PrixVen;

    @Column(name = "AR_Design")
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String label;

    @Transient
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String AR_Design;

    @Column(name = "FA_CodeFamille")
    private String family;

    @Transient
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.ArticleDetails.class, MyJsonView.TopArticleCustomer.class})
    private String FA_CodeFamille;


    @Transient
    @JsonView({MyJsonView.Article.class,MyJsonView.OrderDetails.class, MyJsonView.TopArticleCustomer.class})
    private double finalPrice;

    //ajout du fetch eager car erreur qui dit : failed to lazily initialize a collection of role : Article.orderLines quand on fait un findbyID
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    Set<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "id_details")
    private ArticleDetails articleDetails;

    @ManyToOne
    @JoinColumn(name = "id_picture")
    private ArticlePicture articlePicture;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    Set<Discount> discounts;

    @Override
    public String toString() {
        return "Article{" +
                "reference='" + reference + '\'' +
                ", unitPrice=" + unitPrice +
                ", label='" + label + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}
