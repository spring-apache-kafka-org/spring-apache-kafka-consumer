package ion.mamaliga.spring.apache.kafka.producer.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ion.mamaliga.spring.apache.kafka.producer.dto.ObjectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SpringApacheKafkaConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "my-topic1", groupId = "my-group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        log.debug("Received string message: {}, TOPIC=[{}]", message, "my-topic2");
    }

    @KafkaListener(topics = "my-topic2", groupId = "my-group-id", containerFactory = "objectListenerContainerFactory")
    public void listen(ConsumerRecord<String, ObjectDto> consumerRecord) {
        log.info("Received object message: {}, TOPIC=[{}]", consumerRecord.value(), "my-topic2");

        final ObjectDto objectDto = objectMapper.convertValue(consumerRecord.value(), ObjectDto.class);

        log.info("Finished maintaining messages {}", objectDto);
    }
}
