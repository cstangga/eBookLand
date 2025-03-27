package com.cstangga.ebookland.noticeboard.repository;

import com.cstangga.ebookland.freeboard.entity.Post;
import com.cstangga.ebookland.noticeboard.dto.NoticeDto;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, String > {
    List<Notice> findNoticeByTitleContaining(String title);

    Notice findNoticeById(long noticeId);
}
