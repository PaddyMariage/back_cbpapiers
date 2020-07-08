package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.NotFoundException;
import cbpapiers.app.cbpapiers.dao.ArticleDAO;
import cbpapiers.app.cbpapiers.dao.OrderDAO;
import cbpapiers.app.cbpapiers.model.Article;
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

    @Autowired
    public OrderController(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDao.findAll();
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

    @PatchMapping(value = "/{id}")
    public ResponseEntity updateOrder(@PathVariable String id, @RequestBody Order order) {
        Order orderdb = orderDao.findById(id)
                .orElseThrow(()->new NotFoundException(id, Order.class));
        if(orderdb!=null){
            orderDao.save(orderdb);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
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
