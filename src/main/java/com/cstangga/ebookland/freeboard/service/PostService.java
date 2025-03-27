package com.cstangga.ebookland.freeboard.service;

import com.cstangga.ebookland.freeboard.dto.PostDto;
import com.cstangga.ebookland.freeboard.entity.Post;
import com.cstangga.ebookland.freeboard.repository.PostRepository;
import com.cstangga.ebookland.member.entity.Member;
import com.cstangga.ebookland.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 멤버가 작성한 게시글 입력
    public Post addPost(PostDto postDto) {
        log.info("PostService / addPost");
        Member member = memberRepository.findMemberById(postDto.getMemberId());
        Post post = postDto.dtoToEntity(member);
        return postRepository.save(post);
    }

    // 전체 게시글 가져오기
    public List<PostDto> findAllPost() {
        log.info("PostService / findAllPost");
        List<PostDto> postDtoList=new ArrayList<>();
        List<Post> postList = postRepository.findAll(); // 스트림으로 변경해보자

        for(Post entity:postList){
            postDtoList.add(entity.toDto());
        }
        return postDtoList;
    }

    // 게시글 업데이트 용도
    public PostDto findPostById(long postId) {
        log.info("PostService / findPostById");
        Post entity=postRepository.findPostById(postId);
        return entity.toDto();
    }

    // 게시글 삭제
    public int deletePost(long postId) {
        log.info("PostService / deletePost");
        log.info("postId = {}",postId);
        postRepository.deleteById(String.valueOf(postId)); // 삭제
        boolean exists = postRepository.existsById(String.valueOf(postId));
        if (!exists) {
            return 1;
        } else {
            return 0;
        }
    }

    public Post updatePost(PostDto postDto) {
        log.info("PostService / updatePost");
        Post entity=postRepository.findPostById(postDto.getPostId());
        entity.updatePost(postDto);
        return postRepository.save(entity);
    }

    public List<PostDto> searchPost(String searchType, String word) {
        log.info("PostService / searchPost");
        List<PostDto> postDtoList=new ArrayList<>();
        if(searchType.equals("writer"))
        {
            for(Post entity:postRepository.findPostByMember_NickNameContaining(word))
            {
                postDtoList.add(entity.toDto());
            }
            log.info("postList에 add 후 = {}",postDtoList);
        }
        else {
            for(Post entity:postRepository.findPostByTitleContaining(word))
            {
                postDtoList.add(entity.toDto());

            }
            log.info("postList에 add 후 = {}",postDtoList);

        }
        return postDtoList;
    }
}
