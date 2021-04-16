package mk.ukim.finki.emt.lab.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{


    public CategoryNotFoundException(String categoryId) {
        super(String.format("Category with id: %s was not found", categoryId));
    }

}
