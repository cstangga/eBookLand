package com.cstangga.ebookland.freeboard.repository;

import com.cstangga.ebookland.freeboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String > {

    Post findPostById(long postId);

    List<Post> findPostByTitleContaining(String title);

    @Query(value = "select p from post p join fetch p.member where p.member.nickName LIKE %:nickName%")
    List<Post> findPostByMember_NickNameContaining(String nickName);




}
