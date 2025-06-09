package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.NoticeService;
import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.feed.vo.request.RequestAddNoticeVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateNoticeVo;
import back.vybz.feed_service.feed.vo.response.ResponseAddNoticeVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @Operation(
            summary = "공지 등록 API",
            description = "버스커 공지를 등록하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @PostMapping("/notice")
    public BaseResponseEntity<ResponseAddNoticeVo> createNotice(//HttpServletRequest httpServletRequest,
                                                                @RequestBody RequestAddNoticeVo requestAddNoticeVo){

        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        RequestAddNoticeDto requestAddNoticeDto = RequestAddNoticeDto.from(requestAddNoticeVo, writerUuid);
        ResponseAddNoticeDto responseAddNoticeDto = noticeService.createNotice(requestAddNoticeDto);
        return new BaseResponseEntity<>(responseAddNoticeDto.toVo());
    }


    @Operation(
            summary = "공지 수정 API",
            description = "버스커 공지를 수정하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @PutMapping("/notice/{noticeId}")
    public BaseResponseEntity<Void> updateNotice(//HttpServletRequest httpServletRequest,
                                                 @PathVariable("noticeId") String noticeId,
                                                 @RequestBody RequestUpdateNoticeVo requestUpdateNoticeVo) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        noticeService.updateNotice(RequestUpdateNoticeDto.of(noticeId,requestUpdateNoticeVo, writerUuid));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "공지 삭제 API",
            description = "버스커 공지를 삭제하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @DeleteMapping("/notice/{noticeId}")
    public BaseResponseEntity<Void> deleteNotice(//HttpServletRequest httpServletRequest,
                                                 @PathVariable("noticeId") String noticeId) {

        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        noticeService.deleteNotice(noticeId, writerUuid);
        return new BaseResponseEntity<>();
    }
}



