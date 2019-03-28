package com.liwy.study.mq.rabbitmq;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring整合rabbitmq测试类
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SpringRabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testFanout_Producer() {
        rabbitTemplate.convertAndSend("发布消息啦！！！");
    }
}
