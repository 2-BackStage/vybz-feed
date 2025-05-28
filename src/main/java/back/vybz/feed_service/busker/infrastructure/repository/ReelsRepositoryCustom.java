package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import com.mongodb.client.result.UpdateResult;

public interface ReelsRepositoryCustom {
    UpdateResult updateReels(String feedId, RequestUpdateReelsDto requestUpdateReelsDto);
}
