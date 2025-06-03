package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.NoticeService;
import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
//import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.feed.vo.request.RequestAddNoticeVo;
//import back.vybz.feed_service.feed.vo.request.RequestUpdateNoticeVo;
import back.vybz.feed_service.feed.vo.response.ResponseAddNoticeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class BuskerNoticeController {

    private final NoticeService noticeService;

    @Operation(
            summary = "공지 등록 API",
            description = "버스커 공지를 등록하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @PostMapping("/notice")
    public BaseResponseEntity<ResponseAddNoticeVo> createNotice(@RequestBody RequestAddNoticeVo requestAddNoticeVo){
        RequestAddNoticeDto requestAddNoticeDto = RequestAddNoticeDto.from(requestAddNoticeVo);
        ResponseAddNoticeDto responseAddNoticeDto = noticeService.createNotice(requestAddNoticeDto);
        return new BaseResponseEntity<>(responseAddNoticeDto.toVo());
    }


//    @Operation(
//            summary = "공지 수정 API",
//            description = "버스커 공지를 수정하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @PutMapping("/notice/{noticeId}")
//    public BaseResponseEntity<Void> updateNotice(@PathVariable("noticeId") String noticeId,
//                                                 @RequestBody RequestUpdateNoticeVo requestUpdateNoticeVo) {
//        String userUuid = requestUpdateNoticeVo.getUserUuid();
//        noticeService.updateNotice(RequestUpdateNoticeDto.of(noticeId, userUuid, requestUpdateNoticeVo));
//        return new BaseResponseEntity<>();
//    }
//
//    @Operation(
//            summary = "공지 삭제 API",
//            description = "버스커 공지를 삭제하는 API입니다.",
//            tags = {"BUSKER-SERVICE"}
//    )
//    @DeleteMapping("/notice/{noticeId}")
//    public BaseResponseEntity<Void> deleteNotice(@PathVariable("noticeId") String noticeId) {
//        noticeService.deleteNotice(noticeId);
//        return new BaseResponseEntity<>();
//    }
}



