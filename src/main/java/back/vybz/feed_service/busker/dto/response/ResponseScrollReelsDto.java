package back.vybz.feed_service.busker.dto.response;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.vo.response.ResponseScrollReelsVo;
import back.vybz.feed_service.common.util.CursorPage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollReelsDto {

    private List<ResponseScrollReelsVo> content;
    private boolean hasNext;
    private String nextCursor;

    @Builder
    public ResponseScrollReelsDto(List<ResponseScrollReelsVo> content,
                                  boolean hasNext,
                                  String nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }

    public static ResponseScrollReelsDto from(CursorPage<BuskerFeed> cursorPage) {
        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}
