package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Article;
import cbpapiers.app.cbpapiers.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDAO extends JpaRepository<Order,String> {
    List<Order> findAllByCustomer(String idCustomer);
}
