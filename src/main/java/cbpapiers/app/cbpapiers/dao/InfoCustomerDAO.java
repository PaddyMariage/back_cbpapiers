package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.InfoCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoCustomerDAO extends JpaRepository<InfoCustomer,String> {
}
