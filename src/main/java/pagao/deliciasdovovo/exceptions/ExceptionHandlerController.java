package pagao.deliciasdovovo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicatateEntry(DataIntegrityViolationException ex) {
        ExcpetionDto excpetionDto = new ExcpetionDto("Usuário já possui cadastro", "400");
        return ResponseEntity.badRequest().body(excpetionDto);
    }
}
