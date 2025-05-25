package back.vybz.feed_service.busker.presentation;

import back.vybz.feed_service.busker.application.service.BuskerNoticeService;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.busker.vo.request.RequestAddNoticeVo;
import back.vybz.feed_service.busker.vo.request.RequestUpdateNoticeVo;
import back.vybz.feed_service.busker.vo.response.ResponseAddNoticeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class BuskerController {

    private final BuskerNoticeService buskerNoticeService;

    @Operation(
        summary = "공지 등록 API",
        description = "버스커 공지를 등록하는 API입니다.",
        tags = {"BUSKER-SERVICE"}
    )
    @PostMapping("/notice")
    public ResponseEntity<ResponseAddNoticeVo> createNotice(@RequestBody RequestAddNoticeDto requestAddNoticeDto) {
        ResponseAddNoticeDto noticeDto = buskerNoticeService.createNotice(requestAddNoticeDto);
        return ResponseEntity.ok(noticeDto.toVo());
    }

    @Operation(
        summary = "공지 수정 API",
        description = "버스커 공지를 수정하는 API입니다.",
        tags = {"BUSKER-SERVICE"}
    )
    @PutMapping("/notice/{noticeId}")
    public ResponseEntity<Void> updateNotice(@PathVariable("noticeId") String noticeId,
                                             @RequestBody RequestUpdateNoticeVo requestUpdateNoticeVo) {
        String userUuid = requestUpdateNoticeVo.getUserUuid();
        buskerNoticeService.updateNotice(RequestUpdateNoticeDto.of(noticeId, userUuid, requestUpdateNoticeVo));
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "공지 삭제 API",
        description = "버스커 공지를 삭제하는 API입니다.",
        tags = {"BUSKER-SERVICE"}
    )
    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("noticeId") String noticeId) {
        buskerNoticeService.deleteNotice(new ObjectId(noticeId));
        return ResponseEntity.ok().build();
    }


}



