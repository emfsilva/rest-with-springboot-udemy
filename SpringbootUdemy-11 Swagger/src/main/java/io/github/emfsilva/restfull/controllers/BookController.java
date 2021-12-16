package io.github.emfsilva.restfull.controllers;

import io.github.emfsilva.restfull.data.dto.v1.BookDTO;
import io.github.emfsilva.restfull.data.model.Book;
import io.github.emfsilva.restfull.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookDTO> findAll() {
        List<BookDTO> books = service.findAll();
        books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
        return books;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO findById(@PathVariable("id") Long id) {
        BookDTO bookDTO = service.findById(id);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO create(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.create(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return  bookDTO;
    }

    @PutMapping(produces = {"application/json", "application/xml","application/x-yaml"},
            consumes = {"application/json", "application/xml","application/x-yaml"})
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.update(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return  bookDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();

    }



}
