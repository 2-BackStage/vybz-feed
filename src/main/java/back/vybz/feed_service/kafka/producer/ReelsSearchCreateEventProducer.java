package back.vybz.feed_service.kafka.producer;

import back.vybz.feed_service.kafka.event.ReelsSearchCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReelsSearchCreateEventProducer {

    private final KafkaTemplate<String, ReelsSearchCreateEvent> kafkaTemplate;
    public static final String TOPIC_NAME = "create-reels-search";

    public void send(ReelsSearchCreateEvent event){
        log.info("[Kafka] sending ReelsSearchCreateEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String, ReelsSearchCreateEvent>> future =
                kafkaTemplate.send(TOPIC_NAME, event.getId(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send ReelsSearchCreateEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent ReelsSearchCreateEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
