package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@Autowired
	private QueueMessageProducer jmsTemplate;

	@RequestMapping(value = "/produceQueue")
	public String produceQueue() {
		String result = "Done";
		jmsTemplate.sendDummyMessagesToQueue("TestQueue");
		return result;
	}
	
	
	@RequestMapping(value = "/produceTopic")
	public String produceTopic() {
		long ms = System.currentTimeMillis();
		String result = "Done";
		jmsTemplate.sendMessageToTopic("TestTopic");
		System.out.println("Total time:"+(System.currentTimeMillis()-ms));
		return result;
	}
}