package mk.ukim.finki.emt.lab.model.dto;


import lombok.Data;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.enumerations.CategoryEnumStatus;

@Data
public class BookDto {

    private String name;
    private CategoryEnumStatus category;
    private Long author;
    private Integer availableCopies;

    public BookDto() {
    }


    public BookDto(String name, CategoryEnumStatus category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
