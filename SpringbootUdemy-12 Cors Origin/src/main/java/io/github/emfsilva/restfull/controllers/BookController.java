package io.github.emfsilva.restfull.controllers;

import io.github.emfsilva.restfull.data.dto.v1.BookDTO;
import io.github.emfsilva.restfull.data.model.Book;
import io.github.emfsilva.restfull.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@CrossOrigin({"http://localhost:1001"})
@Api(value = "Book Endpoint", description = "Description for book", tags = {"Book Endpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @CrossOrigin({"http://localhost:1002", "https://emfsilva.gihub.io"})
    @ApiOperation(value = "Find all books record")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookDTO> findAll() {
        List<BookDTO> books = service.findAll();
        books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
        return books;
    }

    @ApiOperation(value = "Find just a book to ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO findById(@PathVariable("id") Long id) {
        BookDTO bookDTO = service.findById(id);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO create(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.create(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return  bookDTO;
    }

    @ApiOperation(value = "Update book")
    @PutMapping(produces = {"application/json", "application/xml","application/x-yaml"},
            consumes = {"application/json", "application/xml","application/x-yaml"})
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.update(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return  bookDTO;
    }

    @ApiOperation(value = "Delete book to ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();

    }



}
