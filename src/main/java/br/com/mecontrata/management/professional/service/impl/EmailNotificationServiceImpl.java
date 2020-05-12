package br.com.mecontrata.management.professional.service.impl;

import br.com.mecontrata.management.professional.domain.ProfessionalDTO;
import br.com.mecontrata.management.professional.domain.avro.ProfessionalNotification;
import br.com.server.resource.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("emailNotification")
@Slf4j
public class EmailNotificationServiceImpl extends KafkaService<ProfessionalDTO, ProfessionalNotification> {

    @Value("${kafka.topic.email}")
    private String topicEmail;

    @Override
    protected String getTopic() {
        return topicEmail;
    }

    @Override
    protected ProfessionalNotification configureProducer(ProfessionalDTO professionalDTO) {
        ProfessionalNotification clientNotification = ProfessionalNotification.newBuilder()
                .setFirstName(professionalDTO.getFirstName())
                .setLastName(professionalDTO.getLastName())
                .setEmail(professionalDTO.getEmail())
                .build();
        return clientNotification;
    }

    @Override
    protected Callback notificationCallback() {
        return new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null) {
                    log.info(metadata.toString());
                } else {
                    log.error(exception.getMessage(), exception.getCause());
                }
            }
        };
    }
}
