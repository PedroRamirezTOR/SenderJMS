package com.example.demo;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * A simple message producer which sends the message to ActiveMQ Broker
 * 
 * @author Mary.Zheng
 *
 */
@Service
public class QueueMessageProducer {

	private static final int NUM_MESSAGES = 5000;

	@Value("${activemq.broker-url}")
	private String activeMqBrokerUri;
	
	@Value("${activemq.user}")
	private String username;
	
	@Value("${activemq.password}")
	private String password;
	
	private long i =0;
	
	public void sendDummyMessagesToQueue(String queueName) {
		System.out.println("QueueMessageProducer started " + this.activeMqBrokerUri);
		ConnectionFactory connFactory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer msgProducer = null;
		try {
			connFactory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUri);
			connection = connFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			msgProducer = session.createProducer(session.createQueue(queueName));

			for (int i = 0; i < NUM_MESSAGES; i++) {
				TextMessage textMessage = session.createTextMessage("Message " + i);
				try {
					msgProducer.send(textMessage);
					System.out.println(textMessage);
				}catch(Exception e) {
					System.out.println("FAIL SENDING MESSAGE:"+i+". Retrying...");
					i--;
				}
			}
			System.out.println("QueueMessageProducer completed");
		} catch (JMSException e) {
			System.out.println("Caught exception: " + e.getMessage());
		} finally {
			try {
				if (msgProducer != null) {
					msgProducer.close();
				}
				if (session != null) {
					session.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException ignore) {
			}
		}
	}

	public void sendDummyMessagesToTopic(String topicName) {
		System.out.println("TopicMessageProducer started " + this.activeMqBrokerUri);
		ConnectionFactory connFactory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer msgProducer = null;
		try {
			connFactory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUri);
			connection = connFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			msgProducer = session.createProducer(session.createTopic(topicName));
			System.out.println("Iniciando envío...."+new Date());
			for (int i = 0; i < NUM_MESSAGES; i++) {
				TextMessage textMessage = session.createTextMessage("##################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################Sender 1 Message " + i);
				try {
					msgProducer.send(textMessage);
				}catch(Exception e) {
					System.out.println("Fail sending message:"+i+". Retrying...");
					i--;
				}
			}
			System.out.println("Envío Finalizado"+new Date());
		} catch (JMSException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
		try {
			if (msgProducer != null) {
				msgProducer.close();
			}
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Throwable ignore) {
		}
	}
	
	
	public void sendMessageToTopic(String topicName) {
		System.out.println("TopicMessageProducer started " + this.activeMqBrokerUri);
		ConnectionFactory connFactory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer msgProducer = null;
		try {
			connFactory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUri);
			connection = connFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			msgProducer = session.createProducer(session.createTopic(topicName));
			TextMessage textMessage = session.createTextMessage("##################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################Sender 1 Message " + i);
			i++;
			msgProducer.send(textMessage);
		} catch (JMSException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
		try {
			if (msgProducer != null) {
				msgProducer.close();
			}
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Throwable ignore) {
		}
	}
	
	
	
	
	
}
