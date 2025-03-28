package com.cstangga.ebookland.recommend.controller;

import com.cstangga.ebookland.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/recommend")

public class RecommendController {
    private final RecommendService recommendService;

    /*
     * true = 추천이 됨
     * false = 추천 안됨(중복방지) -> 이미 하셧습니다 추천을 취소 하시겠습니까??
     * Entity를 다시 생각해 봐야겟다
     * */
    @PostMapping("/disLikes")
    public ResponseEntity<?> disLikes(@RequestParam("user")String user, @RequestParam("noticeId") long noticeId){
        log.info("POST / noticeboard / dislikes");
        log.info("user = {}, noticeId = {}",user,noticeId);

        // true = 증가, false = 이미 추천/비추천을 했다.
        boolean result=recommendService.duplicationCheck("disLikes",user,noticeId);

        if (result) {
            return ResponseEntity.ok(Map.of("success", true, "message", "비추천 완료"));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "이미 추천/비추천을 했습니다."));
        }
    }

    @PostMapping("/likes")
    public ResponseEntity<?> likes(@RequestParam("user")String user, @RequestParam("noticeId") long noticeId){
        log.info("POST / noticeboard / likes");
        log.info("user = {}, noticeId = {}",user,noticeId);

        // true = 증가, false = 이미 추천/비추천을 했다.
        boolean result=recommendService.duplicationCheck("likes",user,noticeId);

        if (result) {
            return ResponseEntity.ok(Map.of("success", true, "message", "추천 완료"));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "이미 추천/비추천을 했습니다."));
        }

    }
}
