package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;

import java.util.List;
import java.util.Optional;

public interface NoticeRepositoryCustom {
    Optional<Feed> findNoticeById(String id);
}
