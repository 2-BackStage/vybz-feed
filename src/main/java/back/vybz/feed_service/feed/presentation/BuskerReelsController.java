package back.vybz.feed_service.feed.presentation;

import back.vybz.feed_service.feed.application.service.ReelsService;
import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;

import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddReelsDto;

import back.vybz.feed_service.feed.infrastructure.repository.ReelsRepository;
import back.vybz.feed_service.feed.vo.request.RequestAddReelsVo;
import back.vybz.feed_service.feed.vo.request.RequestUpdateReelsVo;
import back.vybz.feed_service.common.entity.BaseResponseEntity;
import back.vybz.feed_service.feed.vo.response.ResponseAddReelsVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
public class BuskerReelsController {

  private final ReelsService reelsService;
    private final ReelsRepository reelsRepository;

    @Operation(
        summary = "버스커 릴스 등록 API",
        description = "버스커 릴스를 등록하는 API입니다.",
        tags = {"FEED-SERVICE"}
  )
    @PostMapping("/reels")
  public BaseResponseEntity<ResponseAddReelsVo> createReels(//HttpServletRequest httpServletRequest,
                                                            @RequestBody RequestAddReelsVo requestAddReelsVo){
      String writerUuid = "test-writer-uuid";
      //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
      RequestAddReelsDto requestAddReelsDto = RequestAddReelsDto.from(requestAddReelsVo, writerUuid);
        ResponseAddReelsDto responseAddReelsDto = reelsService.createReels(requestAddReelsDto);
      return new BaseResponseEntity<>(responseAddReelsDto.toVo());
  }

    @Operation(
            summary = "버스커 릴스 수정 API",
            description = "버스커 릴스를 수정하는 API입니다.",
            tags = {"FEED-SERVICE"}
    )
        @PutMapping("/reels/{reelsId}")
    public BaseResponseEntity<Void> updateReels(//HttpServletRequest httpServletRequest,
                                                @PathVariable("reelsId") String reelsId,
                                                @RequestBody RequestUpdateReelsVo requestUpdateReelsVo) {
        //String writerUuid = httpServletRequest.getHeader("X-USER-Id");
        String writerUuid = "test-writer-uuid";
        reelsService.updateReels(RequestUpdateReelsDto.of(reelsId,requestUpdateReelsVo,writerUuid));
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

