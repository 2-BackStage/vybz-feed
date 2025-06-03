package back.vybz.feed_service.feed.domain.mongodb;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaggedHuman {

    //공통 UUID
    private String uuid;
    // 휴먼 타입(Busker, User)
    private HumanType humanType;
    // 닉네임
    private String nickname;
}
