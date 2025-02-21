package ee.fredi.veebipood.repository;

import ee.fredi.veebipood.entity.WebshopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<WebshopUser, Long> {
}
