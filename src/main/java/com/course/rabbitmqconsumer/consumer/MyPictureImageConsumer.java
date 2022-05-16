package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaderMapper;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@AllArgsConstructor
public class MyPictureImageConsumer {

    public static final Logger LOG = LoggerFactory.getLogger(MyPictureImageConsumer.class);
    private final ObjectMapper objectMapper;



    @RabbitListener(queues = "q.mypicture.image")
    public void listen(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        var picture = objectMapper.readValue(message, Picture.class);
        if (picture.getSize() > 9000) {
            channel.basicReject(tag, false);
            return;
//            throw new AmqpRejectAndDontRequeueException("Picture size to large :" + picture);
        }
        LOG.info("On image: {}", picture);
        channel.basicAck(tag, false);
    }
}
