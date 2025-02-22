package ee.fredi.kymnevoistlus.controller;

import ee.fredi.kymnevoistlus.entity.Contestant;
import ee.fredi.kymnevoistlus.repository.ContestantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//contestantcontroller peab saama lisada ja muutma ja kustutama võistlejaid riike ja vanuseid.

@RestController
public class ContestantController {
    @Autowired
    ContestantRepository contestantRepository;

    @GetMapping("Contestants")
    public List<Contestant> getContestants(){
        return contestantRepository.findAll();
    }

    @PostMapping("Contestant")
    public List<Contestant> addContestant(@RequestBody Contestant contestant){
        if (contestant.getId() != null) {
            throw new RuntimeException("ERROR_CONTESTANT_ID_NULL");
        }
        contestantRepository.save(contestant);
        return contestantRepository.findAll();
    }
}