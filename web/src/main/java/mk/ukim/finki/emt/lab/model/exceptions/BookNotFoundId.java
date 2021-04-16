package mk.ukim.finki.emt.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundId extends RuntimeException {

    public BookNotFoundId(Long id) {
        super(String.format("Product with name: %d was not found", id));
    }
}