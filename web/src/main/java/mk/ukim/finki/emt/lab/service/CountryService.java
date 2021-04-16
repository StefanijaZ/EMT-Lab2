package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);

}
