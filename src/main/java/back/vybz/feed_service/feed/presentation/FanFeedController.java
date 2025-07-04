package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.FanFeedService;
import back.vybz.feed_service.feed.dto.request.RequestAddFanFeedDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import back.vybz.feed_service.feed.vo.request.RequestAddFanFeedVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateFanFeedVo;
import io.swagger.v3.oas.annotations.Operation;
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
    public BaseResponseEntity<Void> createFanFeed(@Valid @RequestBody RequestAddFanFeedVo requestAddFanFeedVo) {
        RequestAddFanFeedDto requestAddFanFeedDto = RequestAddFanFeedDto.from(requestAddFanFeedVo, requestAddFanFeedVo.getWriterUuid());
        fanFeedService.createFanFeed(requestAddFanFeedDto);
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "팬 피드 수정 API",
            description = "팬 피드 수정 API 입니다.",
            tags = {"FAN-FEED-SERVICE"}
    )
    @PutMapping("/fan/{fanFeedId}")
    public BaseResponseEntity<Void> updateFanFeed(@PathVariable("fanFeedId") String fanFeedId,
                                                 @RequestBody RequestUpdateFanFeedVo requestUpdateFanFeedVo){
        fanFeedService.updateFanFeed(RequestUpdateFanFeedDto.of(fanFeedId, requestUpdateFanFeedVo, requestUpdateFanFeedVo.getWriterUuid()));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "팬 피드 삭제 API",
            description = "팬 피드 삭제 API 입니다.",
            tags = {"FAN-FEED-SERVICE"}
    )
    @DeleteMapping("/fan/{fanFeedId}")
    public BaseResponseEntity<Void> deleteFanFeed(@PathVariable("fanFeedId") String fanFeedId) {
        fanFeedService.deleteFanFeed(fanFeedId, null);
        return new BaseResponseEntity<>();
    }


}
