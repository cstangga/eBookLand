package com.cstangga.ebookland.freeboard.controller;

import com.cstangga.ebookland.auth.principal.AuthPrincipal;
import com.cstangga.ebookland.freeboard.dto.CommentDto;
import com.cstangga.ebookland.freeboard.dto.PostDto;
import com.cstangga.ebookland.freeboard.entity.Post;
import com.cstangga.ebookland.freeboard.repository.PostRepository;
import com.cstangga.ebookland.freeboard.service.CommentService;
import com.cstangga.ebookland.freeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final PostRepository postRepository;
    // 자유게시판 CRUD


    @GetMapping("/list")
    public void list(Model model) {
        log.info("GET /freeboard/list");
        List<PostDto> postDtoList=postService.findAllPost();
        log.info("postDtoList = {}",postDtoList);
        model.addAttribute("postDtoList", postDtoList);
    }

    @GetMapping("/detail/{postId}")
    public String detail(@PathVariable("postId") long postId, Model model) {
        log.info("POST /freeboard/detail/{}",postId);
        PostDto dto=postService.findPostById(postId);
        model.addAttribute("dto", dto);
        return "/freeboard/detail";
    }

    //게시글 등록 요청
    @PostMapping("/addpost")
    public String addPost(@ModelAttribute PostDto postDto) {
        log.info("POST /freeboard/addpost");
        Post entity=postService.addPost(postDto);
        log.info("postDto = {}", postDto);
        log.info("등록 후 Post={}",entity);
        return "redirect:/freeboard/list";
    }

    //게시글 등록 페이지
    @GetMapping("/addpost")
    public void addPost(@AuthenticationPrincipal AuthPrincipal authPrincipal, Model model)
    {
        log.info("GET /freeboard/addpost");
        model.addAttribute("memberId", authPrincipal.getMember().getId());
    }

    //게시글 수정페이지 호출
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable long postId, Model model)
    {
        log.info("GET /freeboard/edit");
        PostDto dto=postService.findPostById(postId);
        model.addAttribute("postDto", dto);
        return "/freeboard/edit";
    }

    @PostMapping("/updatepost")
    public String updatePost(@ModelAttribute PostDto postDto)
    {
        log.info("POST /freeboard/update");
        Post beforeEntity=postService.updatePost(postDto);
        log.info("수정 전 entity = {}", beforeEntity);
        Post afterEntity=postService.updatePost(postDto);
        log.info("수정 후 entity = {}",afterEntity);
        return String.format("redirect:/freeboard/detail/%d",postDto.getPostId());
    }

    //게시글 삭제
    @PostMapping("/deletePost")
    @ResponseBody
    public int delete(@RequestParam("postId")long postId){
        log.info("GET /freeboard/deletePost");
        int deleteStatus=postService.deletePost(postId);
        log.info("deleteStatus = {}",deleteStatus);
        return deleteStatus;
    }

    // 게시글 검색
    @PostMapping("/search")
    public String search(@RequestParam String searchType,@RequestParam String word, Model model) {
        log.info("GET /freeboard/search");
        log.info("searchType = {}",searchType);
        log.info("word = {}",word);
        List<PostDto> postDtoList=postService.searchPost(searchType,word);
        log.info("postDtoList = {}",postDtoList);
        model.addAttribute("postDtoList", postDtoList);
        return "/freeboard/list";
    }
}
