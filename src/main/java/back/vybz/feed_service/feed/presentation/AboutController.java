package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.application.service.AboutService;
import back.vybz.feed_service.feed.dto.request.RequestAddAboutDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;
import back.vybz.feed_service.feed.vo.request.RequestAddAboutVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateAboutVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/feed")
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @Operation(
            summary = "자기소개 등록 API",
            description = "자기소개 등록 API 입니다.",
            tags = {"ABOUT-SERVICE"}
    )
    @PostMapping("/about")
    public BaseResponseEntity<Void> createAbout(//HttpServletRequest httpServletRequest,
                                                              @Valid @RequestBody RequestAddAboutVo requestAddAboutVo) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        RequestAddAboutDto requestAddAboutDto = RequestAddAboutDto.from(requestAddAboutVo, writerUuid);
        aboutService.createAbout(requestAddAboutDto);
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "자기소개 수정 API",
            description = "자기소개 수정 API 입니다.",
            tags = {"ABOUT-SERVICE"}
    )
    @PutMapping("/about/{aboutId}")
    public BaseResponseEntity<Void> updateAbout(//HttpServletRequest httpServletRequest,
                                                @PathVariable("aboutId") String aboutId,
                                                @RequestBody RequestUpdateAboutVo requestUpdateAboutVo) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        aboutService.updateAbout(RequestUpdateAboutDto.of(aboutId, requestUpdateAboutVo, writerUuid));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "자기소개 삭제 API",
            description = "자기소개 삭제 API 입니다.",
            tags = {"ABOUT-SERVICE"}
    )
    @DeleteMapping("/about/{aboutId}")
    public BaseResponseEntity<Void> deleteAbout(//HttpServletRequest httpServletRequest,
                                                @PathVariable("aboutId") String aboutId) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        aboutService.deleteAbout(aboutId, writerUuid);
        return new BaseResponseEntity<>();
    }
}
