package cbpapiers.app.cbpapiers.controler;

import cbpapiers.app.cbpapiers.dao.ArticleDAO;
import cbpapiers.app.cbpapiers.model.Article;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ArticleController {
    ArticleDAO articleDao;

    @Autowired
    public ArticleController(ArticleDAO articleDAO) {
        this.articleDao = articleDAO;
    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleDao.findAll();
    }
}