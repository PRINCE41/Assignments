package com.apica.UserMngService.service;

import com.apica.UserMngService.config.CommonProp.KafkaProp;
import com.apica.UserMngService.model.JournalEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Component
public class KafkaPublisher {

    @Autowired
    private KafkaProp kafkaProp;

    private KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;

    public KafkaPublisher(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void createKafkaProducer() {
        log.info("Inside createKafkaProducer");
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProp.getBootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProp.getKeySerializer());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProp.getValueSerializer());
        this.kafkaProducer = new KafkaProducer<>(properties);
    }

    public void publishJournalEntry(String userId, String action) {
        log.info("Id:{} Entering publishJournalEntry", userId);
        JournalEntry journalEntry = new JournalEntry(userId, action, new Date());
        try {
            String json = objectMapper.writeValueAsString(journalEntry);
            kafkaProducer.send(new ProducerRecord<>(kafkaProp.getTopicName(), json));
        } catch (Exception e) {
            log.error("Id:{} Error in publishJournalEntry. Message:{} StackTrace:{}", userId, e.getMessage(), e.getStackTrace());
        }
        log.info("Id:{} Exiting publishJournalEntry", userId);
    }

    
}

