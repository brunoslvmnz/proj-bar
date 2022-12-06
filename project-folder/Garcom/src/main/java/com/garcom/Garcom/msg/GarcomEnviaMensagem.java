package com.garcom.Garcom.msg;

import com.garcom.Garcom.entidades.Garcom;
import com.garcom.Garcom.entidades.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GarcomEnviaMensagem {

    @Value("${garcom.rabbitmq.exchange}")
    String exchange;

    @Value("${garcom.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public GarcomEnviaMensagem(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Log<Garcom> garcomLog) {
        rabbitTemplate.convertAndSend(exchange, routingKey, garcomLog);
    }

}
