package io.github.emfsilva.services;

import java.util.List;

import io.github.emfsilva.data.model.Book;
import io.github.emfsilva.data.vo.v1.BookDTO;
import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.exception.ResourceNotFoundException;
import io.github.emfsilva.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.emfsilva.converter.DozerConverter;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServices {
	
	@Autowired
    BookRepository repository;
		
	public BookDTO create(BookDTO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return vo;
	}
	
	public Page<BookDTO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::converterToBookDTO);
	}

	private BookDTO converterToBookDTO(Book book) {
		return DozerConverter.parseObject(book, BookDTO.class);
	}

	public BookDTO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookDTO.class);
	}
		
	public BookDTO update(BookDTO book) {
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return vo;
	}

	@Transactional
	public PersonDTO disableBooks(Long id) {
		repository.disableBooks(id);
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonDTO.class);
	}

	@Transactional
	public PersonDTO enableBooks(Long id) {
		repository.enableBooks(id);
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonDTO.class);
	}
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
