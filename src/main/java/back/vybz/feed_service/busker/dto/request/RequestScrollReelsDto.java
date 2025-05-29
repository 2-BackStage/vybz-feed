package back.vybz.feed_service.busker.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class RequestScrollReelsDto {

    private String lastCreatedAt;
    private String buskerUuid;;
    private int size = 5;

}
