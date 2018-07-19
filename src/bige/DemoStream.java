package bige;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Reducer;

public class DemoStream {
	public static void main(String[] args) throws InterruptedException {
		Properties props=new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStream28");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		//kafka streams do not use zookeeper anymore
		//props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		//props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig);
		
		
		KStreamBuilder builder=new KStreamBuilder();
		//builder.ta
		
		KStream<String, String> stream=builder.stream("SimpleProducerTopic")
				.map((key,value)-> new KeyValue(key, "Streamed"+value));
		
		//KTable<String, String> table = builder.table("SimpleProducerTopic");

		//stream.foreach((x,y)->System.out.println("key"+x+"  value:"+y));
				
				KGroupedStream<String, String> groupedStream = stream.groupByKey();
		
				groupedStream.reduce((v1,v2)->v1+","+v2).toStream().foreach((k,v)->System.out.println("key:"+k+" \nvalue:"+v));
				
				//table.to("NewTable");
		//table.to(Serdes.String(), Serdes.String(), "StreamTa");
		//stream.foreach((x,y)->System.out.println("key"+x+"  value:"+y));
		//stream.to(Serdes.String(), Serdes.String(), "Stream");
		
		KafkaStreams streams=new KafkaStreams(builder, props);
		streams.start();
		//Thread.currentThread().sleep(1000);
		//streams.close();
		//streams.cleanUp();
				
	}

}
