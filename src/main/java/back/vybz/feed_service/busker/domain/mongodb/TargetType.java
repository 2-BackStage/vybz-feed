package back.vybz.feed_service.busker.domain.mongodb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum TargetType {

    NOTICE("공지", "notice"),
    BUSKER_FEED("버스커 피드", "busker_feed"),
    USER_FEED("유저 피드", "user_feed");

    private final String description;
    private final String collectionName;
}
