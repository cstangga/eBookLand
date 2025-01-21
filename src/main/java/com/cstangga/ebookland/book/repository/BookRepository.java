package com.cstangga.ebookland.book.repository;


import com.cstangga.ebookland.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String > {
    List<Book> findBookByAuthorNameContaining(String author);
    List<Book> findBookByBookNameContaining(String bookName);
    Book findBookById(long bookId);
}
