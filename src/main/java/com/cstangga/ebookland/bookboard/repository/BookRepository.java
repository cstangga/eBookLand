package com.cstangga.ebookland.bookboard.repository;


import com.cstangga.ebookland.bookboard.entity.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String > {
    List<Book> findBookByAuthorNameContaining(String author);
    List<Book> findBookByBookNameContaining(String bookName);
    Book findBookById(long bookId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "select b from book b where b.id= :id")
    Book findBookByIdForUpdate(@Param("id")long bookId);


}
