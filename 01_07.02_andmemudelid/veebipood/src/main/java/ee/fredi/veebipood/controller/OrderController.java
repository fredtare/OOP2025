package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.Product;
import ee.fredi.veebipood.entity.WebshopOrder;
import ee.fredi.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;


public class OrderController {

    @Autowired
    OrderRepository orderRepository;

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
