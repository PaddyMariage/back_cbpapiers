package cbpapiers.app.cbpapiers.model;

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
public class Article {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="AR_Ref")
    private String reference;

    @Column(name="AR_PriVen")
    private double Unitprice;

    @Column(name="AR_Design")
    private String label;

    @Column(name="FA_CodeFamille")
    private String family;

    @OneToMany(mappedBy = "article")
    Set<OrderLine> orderLines;

    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private Detail detail;

    @OneToMany(mappedBy = "article")
    Set<Discount> discount;

    @OneToMany(mappedBy = "article")
    Set<TopArticleCustomer> topArticleCustomer;
}
