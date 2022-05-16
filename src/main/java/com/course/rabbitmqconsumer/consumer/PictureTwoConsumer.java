package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class PictureTwoConsumer {

    public static final Logger LOG = LoggerFactory.getLogger(PictureTwoConsumer.class);
    private final ObjectMapper objectMapper;


    public PictureTwoConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = {"q.picture.image2", "q.picture.vector2", "q.picture.filter", "q.picture.log"})
    public void listen(Message message) throws JsonProcessingException {
        var jsonString = new String(message.getBody());
        var picture = objectMapper.readValue(jsonString, Picture.class);
        LOG.info("Consuming picture: {} with routing key: {}", picture, message.getMessageProperties().getReceivedRoutingKey());
    }
}
