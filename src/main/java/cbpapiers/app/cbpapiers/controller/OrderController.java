package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.model.Order;
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

    // retrieve the historic of orders from a customer
    @GetMapping("/{idCustomer}")
    public List<Order> getAllOrders(@PathVariable String idCustomer) {
        return orderDao.findAllByCustomer(idCustomer);
    }

    // vue 1 commande
    @GetMapping(value = "/{idOrder}")
    public Order getOrderById(@PathVariable String idOrder){
        return orderDao.findById(idOrder).orElseThrow(()-> new NotFoundException(idOrder,Order.class));
    }

    @PostMapping
    public @ResponseBody Order createAnOrder(@RequestBody Order order) {
        if(order!=null){
            return orderDao.save(order);
        } else{
            return null;
        }
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
