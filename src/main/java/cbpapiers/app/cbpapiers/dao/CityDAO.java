package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends JpaRepository<City,Integer> {
}
