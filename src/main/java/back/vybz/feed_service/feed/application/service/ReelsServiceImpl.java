package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.feed.infrastructure.repository.ReelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReelsServiceImpl implements ReelsService {

    private final ReelsRepository reelsRepository;

    /**
     * 릴스등록
     */
    @Override
    @Transactional
    public ResponseAddReelsDto createReels(RequestAddReelsDto requestAddReelsDto){
        try {
            Feed feed = requestAddReelsDto.toEntity();
            Feed saved = reelsRepository.save(feed);
            return ResponseAddReelsDto.from(saved);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.REELS_SAVE_FAILED);
        }
    }

    /**
     * 릴스 수정
     */
    @Override
    @Transactional
    public void updateReels(RequestUpdateReelsDto requestUpdateReelsDto){
        Feed feed = reelsRepository.findById(requestUpdateReelsDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REELS_NOT_FOUND));

        if(!feed.getWriterUuid().equals(requestUpdateReelsDto.getWriterUuid())){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_REELS);
        }

        reelsRepository.updateReelsById(requestUpdateReelsDto.getId(), requestUpdateReelsDto);
    }

    /**
     * 릴스 삭제
     */
    @Override
    @Transactional
    public void deleteReels(String reelsId,String writerUuid){
        Feed feed = reelsRepository.findById(reelsId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REELS_NOT_FOUND));

        if(!feed.getWriterUuid().equals(writerUuid)){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_REELS);
        }

        reelsRepository.delete(feed);
    }

}
