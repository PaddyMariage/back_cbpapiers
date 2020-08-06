package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Doclignes {

    @JsonView(MyJsonView.Article.class)
    String AR_Ref;

    @JsonView(MyJsonView.Article.class)
    String DL_PrixUnitaire;
    @JsonView(MyJsonView.Article.class)
    String CT_Num;

}
