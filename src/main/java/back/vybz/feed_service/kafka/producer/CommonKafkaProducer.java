package back.vybz.feed_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topicName, Object event) {
        log.info("[Kafka] Sending to topic '{}': {}", topicName, event);

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send to topic '{}': {}", topicName, ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent to topic '{}', offset={}", topicName, result.getRecordMetadata().offset());
            }
        });
    }
}
