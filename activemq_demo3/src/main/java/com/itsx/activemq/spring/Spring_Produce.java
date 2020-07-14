package com.itsx.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.jms.TextMessage;

@Service
public class Spring_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Spring_Produce producer = (Spring_Produce) ctx.getBean("spring_Produce");
        producer.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("spring 和 activemq 的整合----Listener");
            return textMessage;
        });
        System.out.println(" *** send task over ***");
    }

}
