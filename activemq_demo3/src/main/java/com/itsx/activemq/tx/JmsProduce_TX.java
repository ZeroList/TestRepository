package com.itsx.activemq.tx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_TX {

    public final static String ACTIVEMQ_URL = "tcp://39.108.94.119:61616";
    public final static String QUEUE_NAME = "ItSix";


    public static void main(String[] args) throws JMSException {


        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3 创建会话,两个参数分别是（事务，签收）
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i = 1; i <= 3 ; i++) {
            TextMessage textMessage = session.createTextMessage("小白" + i + "浩");
            producer.send(textMessage);
        }


        producer.close();
        session.commit();
        session.close();
        connection.close();

        System.out.println("消息队列初始。。。。完成");
    }



}
