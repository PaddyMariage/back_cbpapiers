package cbpapiers.app.cbpapiers.controller;


import cbpapiers.app.cbpapiers.dao.*;
import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import cbpapiers.app.cbpapiers.model.*;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import cbpapiers.app.cbpapiers.model.pk.TopArticleCustomerPK;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/toparticles/customer")
public class TopArticleCustomerController {

    private TopArticleCustomerDAO topArticleCustomerDAO;
    private CustomerDAO customerDAO;
    private DiscountDAO discountDAO;
    private OrderDAO orderDAO;

    @Autowired
    public TopArticleCustomerController(TopArticleCustomerDAO topArticleCustomerDAO, CustomerDAO customerDAO, DiscountDAO discountDAO, OrderDAO orderDAO, ArticleDAO articleDAO) {
        this.topArticleCustomerDAO = topArticleCustomerDAO;
        this.customerDAO = customerDAO;
        this.discountDAO = discountDAO;
        this.orderDAO = orderDAO;
    }

    // get top article for a customer
    @GetMapping("/{idCustomer}")
    @JsonView(MyJsonView.TopArticleCustomer.class)
    public List<TopArticleCustomer> getTopArticleCustomer(@PathVariable String idCustomer) {
        Customer customer = customerDAO.findById(idCustomer.toUpperCase()).orElse(null);

        if (customer != null) {

            List<TopArticleCustomer> topArticlesCustomer = topArticleCustomerDAO.findAllByCustomerOrderByPosition(customer).orElse(null);

            if (topArticlesCustomer != null)
                return calculateFinalPrices(topArticlesCustomer);
            else {
                topArticlesCustomer = createTopArticles(customer.getId());
                topArticlesCustomer.forEach(
                        topArticle -> {
                            topArticle.setCustomer(customer);
                            TopArticleCustomerPK cle = new TopArticleCustomerPK();
                            cle.setIdArticle(topArticle.getArticle().getReference());
                            cle.setIdCustomer(customer.getId());
                            topArticle.setTopArticleCustomerPK(cle);

                            topArticleCustomerDAO.save(topArticle);
                        }
                );
                return calculateFinalPrices(topArticlesCustomer);
            }
        }
        return new ArrayList<>();
    }


    private List<TopArticleCustomer> calculateFinalPrices(List<TopArticleCustomer> topArticlesCustomer) {
        topArticlesCustomer.forEach(

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

                                finalPrice = Math.ceil(clientPrice * (1 - discount / 100) * 100) / 100;
                                topArticle.getArticle().setFinalPrice(finalPrice);

                            } else if (discount == 0 && clientPrice != 0) {
                                topArticle.getArticle().setFinalPrice(Math.ceil(clientPrice * 100) / 100 );

                            } else if (discount != 0 && clientPrice == 0) {
                                finalPrice = Math.ceil(topArticle.getArticle().getUnitPrice() * (1 - discount / 100) * 100) / 100;
                                topArticle.getArticle().setFinalPrice(finalPrice);

                            } else {
                                topArticle.getArticle().setFinalPrice(Math.ceil(topArticle.getArticle().getUnitPrice() * 100) / 100);
                            }
                        } else {
                            topArticle.getArticle().setFinalPrice(Math.ceil(topArticle.getArticle().getUnitPrice() * 100 ) / 100);
                        }
                    }
        );

        return topArticlesCustomer;
    }

    private List<TopArticleCustomer> createTopArticles(String id) {
        List<Order> orders = orderDAO.findAllByCustomerId(id);

        Map<Article, Integer> articleAndFrequency = new HashMap<>();

        for (Order order : orders) {
            for (OrderLine orderLine : order.getOrderLines()) {
                Article article = orderLine.getArticle();
                articleAndFrequency.merge(article, 1, Integer::sum);
            }
        }

        List<Map.Entry<Article, Integer>> sortedList = new ArrayList<>(articleAndFrequency.entrySet());
        Comparator<Map.Entry<Article, Integer>> valueComparator = (o1, o2) -> o2.getValue() - o1.getValue();
        sortedList.sort(valueComparator);

        List<TopArticleCustomer> topArticlesCustomer = new ArrayList<>();
        for (Map.Entry<Article, Integer> articleIntegerEntry : sortedList) {
            topArticlesCustomer.add(new TopArticleCustomer(articleIntegerEntry.getKey(),
                    sortedList.indexOf(articleIntegerEntry) + 1));
        }
        return topArticlesCustomer;
    }

    // add a TopArticle at a specific position
    @PutMapping
    public ResponseEntity addTopListArticle(@RequestBody TopArticleCustomer topArticleCustomer) throws
            URISyntaxException {
        if (topArticleCustomer != null) {
            List<TopArticleCustomer> topArticleCustomerList = topArticleCustomerDAO.findAllByCustomer(topArticleCustomer.getCustomer()).orElse(null);
            if (topArticleCustomerList.size() != 0) {
                topArticleCustomerList.stream()
                        .filter(aTopArticleCustomer -> aTopArticleCustomer.getPosition() <= topArticleCustomer.getPosition())
                        .peek(top -> top.setPosition(top.getPosition() + 1))
                        .forEach(top -> topArticleCustomerDAO.save(top));
                topArticleCustomerDAO.save(topArticleCustomer);
                // maybe not good
                return ResponseEntity.created(new URI("/top/customer/" + topArticleCustomer.getCustomer())).build();
            } else {
                // add
                topArticleCustomerDAO.save(topArticleCustomer);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity modifTopArticle(@RequestBody List<TopArticleCustomer> topArticleCustomer) throws
            URISyntaxException {
        if (topArticleCustomer != null && topArticleCustomer.size() != 0) {
            topArticleCustomer.forEach(top -> topArticleCustomerDAO.save(top));
            return ResponseEntity.created(new URI("/top/customer/" + topArticleCustomer.get(0).getCustomer())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idCustomer}/{idArticle}")
    public ResponseEntity deleteTopArticle(@PathVariable String idCustomer, @PathVariable String idArticle) {
        TopArticleCustomerPK topArticleCustomerPK = new TopArticleCustomerPK();
        topArticleCustomerPK.setIdArticle(idArticle);
        topArticleCustomerPK.setIdCustomer(idCustomer);

        TopArticleCustomer topArticleCustomer = topArticleCustomerDAO.findById(topArticleCustomerPK).orElse(null);
        if (topArticleCustomer != null) {
            topArticleCustomerDAO.delete(topArticleCustomer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
