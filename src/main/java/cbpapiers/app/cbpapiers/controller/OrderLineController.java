package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/{idOrder}")
    public ResponseEntity updateOrder(@PathVariable String idOrder,@RequestBody Order order) {
        if(order != null){
            order.getOrderLines().forEach(orderLine -> {
                OrderLinePK key = new OrderLinePK();
                key.setIdArticle(orderLine.getArticle().getReference());
                key.setIdOrder(idOrder);
                orderLine.setOrderLinePK(key);
                orderLine.setOrder(order);
                orderLineDAO.save(orderLine);
            });
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

//    // updates orderlines for a single order
//    @PatchMapping(value = "/{idOrder}")
//    public ResponseEntity updateOrder(@PathVariable String idOrder,@RequestBody List<OrderLine> orderLines) {
//        if(orderLines != null && orderLines.size() != 0){
//            orderLines.forEach(orderLine -> orderLineDAO.save(orderLine));
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

    // updates orderlines for a single order
//    @PatchMapping(value = "/{idOrder}")
//    public ResponseEntity updateOrder(@PathVariable String idOrder,@RequestBody List<OrderLine> orderLines) {
//            if(orderLines != null && orderLines.size() != 0){
//        orderLines.forEach(orderLine -> {
//            OrderLinePK key = new OrderLinePK();
//            key.setIdArticle(orderLine.getArticle().getReference());
//            key.setIdOrder(orderLine.getOrder().getOrderNumber());
//            orderLine.setOrderLinePK(key);
////            orderLine.setOrder(orderDAO.findById(idOrder).orElseThrow(()-> new NotFoundException(idOrder, Order.class)));
//            orderLineDAO.save(orderLine);
//        });
//        return ResponseEntity.ok().build();
//    }
//        return ResponseEntity.badRequest().build();
//}


}
