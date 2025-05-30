package back.vybz.feed_service.user.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.user.application.service.UserFeedService;
import back.vybz.feed_service.user.dto.request.RequestAddFeedDto;
import back.vybz.feed_service.user.dto.response.ResponseAddFeedDto;
import back.vybz.feed_service.user.vo.response.ResponseAddFeedVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/feed")
@RequiredArgsConstructor
public class UserFeedController {

    private final UserFeedService userFeedService;

    @Operation(
            summary = "사용자 피드 등록 API",
            description = "사용자의 피드를 등록하는 API입니다.",
            tags = {"USER-FEED-SERVICE"}
    )
    @PostMapping
    public BaseResponseEntity<ResponseAddFeedVo> createFeed(@RequestBody RequestAddFeedDto requestAddFeedDto){
        ResponseAddFeedDto responseAddFeedDto = userFeedService.createFeed(requestAddFeedDto);
        return new BaseResponseEntity<>(responseAddFeedDto.toVo());
    }
}
