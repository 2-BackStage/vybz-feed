package back.vybz.feed_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
                // 실패 시 재시도 로직 (선택사항)
                retrySend(topicName, event, 3);
            } else {
                log.info("[Kafka] Successfully sent to topic '{}', offset={}", topicName, result.getRecordMetadata().offset());
            }
        });
    }

    /**
     * 동기식 전송 (중요한 이벤트의 경우)
     */
    public void sendSync(String topicName, Object event) {
        try {
            log.info("[Kafka] Sending sync to topic '{}': {}", topicName, event);
            SendResult<String, Object> result = kafkaTemplate.send(topicName, event).get(10, TimeUnit.SECONDS);
            log.info("[Kafka] Successfully sent sync to topic '{}', offset={}", topicName, result.getRecordMetadata().offset());
        } catch (Exception e) {
            log.error("[Kafka] Failed to send sync to topic '{}': {}", topicName, e.getMessage(), e);
            throw new RuntimeException("Kafka sync send failed", e);
        }
    }

    /**
     * 재시도 로직
     */
    private void retrySend(String topicName, Object event, int retryCount) {
        if (retryCount <= 0) {
            log.error("[Kafka] Max retry count reached for topic '{}'", topicName);
            return;
        }

        log.info("[Kafka] Retrying send to topic '{}', retry count: {}", topicName, retryCount);
        
        CompletableFuture<SendResult<String, Object>> retryFuture = kafkaTemplate.send(topicName, event);
        
        retryFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                log.warn("[Kafka] Retry failed for topic '{}', retry count: {}", topicName, retryCount);
                // 지수 백오프로 재시도 간격 증가
                try {
                    Thread.sleep(1000 * (4 - retryCount)); // 1초, 2초, 3초
                    retrySend(topicName, event, retryCount - 1);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    log.error("[Kafka] Retry interrupted for topic '{}'", topicName);
                }
            } else {
                log.info("[Kafka] Retry successful for topic '{}', offset={}", topicName, result.getRecordMetadata().offset());
            }
        });
    }
}
