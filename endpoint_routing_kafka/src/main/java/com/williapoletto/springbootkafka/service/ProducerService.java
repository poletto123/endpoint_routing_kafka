package com.williapoletto.springbootkafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	private KafkaTemplate<String, Object> kafkaTemplate;
	
	private final String topic;
	
	@Autowired
	public ProducerService(KafkaTemplate<String, Object> kafkaTemplate,
							@Value("${kafka.topicName}") String topic) {
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	 void sendMessageWithCallback(Object object) {
		    ListenableFuture<SendResult<String, Object>> future = 
		      kafkaTemplate.send(topic, object);
		  
		    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
		      @Override
		      public void onSuccess(SendResult<String, Object> result) {
		        logger.info("Message [{}] delivered with offset {}",
		          object,
		          result.getRecordMetadata().offset());
		      }
		  
		      @Override
		      public void onFailure(Throwable ex) {
		        logger.warn("Unable to deliver message [{}]. {}", 
		          object,
		          ex.getMessage());
		      }
		    });
		  }

}
