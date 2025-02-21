package ee.fredi.veebipood.exception;

import lombok.Data;

import java.util.Date; //alati javauntil date importida

@Data //tema sees on getter, setter, noargsconstructor
public class ErrorMessage {

    private String message;
    private Date timestamp;
    private int status;
}
