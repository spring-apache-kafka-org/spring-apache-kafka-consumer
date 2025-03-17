// Copyright (c) 2023 Ion Mamaliga Ltd.

package ion.mamaliga.spring.apache.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class SpringApacheKafkaConsumerApplication {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(SpringApacheKafkaConsumerApplication.class);

        final ConfigurableApplicationContext applicationContext = springApplication.run(args);
        final Environment environment = applicationContext.getEnvironment();

        // @formatter:on
        log.info("""
                                 
                                 -------------------------------------------------------
                                 \tApplication: \t'{}' is running!
                                 \tLocal:\t\t\thttp://localhost:{}
                                 \tContext Path:\t{}
                                 \tProfiles:\t\t{}
                                 
                                 -------------------------------------------------------""",
                 environment.getProperty("spring.application.name"),
                 environment.getProperty("server.port"),
                 environment.getProperty("server.servlet.context-path"),
                 environment.getActiveProfiles());
        // @formatter:off
    }
}
