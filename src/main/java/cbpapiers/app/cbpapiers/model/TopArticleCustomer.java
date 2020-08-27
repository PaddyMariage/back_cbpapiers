package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
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
@Table(name = "TOP_ARTICLE")
public class TopArticleCustomer implements Comparable {

    public TopArticleCustomer(Article article, int position) {
        this.article = article;
        this.position = position;
    }

    @EmbeddedId
    private TopArticleCustomerPK topArticleCustomerPK;

    @ManyToOne
    @MapsId("AR_Ref")
    @JoinColumn(name = "AR_Ref")
    @JsonView(MyJsonView.TopArticleCustomer.class)
    private Article article;

    @ManyToOne
    @MapsId("CT_NUM")
    @JoinColumn(name = "CT_NUM")
    private Customer customer;

    @JsonView(MyJsonView.TopArticleCustomer.class)
    private int position;

    @Override
    public int compareTo(Object top) {
        return this.position - ((TopArticleCustomer)top).getPosition();
    }

    @Override
    public String toString() {
        return "TopArticleCustomer{" +
                "topArticleCustomerPK=" + topArticleCustomerPK +
                ", article=" + article +
                ", customer=" + customer +
                ", position=" + position +
                '}';
    }
}
