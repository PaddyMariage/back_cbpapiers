package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.ArticleDetailsDAO;
import cbpapiers.app.cbpapiers.model.ArticleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ArticleDetails getArticleDetailsById(@PathVariable int id){
        return articleDetailsDao.findById(id).orElse(null);
    }
}
