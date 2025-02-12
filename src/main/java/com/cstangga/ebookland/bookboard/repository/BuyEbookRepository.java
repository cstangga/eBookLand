package com.cstangga.ebookland.bookboard.repository;

import com.cstangga.ebookland.bookboard.entity.Book;
import com.cstangga.ebookland.bookboard.entity.BuyEbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface BuyEbookRepository extends JpaRepository<BuyEbook, String > {

    List<BuyEbook> findAllByMemberId(long id);
}
