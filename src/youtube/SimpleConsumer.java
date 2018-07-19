package youtube;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	public static void main(String[] args) {
		
		String topicName="testTopic";
		/*String groupName="g5";
		
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", groupName);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer=new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topicName));
		
		ConsumerRecords<String, String> records=consumer.poll(1000);*/
		/*Set<TopicPartition> partitions = consumer.assignment();
		System.out.println(partitions);
		if(!partitions.isEmpty()) {
			consumer.seekToEnd(partitions);
			TopicPartition next = partitions.iterator().next();
			System.out.println(next);
			long position = consumer.position(next);
			System.out.println(position);
		}*/
		//records.forEach(record -> System.out.println("record : "+String.valueOf(record.value())));
		
		//consumer.close();
		KafkaConsumer<String, String> postMetaConsumer;


		Map<String, Object> config = KafkaConfig.getConsumerConfig();
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "post-manager");
		postMetaConsumer = new KafkaConsumer<>(config);
		postMetaConsumer.subscribe(Arrays.asList(topicName));
		ConsumerRecords<String, String> records = postMetaConsumer.poll(1000);
		for(ConsumerRecord<String, String> record : records) {
			
			System.out.println(record);
			
		}
		postMetaConsumer.close();
		

	}

}

class KafkaConfig {
	
	public static Properties getProducerConfig() {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 0);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	
	}
	
	public static Map<String, Object> getConsumerConfig() {
		
		 Map<String, Object> config = new HashMap<>();
		 config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		 config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		 config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		 config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		 config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		 return config;
	     
	}
}