package com.bookdepot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "bookdepot")
public class Book {
    @Id
    @SequenceGenerator(name="seq",sequenceName="oracle_seq")        
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    @NotBlank(message = "Empty field!")
    @Column(name = "bookCode")
	private Long bookCode;
	@NotBlank(message = "Empty field!")
    @Column(name = "book_name")
	private String bookName;
	@NotBlank(message = "Empty field!")
    @Column(name = "author_name")
	private String authorName;
	@NotBlank(message = "Empty field!")
    @Column(name = "price")
	private String price;
	@NotBlank(message = "Empty field!")
    @Column(name = "description")
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBookCode() {
		return bookCode;
	}
	public void setBookCode(Long bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
