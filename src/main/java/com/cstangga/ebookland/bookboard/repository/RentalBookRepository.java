package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalBookRepository extends JpaRepository<RentalEbook, String > {
    List<RentalEbook> findAllByMemberId(long memberId);
}
