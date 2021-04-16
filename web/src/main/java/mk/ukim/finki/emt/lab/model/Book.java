package mk.ukim.finki.emt.lab.model;

import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.CategoryEnumStatus;

import javax.persistence.*;
import java.util.Optional;


@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private CategoryEnumStatus category=CategoryEnumStatus.HISTORY;

    @ManyToOne
    private Author author;



    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, CategoryEnumStatus category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }


}
