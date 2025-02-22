package ee.fredi.veebipood.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter //encapsulation, peidab sensitiivset datat vöörate silmade eest
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class WebshopUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //int

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
