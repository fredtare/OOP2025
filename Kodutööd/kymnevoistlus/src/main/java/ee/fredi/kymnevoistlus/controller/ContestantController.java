package ee.fredi.kymnevoistlus.controller;

import ee.fredi.kymnevoistlus.entity.Contestant;
import ee.fredi.kymnevoistlus.repository.ContestantRepository;
import ee.fredi.kymnevoistlus.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//contestantcontroller peab saama lisada ja muutma ja kustutama võistlejaid riike ja vanuseid.

@RestController
public class ContestantController {
    @Autowired
    ContestantRepository contestantRepository;
    @Autowired
    ResultRepository resultRepository;

    @GetMapping("contestant")
    public List<Contestant> getContestants()
    {
        return contestantRepository.findAll();
    }

    @PostMapping("contestant")
    public List<Contestant> addContestant(@RequestBody Contestant contestant){
        if (contestant.getId() != null) {
            throw new RuntimeException("ERROR_CONTESTANT_ID_MUST_BE_NULL");
        }
        contestantRepository.save(contestant);
        return contestantRepository.findAll();
    }

    @DeleteMapping("contestant/{id}")
    public List<Contestant> deleteContestant(@PathVariable Long id){
        contestantRepository.deleteById(id);
        return contestantRepository.findAll();
    }


    @GetMapping("contestant/{id}")
    public List<Contestant> getContestantWithPoints(@PathVariable Long id) {
        return contestantRepository.findById(id);
        return resultRepository.findAllById(id);
    }


    //
    @PutMapping("contestant")
    public  List<Contestant> editContestant(@RequestParam Contestant contestant){
        if(contestant.getId() == null){
            throw new RuntimeException("ERROR_NO_ID_SPECIFIED");
        }
        if(contestant.getAge() <= 0) {
            throw new RuntimeException("ERROR_CONTESTANT_AGE_NEGATIVE");
        }
        contestantRepository.save(contestant);
        return contestantRepository.findAll();
    }

    //PATCH localhost:8080/contestant?id=SINUVÄÄRTUS&field=SINUVÄÄRTUS&value=SINUVÄÄRTUS
    @PatchMapping("contestant")
    //see võtab URLIST id, fieldi ja value ja parseb selle ära
    public List<Contestant> editContestantValue(@RequestParam Long id, String field, String value){
        if (id == null){
            throw new RuntimeException("ERROR_NO_ID_SPECIFIED");
        }
        Contestant contestant = contestantRepository.findById(id).orElseThrow();
        switch (field) {
            case "name" -> contestant.setName(value);
            case "country" -> contestant.setCountry(value);
            case "age" -> {
                if (Long.parseLong(value) <= 0) {
                    throw new RuntimeException("ERROR_CONTESTANT_UNREALISTICALLY_YOUNG");
                }
                contestant.setAge(Long.parseLong(value));
            }

            default -> throw new RuntimeException("ERROR_INVALID_FIELD: " + field);
        }
        contestantRepository.save(contestant);
        return contestantRepository.findAll();
    }
}