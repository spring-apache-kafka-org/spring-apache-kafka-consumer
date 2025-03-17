package ion.mamaliga.spring.apache.kafka.producer.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import ion.mamaliga.spring.apache.kafka.producer.dto.ObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ObjectDeserializer implements Deserializer<ObjectDto> {

    private final ObjectMapper objectMapper;

    public ObjectDeserializer(@Qualifier("jsonObjectMapper") ObjectMapper jsonObjectMapper) {
        this.objectMapper = jsonObjectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.debug("Overridden blank configure() method was invoked");
    }

    @Override
    public ObjectDto deserialize(String topic, byte[] data) {
        ObjectDto object = null;
        try {
            object = objectMapper.readValue(data, ObjectDto.class);
        } catch (Exception exception) {
            log.error("Error in deserializing bytes, ERROR=[]", exception);
        }
        return object;
    }

    @Override
    public void close() {
        log.debug("Overridden blank close() method was invoked");
    }
}
