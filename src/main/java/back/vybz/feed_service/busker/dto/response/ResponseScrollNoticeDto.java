package back.vybz.feed_service.busker.dto.response;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.vo.response.ResponseScrollNoticeVo;
import back.vybz.feed_service.common.util.CursorPage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollNoticeDto {

    private List<ResponseScrollNoticeVo> content;
    private boolean hasNext;
    private String nextCursor;

    @Builder
    public ResponseScrollNoticeDto(List<ResponseScrollNoticeVo> content,
                                   boolean hasNext,
                                   String nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }
    public static ResponseScrollNoticeDto from(CursorPage<Notice> cursorPage){
        return ResponseScrollNoticeDto.builder()
                .content(ResponseScrollNoticeVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}
