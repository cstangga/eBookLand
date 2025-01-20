package com.cstangga.ebookland.book.repository;


import com.cstangga.ebookland.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String > {
}
