package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.Notice;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<Notice> findWithScroll(String sortType, String lastId, int size);
}
