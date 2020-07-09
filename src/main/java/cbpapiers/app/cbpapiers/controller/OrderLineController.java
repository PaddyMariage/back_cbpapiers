package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orderlines")
public class OrderLineController {

    private OrderLineDAO orderLineDAO;
    private OrderDAO orderDAO;

    public OrderLineController(OrderLineDAO orderLineDAO, OrderDAO orderDAO) {
        this.orderLineDAO = orderLineDAO;
        this.orderDAO = orderDAO;
    }

    @DeleteMapping(value = "/{idOrder}/{idArticle}")
    public ResponseEntity deleteArticleOrder(@PathVariable String idOrder, @PathVariable String idArticle) {
        OrderLinePK orderLinePK = new OrderLinePK();
        orderLinePK.setIdArticle(idArticle);
        orderLinePK.setIdOrder(idOrder);

        OrderLine orderLine = orderLineDAO.findById(orderLinePK).orElse(null);
        if(orderLine!= null){
            orderLineDAO.delete(orderLine);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // updates orderlines for a single order
    @PatchMapping
    public ResponseEntity updateOrder(@RequestBody List<OrderLine> orderLines) {
        if(orderLines != null && orderLines.size() != 0){
                orderLines.forEach(orderLine -> orderLineDAO.save(orderLine));
                return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
