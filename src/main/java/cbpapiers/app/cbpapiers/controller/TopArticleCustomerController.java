package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.dao.CustomerDAO;
import cbpapiers.app.cbpapiers.dao.DiscountDAO;
import cbpapiers.app.cbpapiers.dao.TopArticleCustomerDAO;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.Article;
import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.Discount;
import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import com.fasterxml.jackson.annotation.JsonView;
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
@RequestMapping("/toparticles/customer")
public class TopArticleCustomerController {

    private TopArticleCustomerDAO topArticleCustomerDAO;
    private CustomerDAO customerDAO;
    private DiscountDAO discountDAO;

    @Autowired
    public TopArticleCustomerController(TopArticleCustomerDAO topArticleCustomerDAO, CustomerDAO customerDAO, DiscountDAO discountDAO) {
        this.topArticleCustomerDAO = topArticleCustomerDAO;
        this.customerDAO = customerDAO;
        this.discountDAO = discountDAO;
    }

    // get top article for a customer
    @GetMapping("/{idCustomer}")
    @JsonView(MyJsonView.TopArticleCustomer.class)
    public List<TopArticleCustomer> getTopArticleCustomer(@PathVariable String idCustomer){
        Customer customer = customerDAO.findById(idCustomer).orElse(null);
        if(customer!= null) {

            List<TopArticleCustomer> topArticleCustomerList = topArticleCustomerDAO.findAllByCustomerOrderByPosition(customer);
            topArticleCustomerList.forEach(

                    topArticle -> {
                        DiscountPK discountKey = new DiscountPK();
                        discountKey.setIdArticle(topArticle.getArticle().getReference());
                        discountKey.setIdCustomer(topArticle.getCustomer().getId());
                        Discount discountInfos = discountDAO.findById(discountKey).orElse(null);

                        if (discountInfos != null) {
                            double discount = discountInfos.getDiscount();
                            double clientPrice = discountInfos.getClientPrice();
                            double finalPrice;

                            if (discount != 0 && clientPrice != 0) {
                                //pourquoi diviser par 100 ? remise en % ?
                                finalPrice = clientPrice * (1 - discount / 100);
                                topArticle.getArticle().setUnitPrice(finalPrice);

                            } else if (discount == 0 && clientPrice != 0) {
                                topArticle.getArticle().setUnitPrice(clientPrice);

                            } else if ( discount != 0 && clientPrice == 0) {
                                finalPrice = topArticle.getArticle().getUnitPrice() * (1 - discount / 100);
                                topArticle.getArticle().setUnitPrice(finalPrice);

                            } else {
                                topArticle.getArticle().setUnitPrice(topArticle.getArticle().getUnitPrice());
                            }
                        } else {
                            topArticle.getArticle().setUnitPrice(topArticle.getArticle().getUnitPrice());
                        }
                    }
            );
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
