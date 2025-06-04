package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddFanFeedDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddFanFeedDto;
import back.vybz.feed_service.feed.infrastructure.repository.FanFeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FanFeedServiceImpl implements FanFeedService {

    private final FanFeedRepository fanFeedRepository;

    /**
     * 팬 피드를 생성하는 메서드
     * @param requestAddFanFeedDto 팬 피드 생성 요청 DTO
     * @return 생성된 팬 피드 정보 DTO
     */
    @Override
    @Transactional
    public ResponseAddFanFeedDto createFanFeed(RequestAddFanFeedDto requestAddFanFeedDto){
        try{
            Feed feed = requestAddFanFeedDto.toEntity();
            Feed saved = fanFeedRepository.save(feed);
            return ResponseAddFanFeedDto.from(saved);
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.FAN_FEED_CREATE_FAIL);
        }
    }

    /**
     * 팬 피드를 업데이트하는 메서드
     * @param requestUpdateFanFeedDto 팬 피드 업데이트 요청 DTO
     */
    @Override
    @Transactional
    public void updateFanFeed(RequestUpdateFanFeedDto requestUpdateFanFeedDto){
        Feed feed = fanFeedRepository.findById(requestUpdateFanFeedDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAN_FEED_NOT_FOUND));

        if (!feed.getWriterUuid().equals(requestUpdateFanFeedDto.getWriterUuid())){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_FAN_FEED);
        }

        fanFeedRepository.updateFanFeedById(requestUpdateFanFeedDto.getId(),requestUpdateFanFeedDto);
    }

    /**
     * 팬 피드를 삭제하는 메서드
     * @param id 팬 피드 ID
     */
    @Override
    @Transactional
    public void deleteFanFeed(String fanFeedId,String writerUuid){
        Feed feed = fanFeedRepository.findById(fanFeedId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAN_FEED_NOT_FOUND));

        if (!feed.getWriterUuid().equals(writerUuid)){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_FAN_FEED);
        }

        fanFeedRepository.delete(feed);
    }

}
