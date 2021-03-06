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
    @JsonView({MyJsonView.Article.class,
            MyJsonView.OrderDetails.class,
            MyJsonView.Order.class,
            MyJsonView.ArticleDetails.class,
            MyJsonView.TopArticleCustomer.class})
    private String reference;

    @JsonView({MyJsonView.Article.class, MyJsonView.ArticleDetails.class})
    @Column(name = "AR_PrixVen")
    private double unitPrice;

    @Column(name = "AR_Design")
    @JsonView({MyJsonView.Article.class,
            MyJsonView.OrderDetails.class,
            MyJsonView.Order.class,
            MyJsonView.ArticleDetails.class,
            MyJsonView.TopArticleCustomer.class})
    private String label;

    @Column(name = "FA_CodeFamille")
    private String family;

    @Transient
    @JsonView({MyJsonView.Article.class,
            MyJsonView.OrderDetails.class,
            MyJsonView.Order.class,
            MyJsonView.TopArticleCustomer.class})
    private double finalPrice;

    @OneToMany(mappedBy = "article")
    Set<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "id_details")
    @JsonView({MyJsonView.Article.class,
            MyJsonView.OrderDetails.class,
            MyJsonView.Order.class,
            MyJsonView.ArticleDetails.class,
            MyJsonView.TopArticleCustomer.class})
    private ArticleDetails articleDetails;

    @ManyToOne
    @JoinColumn(name = "id_picture")
    @JsonView({MyJsonView.Article.class,
            MyJsonView.OrderDetails.class,
            MyJsonView.Order.class,
            MyJsonView.ArticleDetails.class,
            MyJsonView.TopArticleCustomer.class})
    private ArticlePicture articlePicture;

    @OneToMany(mappedBy = "article")
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
