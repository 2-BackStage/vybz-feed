package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import org.bson.types.ObjectId;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<Notice> findWithScroll(String sortType, ObjectId lastId, int size);
}
