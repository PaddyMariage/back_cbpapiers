package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.dao.OrderLineDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.Order;
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
//        Customer customer = customerDAO.findById(idCustomer).orElseThrow(()->new NotFoundException(idCustomer, Customer.class));
//        return orderDao.findAllByCustomer(customer);
        return orderDao.findAllByCustomerId(idCustomer);
    }

    // retrieve one specific order with a number order
    @GetMapping(value = "/{idOrder}")
    @JsonView(MyJsonView.OrderDetails.class)
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
