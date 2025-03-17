package ion.mamaliga.spring.apache.kafka.producer.configurations;

import ion.mamaliga.spring.apache.kafka.producer.component.ObjectDeserializer;
import ion.mamaliga.spring.apache.kafka.producer.dto.ObjectDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringApacheKafkaConfiguration {

    private final String bootstrapServers;
    private final ObjectDeserializer objectDeserializer;

    public SpringApacheKafkaConfiguration(final @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
                                          final ObjectDeserializer objectDeserializer) {
        this.bootstrapServers = bootstrapServers;
        this.objectDeserializer = objectDeserializer;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        final Map<String, Object> configs = getConfigs();
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        final ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return kafkaListenerContainerFactory;
    }

    @Bean
    public ConsumerFactory<String, ObjectDto> ojectConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getConfigs(), new StringDeserializer(), objectDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ObjectDto> objectListenerContainerFactory(ConsumerFactory<String, ObjectDto> ojectConsumerFactory) {

        final ConcurrentKafkaListenerContainerFactory<String, ObjectDto> kafkaListenerContainerFactory =
                        new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(ojectConsumerFactory);
        return kafkaListenerContainerFactory;
    }

    private Map<String, Object> getConfigs() {
        final Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
        return configs;
    }
}
