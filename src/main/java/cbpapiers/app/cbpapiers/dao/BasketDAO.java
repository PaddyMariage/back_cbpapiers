package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketDAO extends JpaRepository<Basket,String> {
    List<Basket> findAllByCustomer(String idCustomer);
}

