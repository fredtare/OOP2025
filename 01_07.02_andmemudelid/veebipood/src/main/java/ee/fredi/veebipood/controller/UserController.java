package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.WebshopOrder;
import ee.fredi.veebipood.entity.WebshopUser;
import ee.fredi.veebipood.repository.OrderRepository;
import ee.fredi.veebipood.repository.PersonRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //lubab API päringuid vastu võtta
public class UserController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("orders")
    public List<WebshopOrder> addOrder(@RequestBody WebshopOrder order) {
        orderRepository.save(order);
        return orderRepository.findAll();
    }

}
