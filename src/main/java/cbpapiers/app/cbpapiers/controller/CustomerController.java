package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.dao.InfoCustomerDAO;
import cbpapiers.app.cbpapiers.dao.RoleDAO;
import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.InfoCustomer;
import cbpapiers.app.cbpapiers.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {
    private CustomerDAO customerDAO;
    private InfoCustomerDAO infoCustomerDAO;
    private RoleDAO roleDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO, InfoCustomerDAO infoCustomerDAO, RoleDAO roleDAO) {
        this.customerDAO = customerDAO;
        this.infoCustomerDAO = infoCustomerDAO;
        this.roleDAO = roleDAO;
    }

    // for all users
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return customerDAO.findById(id).orElse(null);
    }

    // for admin
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    // it doesn't really delete the customer but makes it inactive so we can keep the order records
    @DeleteMapping("/customers/{id}")
    public ResponseEntity setInactiveCustomer(@PathVariable String id){
        Role role = roleDAO.findByRole("inactive");
        InfoCustomer infoCustomer = infoCustomerDAO.findById(id).orElse(null);
        if(infoCustomer != null) {
            infoCustomer.setRole(role);
            infoCustomerDAO.save(infoCustomer);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("There is no customer with id : " + id);
    }
}
