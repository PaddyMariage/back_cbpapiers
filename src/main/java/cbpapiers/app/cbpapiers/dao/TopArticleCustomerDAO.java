package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopArticleCustomerDAO extends JpaRepository<TopArticleCustomer, TopArticleCustomerPK> {
}