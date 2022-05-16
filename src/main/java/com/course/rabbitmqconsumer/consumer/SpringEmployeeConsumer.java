package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class SpringEmployeeConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringEmployeeConsumer.class);

    private ObjectMapper objectMapper;

    public SpringEmployeeConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.spring2.accounting.work")
    public void listenAccounting(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOG.info("On accounting, consuming {}", employee);
        if (StringUtils.isEmpty(employee.getName())) {
            throw new IllegalArgumentException("Name is empty");
        }
        LOG.info("On accounting, employee is {}", employee);
    }

    @RabbitListener(queues = "q.spring2.marketing.work")
    public void listenMarketing(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOG.info("On marketing, consuming {}", employee);
        LOG.info("On marketing, employee is {}", employee);
    }
}
