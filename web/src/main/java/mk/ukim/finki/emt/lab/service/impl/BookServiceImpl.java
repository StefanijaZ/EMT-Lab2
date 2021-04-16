package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.model.enumerations.CategoryEnumStatus;
import mk.ukim.finki.emt.lab.model.events.BookCreatedEvent;
import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundId;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, CategoryEnumStatus
            category, Long authorId, Integer availableCopies) {


//        CategoryEnumStatus categoryEnumStatus = this.categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        //this.refreshMaterializedView();
//        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(String name, CategoryEnumStatus category,
                               Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findByName(name).orElseThrow(() -> new BookNotFoundException(name));
        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

//        CategoryEnumStatus categoryEnumStatus = this.categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
//        book.setCategory(categoryEnumStatus);
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {

//        Category category = this.categoryRepository.findById(productDto.getCategory())
//                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundId(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }
}
