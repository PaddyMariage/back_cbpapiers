package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
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

    @EmbeddedId
    private TopArticleCustomerPK topArticleCustomerPK;

    @ManyToOne
    @MapsId("id_article")
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @MapsId("id_customer")
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private int position;

    @Override
    public int compareTo(Object top) {
        return this.position - ((TopArticleCustomer)top).getPosition();
    }
}
