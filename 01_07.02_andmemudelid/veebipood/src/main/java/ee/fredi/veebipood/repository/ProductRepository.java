package ee.fredi.veebipood.repository;

import ee.fredi.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; //!!! importi listis teine!!!!
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Repository tagastab ainult kas Product, List<Product>. JpaRepository< see sisu määrab>
    //sissekirjutatud päringu:
    //.findAll() SELECT * FROM
    //.save INSERT INTO
    //.deleteById()
    //.findById()
    Page<Product> findByCategory_Id(Long id, Pageable pageable);
}
