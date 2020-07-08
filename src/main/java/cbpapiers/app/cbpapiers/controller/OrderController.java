package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDao;
    private OrderLineDAO orderLineDAO;

    @Autowired
    public OrderController(OrderDAO orderDao, OrderLineDAO orderLineDAO) {
        this.orderDao = orderDao;
        this.orderLineDAO = orderLineDAO;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDao.findAllByCustomer();
    }

    @GetMapping(value = "/{id}")
    public Order getArticleById(@PathVariable String id){
        return orderDao.findById(id).orElseThrow(()-> new NotFoundException(id,Order.class));
    }

    @PostMapping
    public @ResponseBody Order createAnOrder(@RequestBody Order order) {
        if(order!=null){
            return orderDao.save(order);
        } else{
            return null;
        }
    }

    // todo maybe switch to OrderDao.save(order)
    @PatchMapping
    public ResponseEntity updateOrder(@RequestBody List<OrderLine> orderLines) {
        if(orderLines != null && orderLines.size() != 0){
            String idOrder = orderLines.get(0).getOrder().getOrderNumber();
            Order orderDB = orderDao.findById(idOrder)
                    .orElseThrow(()->new NotFoundException(idOrder, Order.class));
            if(orderDB!=null){
                orderLines.forEach(orderLine -> orderLineDAO.save(orderLine));
                return ResponseEntity.ok().build();
            } else{
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrder(@PathVariable String id) {
        Order order = orderDao.findById(id)
                .orElseThrow(()->new NotFoundException(id, Order.class));

        if(order!=null){
            orderDao.delete(order);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
