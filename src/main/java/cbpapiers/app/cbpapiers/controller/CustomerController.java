package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.Security.JwtUtil;
import cbpapiers.app.cbpapiers.Security.MyUserDetails;
import cbpapiers.app.cbpapiers.Security.MyUserDetailsService;
import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.Customer;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDAO customerDAO;

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(CustomerDAO customerDAO,
                              AuthenticationManager authenticationManager,
                              MyUserDetailsService userDetailsService,
                              JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
    }


    // retrieve one specific customer
    @GetMapping("/{id}")
    @JsonView(MyJsonView.Customer.class)
    public Customer getCustomer(@PathVariable String id) {
        return customerDAO.findById(id).orElse(null);
    }

    // retrieve all customers
    @GetMapping
    @JsonView(MyJsonView.Customer.class)
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

    @PostMapping
    public ResponseEntity<String> createACustomer(@RequestBody Customer customer) {
        if (customer != null) {
            customer.setActive(true);
            if(customerDAO.findById(customer.getId()).isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer existe déjà");
            }
            if(customer.getPassword()!=null) {
                customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            }

            customerDAO.save(customer);
            return ResponseEntity.ok(customer.getId());
        } else {
            return null;
        }
    }

    @PostMapping("/createPassword/{idCustomer}")
    public ResponseEntity createPassword(@RequestBody Customer customer, @PathVariable String idCustomer) {
        Customer customerToUpdate = customerDAO.findById(idCustomer).orElse(null);
        if(customerToUpdate!=null){
            customerToUpdate.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerDAO.save(customerToUpdate);
            return ResponseEntity.ok(customer);
        } else
            return ResponseEntity.badRequest().body("There is no customer with id : " + idCustomer);
    }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody Customer customer) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            customer.getId(), customer.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Identifiant ou mot de passe incorrect", e);
        }
        final MyUserDetails userDetails = (MyUserDetails) userDetailsService
                .loadUserByUsername(customer.getId());
        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }
}
