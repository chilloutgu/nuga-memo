//package com.nuga.memo.controller;
//
//import com.nuga.memo.domain.Memo;
//import com.nuga.memo.dto.MemoCdo;
//import com.nuga.memo.dto.MemoUdo;
//import com.nuga.memo.service.MemoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/memos")
//@RequiredArgsConstructor
//public class MemoController {
//    private final MemoService memoService;
//
//    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<String> createMemo(@RequestBody Mono<MemoCdo> memoCdoMono) {
//        return memoCdoMono.flatMap(memoService::createMemo).log();
//    }
//
//    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Memo> getAllMemos() {
//        return memoService.getAllMemos().log();
//    }
//
//    @GetMapping(value = "{memoId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<Memo> getOneMemo(@PathVariable String memoId) {
//        return memoService.getOneMemo(memoId).log();
//    }
//
//    @PutMapping("{memoId}")
//    public Mono<Void> modifyMemo(@PathVariable String memoId,
//                                 @RequestBody Mono<MemoUdo> memoUdoMono) {
//        return memoUdoMono.flatMap(memoUdo -> memoService.modifyMemo(memoId, memoUdo)).log();
//    }
//
//    @DeleteMapping("{memoId}")
//    public Mono<Void> removeMemo(@PathVariable String memoId) {
//        return memoService.removeMemo(memoId).log();
//    }
//}
