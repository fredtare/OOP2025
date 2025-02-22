package ee.fredi.veebipood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice//autmaagiga käib üle kõikide kontrollerite ja handlib veateateiud
public class ExceptionCatcher {
    //TODO: Selgita responseentity

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {

        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setTimestamp(new Date());
        error.setStatus(400);
        return ResponseEntity.badRequest().body(error);

    }
}
