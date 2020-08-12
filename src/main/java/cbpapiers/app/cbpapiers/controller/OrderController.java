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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.Set;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDao;
    private CustomerDAO customerDAO;
    private JavaMailSender emailSender;
    private OrderLineDAO orderLineDAO;

    @Autowired
    public OrderController(OrderDAO orderDao, OrderLineDAO orderLineDAO, CustomerDAO customerDAO, JavaMailSender emailSender, OrderLineDAO orderLineDAO1) {
        this.orderDao = orderDao;
        this.customerDAO = customerDAO;
        this.emailSender = emailSender;
        this.orderLineDAO = orderLineDAO1;
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
    public Order getOrderById(@PathVariable String idOrder) {
        return orderDao.findById(idOrder).orElseThrow(() -> new NotFoundException(idOrder, Order.class));
    }


    // @Adrien j'ai changé le RequestBody en ORDER regarde si ca parait ok pour toi ou sinon bah tu peux m'insulter^^
    @PostMapping
    @JsonView(MyJsonView.Order.class)
    public ResponseEntity<Order> createAnOrder(@RequestBody Order order) {
        try {
            orderDao.saveAndFlush(order);
            if (order.getOrderLines() != null) {
                for (OrderLine orderLine : order.getOrderLines()) {
                    OrderLinePK cle = new OrderLinePK();
                    cle.setIdOrder(order.getOrderNumber());
                    cle.setIdArticle(orderLine.getArticle().getReference());
                    orderLine.setOrderLinePK(cle);
                }
                orderDao.save(order);
            }
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

/*        try {
            //create pdf
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(String.format("REF_CLIENT %s - Commande %s.pdf", order.getCustomer().getId(), order.getOrderNumber())));

            document.open();

            Font font = FontFactory.getFont(FontFactory.defaultEncoding, 12, BaseColor.BLACK);
            Chunk chunk = new Chunk(String.format("Client %s - Commande %s", order.getCustomer().getId(), order.getOrderNumber()), font);
            Paragraph paragraph = new Paragraph(chunk);
            document.add(paragraph);

            chunk = new Chunk("\n\n", font);
            paragraph = new Paragraph(chunk);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            addTableHeader(table);
            addRows(table, order);

            document.add(table);

            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return false;
        }

        try {
            EmailService emailService = new EmailService(emailSender);
            String to = "adrien.fek@gmail.com";
            String subject = String.format("REF_CLIENT %s - Commande %s", order.getCustomer().getId(), order.getOrderNumber());
            String text = "yo, ça s'passe bien de ton côté ?";
            String path = String.format("REF_CLIENT %s - Commande %s.pdf", order.getCustomer().getId(), order.getOrderNumber());
            emailService.sendMessageWithAttachment(to, subject, text, path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @PostMapping("/edit")
    @JsonView(MyJsonView.Order.class)
    public ResponseEntity<Order> editAnOrder(@RequestBody Order order) {
        try {
            Order orderFromDB = orderDao.findById(order.getOrderNumber()).orElse(null);
            Set<OrderLine> orderLinesToDelete = orderFromDB.getOrderLines();

            order.getOrderLines().forEach(
                    orderLine -> {
                        OrderLinePK cle = new OrderLinePK();
                        cle.setIdOrder(order.getOrderNumber());
                        cle.setIdArticle(orderLine.getArticle().getReference());
                        orderLine.setOrderLinePK(cle);
                    }
            );
            orderFromDB.setOrderLines(order.getOrderLines());
            Order response = orderDao.save(orderFromDB);

            for (OrderLine orderLine : orderLinesToDelete) {
                for (OrderLine line : order.getOrderLines()) {
                    if (line.getArticle().getReference().equals(orderLine.getArticle().getReference())) {
                        orderLinesToDelete.remove(orderLine);
                        break;
                    }
                }
            }

            for (OrderLine orderLine : orderLinesToDelete)
                orderLineDAO.remove(orderLine.getArticle().getReference(), orderLine.getOrder().getOrderNumber());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Référence Article", "Description Article", "Quantité")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    Font font = new Font(FontFactory.getFont(FontFactory.defaultEncoding, 12, Font.BOLD));
                    header.setPhrase(new Phrase(columnTitle, font));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Order order) {
        order.getOrderLines().forEach(
                (orderLine -> {
                    table.addCell(orderLine.getArticle().getReference());
                    table.addCell(orderLine.getArticle().getLabel());
                    table.addCell(Integer.toString(orderLine.getQuantity()));
                })
        );
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrder(@PathVariable String id) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Order.class));
        if (order != null) {
            orderDao.delete(order);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
