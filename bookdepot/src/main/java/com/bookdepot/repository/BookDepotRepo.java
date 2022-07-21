package com.bookdepot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookdepot.entity.Book;


@Repository
public interface BookDepotRepo extends JpaRepository<Book, Long>{

}
