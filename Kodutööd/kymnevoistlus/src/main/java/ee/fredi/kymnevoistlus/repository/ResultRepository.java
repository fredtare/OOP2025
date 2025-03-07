package ee.fredi.kymnevoistlus.repository;

import ee.fredi.kymnevoistlus.entity.Contestant;
import ee.fredi.kymnevoistlus.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Contestant> contestantId(Long id);
}
