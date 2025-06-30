package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.FanFeedService;
import back.vybz.feed_service.feed.dto.request.RequestAddFanFeedDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import back.vybz.feed_service.feed.vo.request.RequestAddFanFeedVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateFanFeedVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/feed")
@RequiredArgsConstructor
public class FanFeedController {

    private final FanFeedService fanFeedService;

    @Operation(
            summary = "팬 피드 등록 API",
            description = "팬 피드 등록 API 입니다.",
            tags = {"FAN-FEED-SERVICE"}
    )
    @PostMapping("/fan")
    public BaseResponseEntity<Void> createFanFeed(HttpServletRequest httpServletRequest,
                                                                 @Valid @RequestBody RequestAddFanFeedVo requestAddFanFeedVo) {
        String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        if (writerUuid == null || writerUuid.isEmpty()) {
            throw new IllegalArgumentException("X-USER-Id 헤더가 필요합니다.");
        }
        RequestAddFanFeedDto requestAddFanFeedDto = RequestAddFanFeedDto.from(requestAddFanFeedVo, writerUuid);
        fanFeedService.createFanFeed(requestAddFanFeedDto);
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "팬 피드 수정 API",
            description = "팬 피드 수정 API 입니다.",
            tags = {"FAN-FEED-SERVICE"}
    )
    @PutMapping("/fan/{fanFeedId}")
    public BaseResponseEntity<Void> updateFanFeed(HttpServletRequest httpServletRequest,
                                                  @PathVariable("fanFeedId") String fanFeedId,
                                                  @RequestBody RequestUpdateFanFeedVo requestUpdateFanFeedVo){
        String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        if (writerUuid == null || writerUuid.isEmpty()) {
            throw new IllegalArgumentException("X-USER-Id 헤더가 필요합니다.");
        }
        fanFeedService.updateFanFeed(RequestUpdateFanFeedDto.of(fanFeedId, requestUpdateFanFeedVo, writerUuid));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "팬 피드 삭제 API",
            description = "팬 피드 삭제 API 입니다.",
            tags = {"FAN-FEED-SERVICE"}
    )
    @DeleteMapping("/fan/{fanFeedId}")
    public BaseResponseEntity<Void> deleteFanFeed(HttpServletRequest httpServletRequest,
                                                  @PathVariable("fanFeedId") String fanFeedId) {
        String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        if (writerUuid == null || writerUuid.isEmpty()) {
            throw new IllegalArgumentException("X-USER-Id 헤더가 필요합니다.");
        }
        fanFeedService.deleteFanFeed(fanFeedId, writerUuid);
        return new BaseResponseEntity<>();
    }


}
