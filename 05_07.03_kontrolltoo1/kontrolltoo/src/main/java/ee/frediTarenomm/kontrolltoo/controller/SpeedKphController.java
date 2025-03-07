package ee.frediTarenomm.kontrolltoo.controller;

import ee.frediTarenomm.kontrolltoo.entity.SpeedKph;
import ee.frediTarenomm.kontrolltoo.entity.SpeedMph;
import ee.frediTarenomm.kontrolltoo.repository.SpeedKphRepository;
import ee.frediTarenomm.kontrolltoo.repository.SpeedMphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpeedKphController {
    @Autowired
    SpeedKphRepository kphRepository;

    @Autowired
    SpeedMphRepository mphRepository;

    @GetMapping("speeds")
    public List<SpeedKph> showSpeeds(){
        return kphRepository.findAll();
    }

    @PostMapping("speeds")
    public List<SpeedKph> addSpeed(@RequestBody SpeedKph speedKph){
        if (speedKph.getId() != null) {
            throw new RuntimeException("ERROR_ID_MUST_BE_GENERATED_AUTOMATICALLY");
        }
        if (speedKph.getSpeed() == null){
            throw new RuntimeException("ERROR_NO_SPEED_DETECTED");
        }
        //error kuna meie kiiruseformaat on double
        if (!(speedKph.getSpeed() instanceof Long)) {
            throw new RuntimeException("ERROR_SPEED_MUST_BE_INT");
        }

        kphRepository.save(speedKph);
        return kphRepository.findAll();
    }

    //leiab keskmised nõnda, et käib kõik kiirused andmebaasitst läbi, liidab kokku ja jagab arvuga.
    @GetMapping("avarages")
    public double avarageSpeeds(){
        int i = 0;
        double avarage = 0d;
        for (SpeedKph speeds: kphRepository.findAll()) {

            //lisasin selle siia kuna tabelis on null. Soovi korral saab käima lükata.
            if (speeds.getSpeed() == null){
                //throw new RuntimeException("ERROR_NULL_VALUE_IN_TABLE");
            }

            else {
                i++;
                avarage = avarage + speeds.getSpeed();
            }
        }
        avarage = avarage / i;
        return avarage;
    }

//teeme uue listi kuhu saab paigutada kiirused mida me amerikaniseerime ja tagastame selle.
    @GetMapping("speedsMph")
    public List<Double> americanizeSpeeds(){
        double speedMph = 0d;
        List<SpeedKph> speeds = kphRepository.findAll();
        List<Double> speedsMph = new ArrayList<>();

        for (int i = 0; i < speeds.size(); i++){

            //vajalik, kuna andmebaasis on null ja ma ei viitsi deletemappingut teha (olgugi, et see oleks kiirem)
            if (speeds.get(i).getSpeed() == null) {}
            else {
                speedMph = speeds.get(i).getSpeed() * 1.609;
                speedsMph.add(speedMph);
            }
        }
    return speedsMph;
    }

    @GetMapping("moreSpeed")
    public List<SpeedKph> moreSpeed(){

        for (SpeedKph speedList: kphRepository.findAll()){
            if (speedList.getSpeed() == null) {
                speedList.setSpeed(1L);
                kphRepository.save(speedList);
            }
            else {
                speedList.setSpeed(speedList.getSpeed() + 1 );
                kphRepository.save(speedList);
            }
        }

        return kphRepository.findAll();
    }
    @GetMapping("moreSpeedMph")
    public List<SpeedMph> moreSpeedMph(){

        for (SpeedMph speedList: mphRepository.findAll()){
            if (speedList.getSpeed() == null) {
                speedList.setSpeed(1d);
                mphRepository.save(speedList);
            }
            else {
                speedList.setSpeed(speedList.getSpeed() + 1 );
                mphRepository.save(speedList);
            }
        }

        return mphRepository.findAll();
    }

//see võtab kphd ja pistab need MPHdeks ja siis salvestab MPH tabelisse.
    //selle jaoks pidi importima mph nii repository kui ka entity ja need salvestama.

    @GetMapping("makeMphTable")
    public List<SpeedMph> makeTable(){

        List<SpeedKph> kphSpeeds = kphRepository.findAll();

        for (SpeedKph kphSpeed : kphSpeeds){
            SpeedMph mphSpeed = new SpeedMph();
            mphSpeed.setSpeed(kphSpeed.getSpeed()* 0.621);
            mphRepository.save(mphSpeed);
        }
        return mphRepository.findAll();
    }
}
