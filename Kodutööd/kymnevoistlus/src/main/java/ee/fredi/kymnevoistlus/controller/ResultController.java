package ee.fredi.kymnevoistlus.controller;

import ee.fredi.kymnevoistlus.entity.Result;
import ee.fredi.kymnevoistlus.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Resultkontrollerist peaks saama vaadata, muuta ja lisada ja kustutada tulemusi
@RestController
public class ResultController {
    @Autowired
    ResultRepository resultRepository;

    @GetMapping("result")
    public List<Result> getResults()
    {
        return resultRepository.findAll();
    }


    //lisada saab id, points, contestant. NB !!!!
    //NB!!! Points tähendab APIS sisestada mis kohale spordialas sportlane tuli, punktid arvutab süsteem automaagiliselt.
    @PostMapping("result")
    public List<Result> addResult(@RequestBody Result result){

        if (result.getId() != null) {
            throw new RuntimeException("ERROR_RESULT_ID_MUST_BE_NULL");
        }
        if(result.getPoints() == null || result.getPoints() < 0 ){
            throw new RuntimeException("ERROR_UNREALISTICALLY_BAD_RESULT");
        }
        //postsioonid suuremad kui 10 saavad 0 punkti.
        if(result.getPoints() > 10 || result.getPoints() == 0) {
            result.setPoints(0L);
        }
        //positsioonid 10-5 saavad 1 punkti.
        if(result.getPoints() <= 10 && result.getPoints() > 5 ){
            result.setPoints(1L);
        }
        //esiviisik saavad 2, 2, 3, 5 ja 10 punkti.
        if(result.getPoints() <= 5 && result.getPoints() >= 1){
            long roundedResult = Math.round(10/result.getPoints());
            result.setPoints(roundedResult);
        }

        if (result.getContestant() == null || result.getContestant().getId() == null){
            throw new RuntimeException("ERROR_CONTESTANT_NOT_REFERENCED");
        }

        /*    //teeme asjad nii et saab esitada ainult kindlaid spordialasid.
        if (result.getSport() != "kivipall" ||
            result.getSport() != "noolejooks" ||
            result.getSport() != "kastimaraton" ||
            result.getSport() != "katilõikamine" ||
            result.getSport() != "laskesurfimine") {
            throw new RuntimeException("ERROR_SPORT_DOES_NOT_EXIST");
        }*/




        resultRepository.save(result);
        return resultRepository.findAll();
    }

    @DeleteMapping("result/{id}")
    public List<Result> deleteResult(@PathVariable Long id){
        resultRepository.deleteById(id);
        return resultRepository.findAll();
    }


    //
    @PutMapping("result")
    public  List<Result> editResult(@RequestParam Result result){
        if(result.getId() == null){
            throw new RuntimeException("ERROR_NO_ID_SPECIFIED");
        }
        if(result.getPoints() <= 0) {
            throw new RuntimeException("ERROR_POINTS_NEGATIVE");
        }
        resultRepository.save(result);
        return resultRepository.findAll();
    }

}