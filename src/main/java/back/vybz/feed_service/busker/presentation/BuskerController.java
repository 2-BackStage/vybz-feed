package back.vybz.feed_service.busker.presentation;

import back.vybz.feed_service.busker.application.service.BuskerNoticeService;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseNoticeDto;
import back.vybz.feed_service.busker.vo.response.ResponseNoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class BuskerController {

    private final BuskerNoticeService buskerNoticeService;

    @PostMapping("/notice")
    public ResponseEntity<ResponseNoticeVo> createNotice(@RequestBody RequestAddNoticeDto requestAddNoticeDto) {
        ResponseNoticeDto noticeDto = buskerNoticeService.createNotice(requestAddNoticeDto);
        return ResponseEntity.ok(noticeDto.toVo());
    }

}



