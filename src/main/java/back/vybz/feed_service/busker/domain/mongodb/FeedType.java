package back.vybz.feed_service.busker.domain.mongodb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedType {

    REELS("릴스"),
    IMAGE("이미지");

    private final String description;

}
