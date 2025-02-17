package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.RentalEbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalBookRepository extends JpaRepository<RentalEbook, String > {

    // OneToMany(One=RentalEBook, Many=Book)
    @Query(value = "select re from RentalEbook re join fetch re.book where re.memberId=:memberId")
    List<RentalEbook> findAllByMemberId(@Param("memberId")long memberId);

    RentalEbook findRentalEbookByBookId(long id);
}
