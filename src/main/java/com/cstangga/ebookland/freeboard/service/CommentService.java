package com.cstangga.ebookland.freeboard.service;

import com.cstangga.ebookland.freeboard.dto.CommentDto;
import com.cstangga.ebookland.freeboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDto> findCommentByPostId(Long postId) {
        return null;
    }

}
