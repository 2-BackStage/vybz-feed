package back.vybz.feed_service.kafka.config;

import back.vybz.feed_service.kafka.event.ReelsSearchCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class ReelsSearchCreateEventConfig {

    private final CommonKafkaProducerConfig commonKafkaProducerConfig;

    @Bean
    public ProducerFactory<String, ReelsSearchCreateEvent> reelsSearchCreateEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonKafkaProducerConfig.producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ReelsSearchCreateEvent> reelsSearchCreateEventKafkaTemplate() {
        return new KafkaTemplate<>(reelsSearchCreateEventProducerFactory());
    }
}
