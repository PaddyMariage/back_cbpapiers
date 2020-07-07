package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Discount;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, DiscountPK> {
}
