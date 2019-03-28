package com.liwy.study.mq.rabbitmq.spring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <b>名称：</b> 与spring整合的消费者<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/27 14:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Consumer.class);
    public void receiveMsg(String msg) {
        logger.info("=====================================");
        logger.info("我是消费者，我收到消息啦");
        logger.info(msg);
    }
}
