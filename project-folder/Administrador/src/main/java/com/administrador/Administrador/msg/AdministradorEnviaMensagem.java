package com.administrador.Administrador.msg;

import com.administrador.Administrador.entidades.Administrador;
import com.administrador.Administrador.entidades.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdministradorEnviaMensagem {

    @Value("${administrador.rabbitmq.exchange}")
    String exchange;

    @Value("${administrador.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public AdministradorEnviaMensagem(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Log<Administrador> administrador) {
        rabbitTemplate.convertAndSend(exchange, routingKey, administrador);
    }

}
