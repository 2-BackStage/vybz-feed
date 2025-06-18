package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.ReelsService;
import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.feed.vo.request.RequestAddReelsVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateReelsVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
public class ReelsController {

    private final ReelsService reelsService;

    @Operation(
            summary = "버스커 릴스 등록 API",
            description = "버스커 릴스를 등록하는 API입니다.",
            tags = {"FEED-SERVICE"}
    )
    @PostMapping("/reels")
    public BaseResponseEntity<Void> createReels(//HttpServletRequest httpServletRequest,
                                                              @RequestBody RequestAddReelsVo requestAddReelsVo) {
       // String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        RequestAddReelsDto requestAddReelsDto = RequestAddReelsDto.from(requestAddReelsVo, writerUuid);
        reelsService.createReels(requestAddReelsDto);
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "버스커 릴스 수정 API",
            description = "버스커 릴스를 수정하는 API입니다.",
            tags = {"FEED-SERVICE"}
    )
    @PutMapping("/reels/{reelsId}")
    public BaseResponseEntity<Void> updateReels(//HttpServletRequest httpServletRequest,
                                                @PathVariable("reelsId") String reelsId,
                                                @Valid @RequestBody RequestUpdateReelsVo requestUpdateReelsVo) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        reelsService.updateReels(RequestUpdateReelsDto.of(reelsId, requestUpdateReelsVo, writerUuid));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "버스커 릴스 삭제 API",
            description = "버스커 릴스를 삭제하는 API입니다.",
            tags = {"FEED-SERVICE"}
    )
    @DeleteMapping("/reels/{reelsId}")
    public BaseResponseEntity<Void> deleteReels(//HttpServletRequest httpServletRequest,
                                                @PathVariable("reelsId") String reelsId) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        reelsService.deleteReels(reelsId, writerUuid);
        return new BaseResponseEntity<>();
    }
}

