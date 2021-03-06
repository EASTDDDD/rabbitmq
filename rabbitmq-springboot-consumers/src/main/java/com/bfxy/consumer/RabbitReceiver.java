package com.bfxy.consumer;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bfxy.entity.Order;
import com.rabbitmq.client.Channel;

@Component
public class RabbitReceiver {

	/**
	 * 消息监听
	 * @param message
	 * @param channel
	 * @throws IOException 
	 */
	/*@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "queue-1", durable = "true"),
			exchange = @Exchange(value = "exchange-1", durable = "true", type="topic",
			ignoreDeclarationExceptions = "true"),
			key = "springboot.*"
			
			))
	@RabbitHandler
	public void onMessage(Message message,Channel channel) throws IOException {
		System.out.println("------------消费端----------------------");
		System.out.println("消费端：" + message.getPayload());
		Long deliverTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		
		//手动ack
		channel.basicAck(deliverTag, false);
	}*/
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
			durable = "${spring.rabbitmq.listener.order.queue.durable}"),
			exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
			durable = "${spring.rabbitmq.listener.order.exchange.durable}",
			type="${spring.rabbitmq.listener.order.exchange.type}",
			ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
			key = "${spring.rabbitmq.listener.order.key}"
			
			))
	@RabbitHandler
	public void onMessage(@Payload Order order,Channel channel,@Headers Map<String, Object> headres) throws IOException {
		System.out.println("------------消费端----------------------");
		System.out.println("消费端：" + order.getId());
		Long deliverTag = (Long) headres.get(AmqpHeaders.DELIVERY_TAG);
		
		//手动ack
		channel.basicAck(deliverTag, false);
	}
	
}
