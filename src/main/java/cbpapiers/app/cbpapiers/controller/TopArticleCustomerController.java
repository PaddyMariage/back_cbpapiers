package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.dao.TopArticleCustomerDAO;
import cbpapiers.app.cbpapiers.model.Article;
import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/top/customer")
public class TopArticleCustomerController {

    private TopArticleCustomerDAO topArticleCustomerDAO;
    private CustomerDAO customerDAO;

    @Autowired
    public TopArticleCustomerController(TopArticleCustomerDAO topArticleCustomerDAO, CustomerDAO customerDAO) {
        this.topArticleCustomerDAO = topArticleCustomerDAO;
        this.customerDAO = customerDAO;
    }

    // get top article for a customer
    @GetMapping("/{idCustomer}")
    public List<TopArticleCustomer> getTopArticleCustomer(@PathVariable String idCustomer){
        Customer customer = customerDAO.findById(idCustomer).orElse(null);
        if(customer!= null) {
            //List<Article> sortedArticleList = topArticleCustomerDAO.findByCustomer(customer);
            List<TopArticleCustomer> topArticleCustomerList = topArticleCustomerDAO.findAllByCustomer(customer);
            // sort accordingly to compareTo defined in TopArticleCustomer ( by asc position )
            /*
            Collections.sort(topArticleCustomerList);
            List<Article> sortedArticleList = topArticleCustomerList.stream()
                    .map(TopArticleCustomer::getArticle)
                    .collect(Collectors.toList());
             */
            return topArticleCustomerList;
        }
        return new ArrayList();
    }

    // add a TopArticle at a specific position
    @PutMapping
    public ResponseEntity addTopListArticle(@RequestBody TopArticleCustomer topArticleCustomer) throws URISyntaxException {
        if(topArticleCustomer != null) {
            List<TopArticleCustomer> topArticleCustomerList = topArticleCustomerDAO.findAllByCustomer(topArticleCustomer.getCustomer());
            if (topArticleCustomerList.size() != 0) {
                topArticleCustomerList.stream()
                        .filter(aTopArticleCustomer -> aTopArticleCustomer.getPosition() <= topArticleCustomer.getPosition())
                        .peek(top -> top.setPosition(top.getPosition()+1))
                        .forEach(top -> topArticleCustomerDAO.save(top));
                topArticleCustomerDAO.save(topArticleCustomer);
                // maybe not good
                return ResponseEntity.created(new URI("/top/customer/" + topArticleCustomer.getCustomer())).build();
            }else{
                // add
                topArticleCustomerDAO.save(topArticleCustomer);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity modifTopArticle(@RequestBody List<TopArticleCustomer> topArticleCustomer) throws URISyntaxException {
        if(topArticleCustomer != null && topArticleCustomer.size() != 0) {
            topArticleCustomer.forEach(top -> topArticleCustomerDAO.save(top));
            return ResponseEntity.created(new URI("/top/customer/" + topArticleCustomer.get(0).getCustomer())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idCustomer}/{idArticle}")
    public ResponseEntity deleteTopArticle(@PathVariable String idCustomer, @PathVariable String idArticle ){
        TopArticleCustomerPK topArticleCustomerPK = new TopArticleCustomerPK();
        topArticleCustomerPK.setIdArticle(idArticle);
        topArticleCustomerPK.setIdCustomer(idCustomer);

        TopArticleCustomer topArticleCustomer = topArticleCustomerDAO.findById(topArticleCustomerPK).orElse(null);
        if(topArticleCustomer != null){
            topArticleCustomerDAO.delete(topArticleCustomer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
