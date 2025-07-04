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
    public BaseResponseEntity<Void> createAbout(@Valid @RequestBody RequestAddAboutVo requestAddAboutVo) {
        RequestAddAboutDto requestAddAboutDto = RequestAddAboutDto.from(requestAddAboutVo, requestAddAboutVo.getWriterUuid());
        aboutService.createAbout(requestAddAboutDto);
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "자기소개 수정 API",
            description = "자기소개 수정 API 입니다.",
            tags = {"ABOUT-SERVICE"}
    )
    @PutMapping("/about/{aboutId}")
    public BaseResponseEntity<Void> updateAbout(@PathVariable("aboutId") String aboutId,
                                                @RequestBody RequestUpdateAboutVo requestUpdateAboutVo) {
        aboutService.updateAbout(RequestUpdateAboutDto.of(aboutId, requestUpdateAboutVo, requestUpdateAboutVo.getWriterUuid()));
        return new BaseResponseEntity<>();
    }

    @Operation(
            summary = "자기소개 삭제 API",
            description = "자기소개 삭제 API 입니다.",
            tags = {"ABOUT-SERVICE"}
    )
    @DeleteMapping("/about/{aboutId}")
    public BaseResponseEntity<Void> deleteAbout(@PathVariable("aboutId") String aboutId) {
        aboutService.deleteAbout(aboutId, null);
        return new BaseResponseEntity<>();
    }
}
