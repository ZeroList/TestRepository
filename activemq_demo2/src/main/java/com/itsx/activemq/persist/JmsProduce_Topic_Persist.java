package com.itsx.activemq.persist;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_Topic_Persist {

    public final static String ACTIVEMQ_URL = "tcp://39.108.94.119:61616";
    public final static String TOPIC_NAME = "ItSix";


    public static void main(String[] args) throws JMSException {

        System.out.println("******生产者*********");
        // 1 创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 连接工厂
        Connection connection = activeMQConnectionFactory.createConnection();
        // 3 创建会话,两个参数分别是（事务，签收）
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（即 队列queue还是主题topic）

        Topic topic = session.createTopic(TOPIC_NAME);
        //创建主题的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //配置生产者持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 启动生产
        connection.start();
        //接收数据
        for (int i = 1; i < 4 ; i++) {
            // 7  创建字消息
            TextMessage textMessage = session.createTextMessage("topic_name--" + i);
            // 8  通过messageProducer发布消息
            messageProducer.send(textMessage);
        }


        //关闭资源
        session.close();
        connection.close();

        System.out.println("消息主题初始。。。。结束");
    }



}
