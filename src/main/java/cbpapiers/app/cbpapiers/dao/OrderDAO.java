package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order,Integer> {
}
