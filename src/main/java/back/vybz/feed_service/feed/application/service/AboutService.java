package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.feed.dto.request.RequestAddAboutDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;

public interface AboutService {
    void createAbout(RequestAddAboutDto requestAddAboutDto);
    void updateAbout(RequestUpdateAboutDto requestUpdateAboutDto);
    void deleteAbout(String aboutId, String writerUuid);
}

