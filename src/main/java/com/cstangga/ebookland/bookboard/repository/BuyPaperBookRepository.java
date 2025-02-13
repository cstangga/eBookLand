package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface BuyPaperBookRepository extends JpaRepository<BuyPaperBook, String > {


    @Query(value = "select bp from BuyPaperBook bp join fetch bp.book where bp.memberId=:memberId")
    List<BuyPaperBook> findAllByMemberId(@Param("memberId")long id);
}
