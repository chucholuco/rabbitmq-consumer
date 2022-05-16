package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SpringPictureConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPictureConsumer.class);

    private ObjectMapper objectMapper;

    public SpringPictureConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);
        LOG.info("Consuming image {}", picture.getName());
        if (picture.getSize() > 9000) {
            throw new IllegalArgumentException("Image too large: " + picture.getName());
        }
        LOG.info("Processing image: " + picture.getName());
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);
        LOG.info("Consuming vector {}", picture.getName());

        LOG.info("Processing vector: " + picture.getName());
    }

}
