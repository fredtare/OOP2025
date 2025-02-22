package ee.fredi.veebipood.entity;

//Hibernate
//automaatselt tekib andmebaasi tabel mis on klassi nimega

// file settings plugins jpa buddy install
//boolean
//String
//char
//int -> 2.1 miljardit
//short -> 128 numbrit, üle läheb hakkab negatiivist otsast peale
//byte -> 32 numbrit
//long on suurem int

//float 8 komakohta
//double 16 komakohta

//public ei vaja getterit ja setterit
//private vajab pöördumiseks setterit
//best praktis teha alati getterid setterid

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter //encapsulation, peidab sensitiivset datat vöörate silmade eest
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class Product { //see loob automaagiliselt samanimelise andmebaasi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //int

    private String name;
    private double price;
    private String image;
    private boolean active;

    @ManyToOne
    private Category category;

    //category seose valik andmebaasi sidumisteks: @ManyToMany, @ManyToOne, @OneToMany, @OneToOne
    //seoste valikus vasakpool on kas saab taaskasutada ja parempool Tost on kas list<> või ainsus.


}//lõpulaks
