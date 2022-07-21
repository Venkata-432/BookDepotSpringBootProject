package com.bookdepot.service;

import java.util.List;
import java.util.Optional;

import com.bookdepot.entity.Book;

public interface BookDepotService{

	   Book save(Book b);
	   
	   Optional<Book> viewBookById(Long id);
	   
	   void deleteBook(Book b);
	   
	   List<Book> viewBooks();
}
