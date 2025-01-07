package com.cstangga.ebookland.member.repository;

import com.cstangga.ebookland.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Member findByEmail(String email);
}
