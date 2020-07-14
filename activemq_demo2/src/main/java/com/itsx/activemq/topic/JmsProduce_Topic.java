package com.itsx.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_Topic {

    public final static String ACTIVEMQ_URL = "tcp://39.108.94.119:61616";
    public final static String TOPIC_NAME = "ItSix";


    public static void main(String[] args) throws JMSException {

        // 1 创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 连接工厂
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3 创建会话,两个参数分别是（事务，签收）
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（即 队列queue还是主题topic）
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5 创建生产者
        MessageProducer messageProducer = session.createProducer(topic);

        for (int i = 1; i <= 3 ; i++) {
            // 6 创建消息
            TextMessage textMessage = session.createTextMessage("小白" + i + "浩");
            // 7 发送消息
            messageProducer.send(textMessage);


        }

        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息主题初始。。。。结束");
    }



}
