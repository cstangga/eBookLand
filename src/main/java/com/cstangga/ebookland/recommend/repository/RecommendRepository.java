package com.cstangga.ebookland.recommend.repository;


import com.cstangga.ebookland.recommend.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,String > {

    Optional<Recommend> findByMemberEmailAndNoticeId(String email, Long noticeId);

}
