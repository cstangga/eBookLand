package com.cstangga.ebookland.recommend.service;

import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import com.cstangga.ebookland.noticeboard.entity.Notice;
import com.cstangga.ebookland.noticeboard.repository.NoticeRepository;
import com.cstangga.ebookland.recommend.entity.Recommend;
import com.cstangga.ebookland.recommend.entity.RecommendType;
import com.cstangga.ebookland.recommend.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    public boolean duplicationCheck(String recommend, String userEmail, long noticeId) {
        log.info("RecommendService / recommendCheck()");
        log.info("recommend = {}, userEmail = {}, noticeId = {}", recommend, userEmail, noticeId);

        RecommendType newType = RecommendType.valueOf(recommend.toUpperCase());

        Member member = memberRepository.findMemberByEmail(userEmail);
        Notice notice = noticeRepository.findNoticeById(noticeId);


        Optional<Recommend> existingRecommend = recommendRepository.findByMemberEmailAndNoticeId(userEmail, noticeId);

        if (existingRecommend.isPresent()) {
            return false;

        } else {
            Recommend entity = new Recommend(newType, member, notice);
            recommendRepository.save(entity);
            return true;
        }

    }

}
