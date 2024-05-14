package com.apica.JournalingService.service;
    
import org.apache.kafka.clients.consumer.ConsumerConfig;

import com.apica.JournalingService.config.CommonProp.KafkaProp;
import com.apica.JournalingService.controller.JournalController;
import com.apica.JournalingService.model.JournalEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
@Component
public class KafkaConsumerService {

    @Autowired
    private JournalController journalController;
    @Autowired
    private KafkaProp kafkaProp;

    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void consumeJournalEntries() {
        log.info("Entering consumeJournalEntries");
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProp.getBootstrapServers());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProp.getGroupId());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProp.getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProp.getValueDeserializer());

        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Collections.singletonList(kafkaProp.getTopicName()));
            while (true) {
                log.debug("Entering blocking-poll");
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                log.debug("Exiting blocking-poll with data:{}", records);
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        String jsonString = record.value();
                        JournalEntry journalEntry = objectMapper.readValue(jsonString, JournalEntry.class);
                        processJournalEntry(journalEntry);
                    } catch (Exception e) {
                        log.error("Exception in consumeJournalEntries. Message:{} StackTrace:{}", 
                        e.getMessage(), e.getStackTrace());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception in consumeJournalEntries. Message:{} StackTrace:{}", 
            e.getMessage(), e.getStackTrace());
        }
        log.info("Exiting consumeJournalEntries");
    }

    private void processJournalEntry(JournalEntry journalEntry) {
        log.info("Received data:{} in processJournalEntry", journalEntry);
        // Store data to redis and make controller to fetch those data
        journalController.createJournal(journalEntry);
        log.info("Exiting processJournalEntry");
    }
}