package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDAO extends JpaRepository<Picture,Integer> {
}
