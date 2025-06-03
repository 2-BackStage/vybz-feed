package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;

import java.util.Optional;

public interface NoticeRepositoryCustom {
    Optional<Feed> findNoticeById(String id);
    void updateNoticeFieldsById(String id, RequestUpdateNoticeDto requestUpdateNoticeDto);
}
