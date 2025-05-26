package back.vybz.feed_service.busker.domain.mongodb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum TargetType {

    NOTICE("공지"),
    REELS("릴스"),
    IMAGE("이미지"),
    FEED("피드");

    private final String description;
}
