package back.vybz.feed_service.busker.presentation;

import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.busker.application.service.BuskerReelsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
            @ModelAttribute RequestAddReelsDto requestAddReelsDto,
            @RequestPart("videoFile") MultipartFile videoFile
    ) {

        requestAddReelsDto.setVideoFile(videoFile);

        return ResponseEntity.ok(buskerReelsService.createReels(requestAddReelsDto));
    }
}
