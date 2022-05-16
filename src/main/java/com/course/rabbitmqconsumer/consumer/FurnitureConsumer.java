package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Furniture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
@AllArgsConstructor
public class FurnitureConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(FurnitureConsumer.class);
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.promotion.discount", "q.promotion.free-delivery"})
    public void listen(Message message) throws JsonProcessingException {
        var jsonString = new String(message.getBody());
        var furniture = objectMapper.readValue(jsonString, Furniture.class);
        LOG.info("Consuming furniture: {} with headers: {}", furniture, message.getMessageProperties().getHeaders());
    }
}
