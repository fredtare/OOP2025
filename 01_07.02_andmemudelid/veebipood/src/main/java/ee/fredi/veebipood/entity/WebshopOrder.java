package ee.fredi.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter //encapsulation, peidab sensitiivset datat vöörate silmade eest
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class WebshopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //int

    private String name;
    private Date created; //java.util.date importida
    @ManyToOne
    private WebshopUser webshopUser;
    @ManyToMany
    private List<Product> products;

    private double totalSum;
    //kuna pole mõtet iga kord iga requestiga summat arvutada.


}
