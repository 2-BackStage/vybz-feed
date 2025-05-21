package back.vybz.feed_service.busker.domain.mongodb;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Location {

    // 위치 이름
    private String name;

    // 위도
    private Double latitude;

    // 경도
    private Double longitude;

}
