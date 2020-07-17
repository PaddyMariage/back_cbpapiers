package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.dao.ArticleDetailsDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.ArticleDetails;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/articledetails")
public class ArticleDetailsController {
    private ArticleDetailsDAO articleDetailsDao;

    @Autowired
    public ArticleDetailsController(ArticleDetailsDAO articleDetailsDAO) {
        this.articleDetailsDao = articleDetailsDAO;
    }

    @GetMapping(value = "/{id}")
    @JsonView(MyJsonView.ArticleDetails.class)
    public ArticleDetails getArticleDetailsById(@PathVariable int id){
        return articleDetailsDao.findById(id).orElse(null);
    }
}
