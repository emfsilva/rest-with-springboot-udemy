package io.github.emfsilva.services;

import java.util.List;

import io.github.emfsilva.data.model.Book;
import io.github.emfsilva.data.vo.v1.BookDTO;
import io.github.emfsilva.exception.ResourceNotFoundException;
import io.github.emfsilva.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.emfsilva.converter.DozerConverter;

@Service
public class BookServices {
	
	@Autowired
    BookRepository repository;
		
	public BookDTO create(BookDTO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return vo;
	}
	
	public List<BookDTO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookDTO.class);
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
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
