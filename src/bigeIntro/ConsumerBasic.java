package bigeIntro;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class ConsumerBasic {
	public static void main(String[] args) {
		
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "ConsumerBasic");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<Integer, String> consumer=new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("BasicTopic"));
		
		
		while(true){
			ConsumerRecords<Integer, String> records = consumer.poll(100);
			records.forEach(record -> {
				System.out.println(record.topic()+"::"+record.partition()+"::"+record.offset());
			});
		}
	}

}
