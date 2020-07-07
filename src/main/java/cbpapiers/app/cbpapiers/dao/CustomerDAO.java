package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {
}
