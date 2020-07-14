package com.itsx.activemq.persist;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://39.108.94.119:61616";
    public static final String TOPIC_NAME = "ItSix";  // 1对1 的队列

    public static void main(String[] args) throws Exception{
        System.out.println("******订阅者1111******");
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        Connection connection = activeMQConnectionFactory.createConnection();
        //设置连接ID
        connection.setClientID("lh");
        // 3 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建主题的订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark...");
        // 5 发布订阅
        connection.start();
        Message message = topicSubscriber.receive();// 阻塞式
        while (null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println(" 收到的持久化 topic ："+textMessage.getText());
            message = topicSubscriber.receive();    // 等1秒后meesage 为空，跳出循环，控制台关闭
        }

        session.close();
        connection.close();
    }
}
