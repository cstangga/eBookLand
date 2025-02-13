package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import com.cstangga.ebookland.bookboard.entity.SellsOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface BuyEbookRepository extends JpaRepository<BuyEbook, String > {

    @Query(value = "select be from BuyEbook be join fetch be.book where be.memberId=:memberId")
    List<BuyEbook> findAllByMemberId(@Param("memberId")long id);
}
