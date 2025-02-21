package ee.fredi.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity //tekitab sest autom. andmebaasi tabeli
//nii saab muuta tabeli nime erinevaks klassinimest @Table(name = "kategooria")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@ColumnDefault("false") parandab errori iniatsieelides falsiga
    private boolean active; //kui lisandub tagantjärgi bol siis ta läheb errorisse kuna ta üritab nulli panna booli

}
