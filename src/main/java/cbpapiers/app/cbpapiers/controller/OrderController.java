package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.OrderLine;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDao;
    private CustomerDAO customerDAO;
    private OrderLineDAO orderLineDAO;

    @Autowired
    public OrderController(OrderDAO orderDao, OrderLineDAO orderLineDAO, CustomerDAO customerDAO) {
        this.orderDao = orderDao;
        this.orderLineDAO = orderLineDAO;
        this.customerDAO = customerDAO;
    }

    // retrieve the history of orders from a customer
    @GetMapping("/history/{idCustomer}")
    @JsonView(MyJsonView.Order.class)
    public List<Order> getAllOrders(@PathVariable String idCustomer) {
        return orderDao.findAllByCustomerId(idCustomer);
    }

    // retrieve one specific order with a number order
    @GetMapping(value = "/{idOrder}")
    @JsonView(MyJsonView.OrderDetails.class)
    public Order getOrderById(@PathVariable String idOrder){
        return orderDao.findById(idOrder).orElseThrow(()-> new NotFoundException(idOrder,Order.class));
    }


    @PostMapping
    public @ResponseBody boolean createAnOrder(@RequestBody Order order) {
        if(order!=null){
            for (OrderLine orderLine : order.getOrderLines()) {
                OrderLinePK cle = new OrderLinePK();
                cle.setIdOrder(order.getOrderNumber());
                cle.setIdArticle(orderLine.getArticle().getReference());
                orderLine.setOrderLinePK(cle);
            }
            orderDao.save(order);
            return true;
        } else{
            return false;
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
