package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.ArticleDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.Article;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private ArticleDAO articleDao;

    @Autowired
    public ArticleController(ArticleDAO articleDAO) {
        this.articleDao = articleDAO;
    }

    @GetMapping
    @JsonView(MyJsonView.Article.class)
    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }

    @GetMapping(value = "/{id}")
    @JsonView(MyJsonView.Article.class)
    public Article getArticleById(@PathVariable String id){
        return articleDao.findById(id).orElseThrow(()-> new NotFoundException(id,Article.class));
    }
}
