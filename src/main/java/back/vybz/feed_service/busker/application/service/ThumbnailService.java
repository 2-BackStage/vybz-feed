package back.vybz.feed_service.busker.application.service;

import java.io.File;

public interface ThumbnailService {
    File generateThumbnail(File videoFile);
}
