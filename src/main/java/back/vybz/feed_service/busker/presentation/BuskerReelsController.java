package back.vybz.feed_service.busker.presentation;

import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.busker.application.service.BuskerReelsService;
import back.vybz.feed_service.busker.vo.request.RequestAddReelsVo;
import back.vybz.feed_service.busker.vo.request.RequestUpdateReelsVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reels")
public class BuskerReelsController {

    private final BuskerReelsService buskerReelsService;

    @Operation(
            summary = "Reels 생성 API",
            description = "버스커 Reels를 생성하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAddReelsDto> createReels(
            @ModelAttribute RequestAddReelsVo requestAddReelsVo,
            @RequestPart("videoFile") MultipartFile videoFile
    ) {

        RequestAddReelsDto requestAddReelsDto = RequestAddReelsDto.from(requestAddReelsVo);
        requestAddReelsDto.setVideoFile(videoFile);

        return ResponseEntity.ok(buskerReelsService.createReels(requestAddReelsDto));
    }

    @Operation(
            summary = "Reels 수정 API",
            description = "버스커 Reels를 수정하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReels(@PathVariable("id") String feedId,
                                            @RequestBody RequestUpdateReelsVo requestUpdateReelsVo) {
        RequestUpdateReelsDto requestUpdateReelsDto = RequestUpdateReelsDto.from(feedId, requestUpdateReelsVo);
        buskerReelsService.updateReels(requestUpdateReelsDto);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Reels 삭제 API",
            description = "버스커 Reels를 삭제하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReels(@PathVariable("id") String feedId) {
        buskerReelsService.deleteReels(new ObjectId(feedId));
        return ResponseEntity.ok().build();
    }
}

