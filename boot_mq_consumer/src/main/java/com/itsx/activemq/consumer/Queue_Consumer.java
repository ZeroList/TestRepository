package com.itsx.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class Queue_Consumer {

    @JmsListener(destination = "${myqueue}")
    private void receive(TextMessage textMessage) throws JMSException {
        System.out.println("********消费者受到的消息："+textMessage.getText());
    }

}
