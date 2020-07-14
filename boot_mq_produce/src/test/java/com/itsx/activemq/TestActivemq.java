package com.itsx.activemq;

import com.itsx.activemq.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = BootMqProduceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActivemq {

    @Resource
    private Queue_Produce queue_produce;

    @Test
    public void testSend() throws Exception {
        queue_produce.produceMsg();
    }


}
