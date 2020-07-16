package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.CustomerPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDAO extends JpaRepository<CustomerPicture,Integer> {
}
