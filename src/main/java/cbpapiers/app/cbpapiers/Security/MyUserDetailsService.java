package cbpapiers.app.cbpapiers.Security;

import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
     private CustomerDAO customerDAO;

    @Autowired
    public MyUserDetailsService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
        public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
            Customer customer = customerDAO.findById(id)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Inconnu : " + id));

            return new MyUserDetails(customer);
        }
    }

