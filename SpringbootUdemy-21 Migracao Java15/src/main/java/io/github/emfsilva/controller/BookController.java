package io.github.emfsilva.controller;

import io.github.emfsilva.data.vo.v1.BookDTO;
import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "BookEndpoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private final BookServices service;

    public BookController(BookServices service) {
        this.service = service;
    }

    @Operation(summary = "Find all books")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<CollectionModel<BookDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "author"));

        Page<BookDTO> books = service.findAll(pageable);
        books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
        return ResponseEntity.ok(CollectionModel.of(books));
    }

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO findById(@PathVariable("id") Long id) {
        BookDTO bookDTO = service.findById(id);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    @Operation(summary = "Create a new book")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO create(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.create(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    @Operation(summary = "Update a specific book")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.update(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    @Operation(summary = "Disable a specific person by your ID")
    @PatchMapping(value = "/disable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO disableBooks(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.disableBooks(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Enable a specific person by your ID")
    @PatchMapping(value = "/enable/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonDTO enableBooks(@PathVariable("id") Long id) {
        PersonDTO personDTO = service.enableBooks(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    @Operation(summary = "Delete a specific book by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}