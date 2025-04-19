package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.Product;
import ee.fredi.veebipood.entity.WebshopOrder;
import ee.fredi.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("orders")
    public List<WebshopOrder> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("orders")//see on api otspunkti nimetus lihtsalt suvaline.
    public List<WebshopOrder> addOrder(@RequestBody WebshopOrder order) {
        order.setCreated(new Date());
        double sum = 0; //tüüp nimetus ja mis on väärtus
        for (Product p: order.getProducts()) {
            sum = sum + p.getPrice();

        }
        order.setTotalSum(sum);
        orderRepository.save(order);
        return orderRepository.findAll();
    }
}
