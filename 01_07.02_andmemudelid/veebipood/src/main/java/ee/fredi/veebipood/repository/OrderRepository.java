package ee.fredi.veebipood.repository;


import ee.fredi.veebipood.entity.WebshopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<WebshopOrder, Long> {
}
