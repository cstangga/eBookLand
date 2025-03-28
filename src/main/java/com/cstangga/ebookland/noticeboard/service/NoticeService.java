package com.cstangga.ebookland.noticeboard.service;


import com.cstangga.ebookland.noticeboard.dto.NoticeDto;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import lombok.RequiredArgsConstructor;
import com.cstangga.ebookland.noticeboard.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice addNotice(NoticeDto noticeDto){
        log.info("NoticeService / addNotice");
        Notice entity = noticeDto.dtoToEntity();

        return noticeRepository.save(entity);

    }

    // 전체 공지사항 게시글 가져오기
    public List<NoticeDto> findAll() {
        log.info("NoticeService / findAll()");
        return noticeRepository.findAll().stream()
                .map(entity->entity.toDto())
                .toList();
    }

    public List<NoticeDto> findByTitle(String title)
    {
        log.info("NoticeService / findByTitle");

        return noticeRepository.findNoticeByTitleContaining(title).stream()
                .map(entity->entity.toDto())
                .toList();
    }

    public NoticeDto findNoticeById(long noticeId) {
        log.info("NoticeService / findPostById");

        Notice entity = noticeRepository.findNoticeById(noticeId);
        log.info("entity = {}",entity);

        return entity.toDto();
    }

    public Notice update(NoticeDto dto) {
        Notice entity=noticeRepository.findNoticeById(dto.getNoticeId());
        entity.update(dto);
        return noticeRepository.save(entity);

    }

    public void delete(long noticeId) {
        log.info("NoticeService / delete / {}",noticeId);
        noticeRepository.deleteById(String.valueOf(noticeId));
    }

}


