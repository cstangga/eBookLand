package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalBookRepository extends JpaRepository<RentalEbook, String > {
}
