package com.cstangga.ebookland.bookboard.repository;


import com.cstangga.ebookland.bookboard.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String > {
    List<Book> findBookByAuthorNameContaining(String author);
    List<Book> findBookByBookNameContaining(String bookName);
    Book findBookById(long bookId);
}
