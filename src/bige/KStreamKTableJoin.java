package bige;

import java.nio.file.SecureDirectoryStream;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

public class KStreamKTableJoin {
	public static void main(String[] args) {
		Properties props=new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG,"kstream-ktable-join-example");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());
		props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		
		final Serde<String> StringSerde = Serdes.String();
	    final Serde<Long> longSerde=Serdes.Long();
	    
	    KStreamBuilder builder=new KStreamBuilder();
	    
	    KStream<String, String> textLines=builder.stream(StringSerde, StringSerde, "LinesStreamForJoin")
	    		.map((key,word) -> new KeyValue(word, word+":modified"))
	    		.through("tmpLinesStreamForJoin");
	    
	    
	    
	    			
	    textLines.to(StringSerde, StringSerde, "WordsWithCategory");
	    
	    KafkaStreams streams=new KafkaStreams(builder, props);
	    streams.start();
	} 

}
