package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.ArticleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDetailsDAO extends JpaRepository<ArticleDetails,Integer> {
}
