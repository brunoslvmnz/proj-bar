package com.caixa.Caixa.msg;

import com.caixa.Caixa.entidades.Caixa;
import com.caixa.Caixa.entidades.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CaixaEnviaMensagem {

    @Value("${caixa.rabbitmq.exchange}")
    String exchange;

    @Value("${caixa.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public CaixaEnviaMensagem(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Log<Caixa> caixaLog) {
        rabbitTemplate.convertAndSend(exchange, routingKey, caixaLog);
    }

}
