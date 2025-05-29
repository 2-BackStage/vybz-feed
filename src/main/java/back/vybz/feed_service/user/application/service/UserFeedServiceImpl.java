package back.vybz.feed_service.user.application.service;

import back.vybz.feed_service.user.domain.mongodb.UserFeed;
import back.vybz.feed_service.user.dto.request.RequestAddFeedDto;
import back.vybz.feed_service.user.dto.response.ResponseAddFeedDto;
import back.vybz.feed_service.user.infrastructure.UserFeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFeedServiceImpl implements UserFeedService {

    private final UserFeedRepository userFeedRepository;

    /**
     * 사용자 피드 등록
     * @param requestAddFeedDto 사용자 피드 등록 요청 DTO
     *
     */
    @Override
    @Transactional
    public ResponseAddFeedDto createFeed(RequestAddFeedDto requestAddFeedDto){
        UserFeed userFeed = requestAddFeedDto.toEntity();
        UserFeed savedUserFeed = userFeedRepository.save(userFeed);
        return ResponseAddFeedDto.from(savedUserFeed);
    }


}
