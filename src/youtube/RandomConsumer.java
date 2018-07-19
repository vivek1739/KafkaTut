package youtube;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class RandomConsumer {
	
	public static void main(String[] args) {
		
		String topicName="RandomProducerTopic1";
		KafkaConsumer<String, String> consumer=null;
		
		String groupName="RG";
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");
        
        consumer=new KafkaConsumer<String,String>(props);
        
        RebalanceListener rl=new RebalanceListener(consumer);
        
        consumer.subscribe(Arrays.asList(topicName), rl);
        
        while(true){
        	ConsumerRecords<String, String> records=consumer.poll(100);
        	records.forEach(record -> {
        		System.out.println("Topic:"+ record.topic() +" Partition:" + record.partition() + " Offset:" + record.offset() + " Value:"+ record.value());
            rl.addOffset(record.topic(), record.partition(),record.offset());
        		
        	});
        	
        	consumer.commitSync(rl.getCurrentOffsets());
        	consumer.close();
        	
        	
        	
        
        }
	}

}
