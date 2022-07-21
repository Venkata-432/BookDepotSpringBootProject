package com.bookdepot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookdepot.entity.Book;
import com.bookdepot.exception.ResourceNotFoundException;
import com.bookdepot.service.BookDepotService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookdepot")
public class BookDepotController{

	private final BookDepotService bookDepotService;
	
	@Autowired
	public BookDepotController(BookDepotService bookDepotService){
		this.bookDepotService=bookDepotService;
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Book savedBook = bookDepotService.save(book);

            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        }
	}

	@GetMapping("/viewBookById/{id}")
	public ResponseEntity<?> viewBookById(@PathVariable Long id) {
		Book book = bookDepotService.viewBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));

        return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id) {
		
		Book book = bookDepotService.viewBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));

		bookDepotService.deleteBook(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id,
            @RequestBody @Valid Book bookDetails,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Book book = bookDepotService.viewBookById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));

            book.setBookCode(bookDetails.getBookCode());
            book.setBookName(bookDetails.getBookName());
            book.setAuthorName(bookDetails.getAuthorName());
            book.setPrice(bookDetails.getPrice());
            book.setDescription(bookDetails.getDescription());

            bookDepotService.save(book);

            return new ResponseEntity<>(HttpStatus.OK);
        }
		
	}

	@GetMapping("/books")
	public ResponseEntity<?> viewBooks() {
		List<Book> books = bookDepotService.viewBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
	}

}
