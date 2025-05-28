package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThumbnailServiceImpl implements ThumbnailService {


    @Override
    @Transactional
    public File generateThumbnail(File videoFile) {
        try {
            File thumbnailFile = File.createTempFile("thumbnail", ".jpg");

            String ffmpegPath = "C:\\ffmpeg-7.1.1-essentials_build\\bin\\ffmpeg.exe";

            ProcessBuilder processBuilder = new ProcessBuilder(
                    ffmpegPath,
                    "-ss", "00:00:00.500",
                    "-i", videoFile.getAbsolutePath(),
                    "-frames:v", "1",
                    "-y",
                    thumbnailFile.getAbsolutePath()
            );

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();


            boolean finished = process.waitFor(10, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                throw new BaseException(BaseResponseStatus.THUMBNAIL_CREATE_FAILED);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info("[ffmpeg] {}", line);
                }
            }

            int exitCode = process.exitValue();

            if (exitCode != 0 || !thumbnailFile.exists() || thumbnailFile.length() == 0) {
                throw new BaseException(BaseResponseStatus.THUMBNAIL_CREATE_FAILED);
            }

            return thumbnailFile;

        } catch (IOException | InterruptedException e) {
            throw new BaseException(BaseResponseStatus.THUMBNAIL_CREATE_FAILED);
        }
    }


}



