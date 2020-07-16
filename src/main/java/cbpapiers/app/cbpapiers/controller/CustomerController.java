package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return customerDAO.findById(id).orElse(null);
    }

    // retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    // it doesn't really delete the customer but makes it inactive so we can keep the order records
    @DeleteMapping("/{id}")
    public ResponseEntity setInactiveCustomer(@PathVariable String id) {
        Customer customer = customerDAO.findById(id).orElse(null);
        if (customer != null) {
            customer.setActive(false);
            customerDAO.save(customer);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("There is no customer with id : " + id);
    }
}
