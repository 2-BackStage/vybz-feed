//package back.vybz.feed_service.feed.presentation;
//
//import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;
//import back.vybz.feed_service.feed.dto.request.RequestScrollReelsDto;
//import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
//import back.vybz.feed_service.feed.dto.response.ResponseAddReelsDto;
//import back.vybz.feed_service.feed.dto.response.ResponseScrollReelsDto;
//import back.vybz.feed_service.feed.vo.request.RequestAddReelsVo;
//import back.vybz.feed_service.feed.vo.request.RequestUpdateReelsVo;
//import back.vybz.feed_service.common.entity.BaseResponseEntity;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/reels")
//public class BuskerReelsController {
//
//    private final BuskerReelsService buskerReelsService;
//
//    @Operation(
//            summary = "Reels 생성 API",
//            description = "버스커 Reels를 생성하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @PostMapping
//    public ResponseEntity<ResponseAddReelsDto> createReels(
//            @RequestPart RequestAddReelsVo requestAddReelsVo,
//            @RequestPart("videoFile") MultipartFile videoFile
//    ) {
//        RequestAddReelsDto requestAddReelsDto = RequestAddReelsDto.from(requestAddReelsVo);
//        requestAddReelsDto.setVideoFile(videoFile);
//
//        return ResponseEntity.ok(buskerReelsService.createReels(requestAddReelsDto));
//    }
//
//    @Operation(
//            summary = "Reels 목록 무한스크롤 조회 API",
//            description = "버스커 Reels 목록을 무한스크롤로 조회하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @GetMapping("/reels")
//    public BaseResponseEntity<ResponseScrollReelsDto> getReelsScrollList(
//            @ModelAttribute RequestScrollReelsDto requestScrollReelsDto
//    ) {
//        return BaseResponseEntity.ok(
//                buskerReelsService.getReelsScrollList(requestScrollReelsDto)
//        );
//    }
//
//    @Operation(
//            summary = "Reels 수정 API",
//            description = "버스커 Reels를 수정하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateReels(@PathVariable("id") String feedId,
//                                            @RequestBody RequestUpdateReelsVo requestUpdateReelsVo) {
//        RequestUpdateReelsDto requestUpdateReelsDto = RequestUpdateReelsDto.from(feedId, requestUpdateReelsVo);
//        buskerReelsService.updateReels(requestUpdateReelsDto);
//        return ResponseEntity.ok().build();
//    }
//
//
//    @Operation(
//            summary = "Reels 삭제 API",
//            description = "버스커 Reels를 삭제하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteReels(@PathVariable("id") String feedId) {
//        buskerReelsService.deleteReels(feedId);
//        return ResponseEntity.ok().build();
//    }
//}
//
