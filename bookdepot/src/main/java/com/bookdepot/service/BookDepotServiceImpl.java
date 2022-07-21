package com.bookdepot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookdepot.entity.Book;
import com.bookdepot.repository.BookDepotRepo;

@Service
public class BookDepotServiceImpl implements BookDepotService{

	private final BookDepotRepo bookDepotRepo;
	
	@Autowired
	public BookDepotServiceImpl(BookDepotRepo bookDepotRepo) {
		this.bookDepotRepo=bookDepotRepo;
	}
	
	@Override
	public Book save(Book book) {
		return bookDepotRepo.save(book);
	}

	@Override
	public Optional<Book> viewBookById(Long id) {
		 return bookDepotRepo.findById(id);
	}

	@Override
	public void deleteBook(Book book) {
		bookDepotRepo.delete(book);
		
	}

	@Override
	public List<Book> viewBooks() {
		return bookDepotRepo.findAll();
	}

	
}
