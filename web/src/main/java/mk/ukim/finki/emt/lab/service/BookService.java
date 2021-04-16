package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.model.enumerations.CategoryEnumStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, CategoryEnumStatus category, Long authorId
            ,Integer availableCopies);

    Optional<Book> edit(String name, CategoryEnumStatus category, Long authorId
            ,Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    void save(Book book);


    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

//    void takeBook(Long id);
}
