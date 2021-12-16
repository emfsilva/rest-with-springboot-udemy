package io.github.emfsilva.restfull.services;

import io.github.emfsilva.restfull.adapter.DozerConverter;
import io.github.emfsilva.restfull.data.dto.v1.BookDTO;
import io.github.emfsilva.restfull.data.model.Book;
import io.github.emfsilva.restfull.exception.ResourceNotFoundException;
import io.github.emfsilva.restfull.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO create(BookDTO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        var dto = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
        return dto;
    }

    public List<BookDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookDTO.class);
    }

    public BookDTO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return  DozerConverter.parseObject(entity, BookDTO.class);
    }

    public BookDTO update(BookDTO book) {
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
        return dto;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
