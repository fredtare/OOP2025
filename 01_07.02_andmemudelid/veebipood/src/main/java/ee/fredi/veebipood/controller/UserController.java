package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.WebshopOrder;
import ee.fredi.veebipood.entity.WebshopUser;
import ee.fredi.veebipood.repository.OrderRepository;
import ee.fredi.veebipood.repository.PersonRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController //lubab API päringuid vastu võtta
public class UserController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    private OrderRepository orderRepository;


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
