package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityDAO extends JpaRepository<City,Integer> {

    Optional<City> findByPostalCode(String pc);
    Optional<City> findByName(String name);
    Optional<City> findByNameAndPostalCode(String name, String pc);
    Optional<List<City>> findAllByName(String ville);
}
