package ee.frediTarenomm.libisevkeskmineproov.controller;

import ee.frediTarenomm.libisevkeskmineproov.entity.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ee.frediTarenomm.libisevkeskmineproov.repository.NumberRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumberController {
    @Autowired
    NumberRepository numberRepository;

    @GetMapping("number")
    public List<Number> getNumbers(){
        return numberRepository.findAll();
    }

    @PostMapping("number")
    public List<Number> addNumber(@RequestBody Number number){
        if (number.getId() != null) {
            throw new RuntimeException("ERROR_ID_MUST_BE_EMPTY");
        }
        numberRepository.save(number);
        return numberRepository.findAll();
    }
    @GetMapping("sum")
    public Long sumNumbers(){
        Long sum = 0L;
        for (Number individualNumber: numberRepository.findAll()){
            sum = sum + individualNumber.getValue();

        }
        return sum;
    }

    @GetMapping("avarage")
    public double avarageNumbers(){
        Long sum = 0L;
        Long numCount = 0L;
        for (Number individualNumber: numberRepository.findAll()){
            sum = sum + individualNumber.getValue();
            numCount = numCount + 1;
        }

        return sum / numCount;
    }

    @GetMapping("max")
    public Long maxNumbers(){
        Long max = 0L;
        for (Number individualNumber: numberRepository.findAll()){
            if (individualNumber.getValue() > max){
                max = individualNumber.getValue();
            }
        }
        return max;
    }

    @GetMapping("runningavarage")
    public List<Double> runningAvarage() {


        List<Number> numbers = numberRepository.findAll();
        List<Double > avarages = new ArrayList<>();

        for (int i = 0; i < numbers.size() - 2; i++) {
           Long currentNum = numbers.get(i).getValue();
           Long nextNum = numbers.get(i + 1).getValue();
           Long superNextNum = numbers.get(i + 2).getValue();
           double avarage = (currentNum + nextNum + superNextNum ) / 3;
            avarages.add(avarage);
        }
        return avarages;
    }
}
