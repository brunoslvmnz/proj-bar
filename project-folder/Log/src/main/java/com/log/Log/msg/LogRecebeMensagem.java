package com.log.Log.msg;

import com.log.Log.entidades.Log;
import com.log.Log.servicos.LogServico;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogRecebeMensagem {
    @Autowired
    private LogServico logServico;

    @RabbitListener(queues = {"${log.rabbitmq.queue}"})
    public void receive(@Payload Log log) {
        logServico.criarLog(log);
    }
}
