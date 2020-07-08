package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleDAO extends JpaRepository<Article,Integer> {
    //surcharge du findById pour un id en String
    Optional<Article> findById(String id);
}
