package com.cstangga.ebookland.member.repository;

import com.cstangga.ebookland.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Object> findByEmail(String email);
    @Query("""
        select
            m
        from
            Member m join fetch m.authorities
        where
            m.email = :username
    """)
    Optional<Member> findByUsername(@Param("username")String username); //

}
