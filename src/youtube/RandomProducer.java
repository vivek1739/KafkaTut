package youtube;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class RandomProducer {
	
public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
	
	String topicName="RandomProducerTopic1";
	String msg;
	
	Properties props=new Properties();

	props.put("bootstrap.servers", "localhost:9092,localhost:9093");
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	
	Producer<String, String> producer=new KafkaProducer(props);
	Random rg=new Random();
	Calendar dt=Calendar.getInstance();
	dt.set(2016, 1, 1);
	
	while(true){
		for( int i=0; i<100;i++){
			msg=dt.get(Calendar.YEAR)+"-"+dt.get(Calendar.MONTH)+"-"+dt.get(Calendar.DATE)+","+rg.nextInt(1000);
			producer.send(new ProducerRecord<String, String>(topicName,0, "Key", msg)).get();
			
			msg=dt.get(Calendar.YEAR)+"-"+dt.get(Calendar.MONTH)+"-"+dt.get(Calendar.DATE)+","+rg.nextInt(1000);
			producer.send(new ProducerRecord<String, String>(topicName,1, "Key", msg)).get();
		
			dt.add(Calendar.DATE, 1);
			
			System.out.println("Data sent for "+dt.get(Calendar.DATE));
			
			
		}
		producer.close();
	}
	
	
	
	
}
}
