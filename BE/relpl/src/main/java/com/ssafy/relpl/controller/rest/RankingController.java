package com.ssafy.relpl.controller.rest;


import com.ssafy.relpl.dto.response.RankingDataDto;
import com.ssafy.relpl.service.RankingService;
import com.ssafy.relpl.service.result.CommonResult;
import com.ssafy.relpl.service.result.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Slf4j
@RestController
@RequestMapping(path = "api/rank")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RankingController {

    private final RankingService rankingService;

    
    // 요청 들어오면 랭킹을 앱에 반환해주는 서비스 로직 호출
    @GetMapping("/now")
    public SingleResult<RankingDataDto> getNowRanking () {
        log.info("getNowRanking 내부로 들어옴");
        return rankingService.getNowRanking();
    }
    

    // 플로깅 중단 시 랭킹에 사용자 추가 혹은 update 필요 !
    // 테스트용 : 랭킹에 사용자 추가 혹은 update 하는 서비스 로직 호출
    @GetMapping("/{nickname}/{distance}")
    public CommonResult addOrUpDateRanking(@PathVariable String nickname, @PathVariable double distance) {
        log.info("새로운 유저를 랭킹에 추가하기");
        return rankingService.addOrUpDateRanking(nickname, distance);
    }
}