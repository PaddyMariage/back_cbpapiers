package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineDAO extends JpaRepository<OrderLine, OrderLinePK> {
    List<OrderLine> findAllByOrder(Order order);
}
