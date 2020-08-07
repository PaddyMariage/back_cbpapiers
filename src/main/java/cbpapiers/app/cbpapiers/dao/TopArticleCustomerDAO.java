package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopArticleCustomerDAO extends JpaRepository<TopArticleCustomer, TopArticleCustomerPK> {
    Optional<List<TopArticleCustomer>> findAllByCustomer(Customer customer);
    Optional<List<TopArticleCustomer>> findAllByCustomerOrderByPosition(Customer customer);
}
