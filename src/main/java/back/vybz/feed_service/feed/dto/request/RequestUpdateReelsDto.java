package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.vo.request.RequestUpdateReelsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class RequestUpdateReelsDto {

    private String id;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;

    @Builder
    public RequestUpdateReelsDto(String id,
                                 String content,
                                 List<String> humanTag,
                                 List<String> hashTag) {
        this.id = id;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
    }

    public static RequestUpdateReelsDto from(String id,
                                             RequestUpdateReelsVo requestUpdateReelsVo) {
        {
            return RequestUpdateReelsDto.builder()
                    .id(id)
                    .content(requestUpdateReelsVo.getContent())
                    .humanTag(requestUpdateReelsVo.getHumanTag())
                    .hashTag(requestUpdateReelsVo.getHashTag())
                    .build();
        }
    }
    public Map<String, Object> toUpdateMap() {
        Map<String, Object> updateMap = new HashMap<>();
        if (content != null) updateMap.put("content", content);
        if (humanTag != null) updateMap.put("humanTag", humanTag);
        if (hashTag != null) updateMap.put("hashTag", hashTag);
        return updateMap;
    }
}