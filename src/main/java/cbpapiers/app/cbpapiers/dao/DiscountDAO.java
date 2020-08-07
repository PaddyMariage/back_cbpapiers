package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Discount;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, DiscountPK> {
    Optional<List<Discount>> findAllByCustomerId(String customerId);
}
