package bigeIntro;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerBasic {
	
	public static void main(String[] args) {
		
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		String topic="BasicTopic";
		
		KafkaProducer<Integer, String> producer=new KafkaProducer<>(props);
		ProducerRecord<Integer, String> producerRecord=null;
		
		producerRecord = new ProducerRecord<Integer, String>(topic, 1, "ABC");
		producer.send(producerRecord);
		System.out.println("Record sent");
		producer.close();
	}

}
