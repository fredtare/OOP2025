package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.WebshopOrder;
import ee.fredi.veebipood.entity.WebshopUser;
import ee.fredi.veebipood.repository.OrderRepository;
import ee.fredi.veebipood.repository.PersonRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    //peab saatma id ja parooli
    @PostMapping("login")
    public boolean login(@RequestBody WebshopUser user) {
        WebshopUser dbPerson = personRepository.findById(user.getId()).orElseThrow();

        if (dbPerson.getPassword().equals(user.getPassword())){
            return true;
        }else{ return false; }
    }

    @PostMapping("signup")
    public List<WebshopUser> signup(@RequestBody WebshopUser user) {

        if(user.getEmail() == null || user.getEmail().isBlank()){
            throw new RuntimeException("ERROR_MISSING_EMAIL");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new RuntimeException("ERROR_PASSWORD_MISSING");
        }
        personRepository.save(user);
        return personRepository.findAll();

    }
}
