package com.cstangga.ebookland.noticeboard.repository;


import com.cstangga.ebookland.noticeboard.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, String > {
    List<Notice> findNoticeByTitleContaining(String title);

    @Query("SELECT n FROM notice n LEFT JOIN FETCH n.recommends WHERE n.id = :noticeId")
    Notice findNoticeById(@Param("noticeId") long noticeId);
}
