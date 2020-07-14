package com.itsx.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Spring_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Spring_Consumer  sm = (Spring_Consumer)ac.getBean("spring_Consumer");
        String s = (String) sm.jmsTemplate.receiveAndConvert();
        System.out.println(" *** 消费者消息"+s);
    }
}
