package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.enumerations.CategoryEnumStatus;

import java.util.List;
import java.util.Optional;


public interface AuthorService{
    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> findById(Long id);

    List<Author> findAll();

    void delete(String name);

    void delete(Long id);




}
