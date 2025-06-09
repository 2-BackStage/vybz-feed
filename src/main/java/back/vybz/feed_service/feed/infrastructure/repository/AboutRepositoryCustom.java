package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;

public interface AboutRepositoryCustom {
    void updateAboutById(String id, RequestUpdateAboutDto requestUpdateAboutDto);
}
