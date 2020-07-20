package cbpapiers.app.cbpapiers.dao;

import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineDAO extends JpaRepository<OrderLine, OrderLinePK> {

    List<OrderLine> findAllByOrder(Order order);

    @Query(nativeQuery = true, value = "delete from F_DOCLIGNE where AR_Ref = :idArticle and DO_PIECE = :idOrder")
    void remove(@Param("idArticle") String idArticle, @Param("idOrder") String idOrder);
}
