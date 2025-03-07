package ee.frediTarenomm.hulknurk.controller;

import ee.frediTarenomm.hulknurk.entity.Coordinates;
import ee.frediTarenomm.hulknurk.repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CoordinatesController {
    @Autowired
    CoordinatesRepository coordinatesRepository;

    @GetMapping("coordinates")
        public List<Coordinates> getCoordinates(){
            return coordinatesRepository.findAll();
        }

    @PostMapping("coordinates")
        public List<Coordinates> addCoordinates(@RequestBody Coordinates coordinates){
        coordinatesRepository.save(coordinates);
        return coordinatesRepository.findAll();
    }

    @GetMapping("circumference")
    public double getCircumference(){
        double circumference = 0;
        List<Coordinates> coord = coordinatesRepository.findAll();

        for (int i = 0; i < coord.size() - 1; i++){
            long x1 = coord.get(i).getCoordX();
            long x2 = coord.get(i+1).getCoordX();
            long y1 = coord.get(i).getCoordY();
            long y2 = coord.get(i+1).getCoordY();

            circumference = circumference + Math.sqrt((x2-x1)^2 + (y2 -y1)^2);
        }
    return circumference;
    }

    //PATCH localhost:8080/increase?addx=v''rtus&addy=vaartus
    @PatchMapping("increase")
    public List<Coordinates> editCoordinates(@RequestParam long addx, long addy){

        for (Coordinates coord: coordinatesRepository.findAll()){
            coord.setCoordY(coord.getCoordY() + addy);
            coord.setCoordX(coord.getCoordX() + addx);
        }
    return coordinatesRepository.findAll();
    }
}
