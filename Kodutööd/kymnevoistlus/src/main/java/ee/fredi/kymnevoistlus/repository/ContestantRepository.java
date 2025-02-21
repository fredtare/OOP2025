package ee.fredi.kymnevoistlus.repository;

import ee.fredi.kymnevoistlus.entity.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestantRepository extends JpaRepository<Contestant, Long> {
}
