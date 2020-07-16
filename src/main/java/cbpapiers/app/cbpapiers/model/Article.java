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
@Table(name = "F_ARTICLE")
public class Article {

    @Id
    @Column(name = "AR_Ref")
    private String reference;

    @Column(name = "AR_PrixVen")
    private double unitPrice;

    @Column(name = "AR_Design")
    private String label;

    @Column(name = "FA_CodeFamille")
    private String family;

    @Transient
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
    Set<Discount> discount;


}
