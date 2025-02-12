package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyPaperBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface BuyPaperBookRepository extends JpaRepository<BuyPaperBook, String > {


    List<BuyPaperBook> findAllByMemberId(long memberId);
}
