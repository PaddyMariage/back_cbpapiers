package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopArticleCustomerDAO extends JpaRepository<TopArticleCustomer, TopArticleCustomerPK> {
    List<TopArticleCustomer> findAllByCustomer(Customer customer);
    List<TopArticleCustomer> findAllByCustomerOrderByPosition(Customer customer);
}
