package youtube;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class RebalanceListener implements ConsumerRebalanceListener{

	KafkaConsumer<String, String> consumer=null;
	
	private Map<TopicPartition,OffsetAndMetadata> currentOffsets=new HashMap<>();
	
	
	public RebalanceListener(KafkaConsumer<String, String> consumer) {
		// TODO Auto-generated constructor stub
		this.consumer=consumer;
	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub
		System.out.println("on partitions assigned called");
		System.out.println("Following Partitions Assigned ....");
        for(TopicPartition partition: partitions)
            System.out.println(partition.partition()+",");
	}

	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub
		  System.out.println("Following Partitions Revoked ....");
	        for(TopicPartition partition: partitions)
	            System.out.println(partition.partition()+",");
	        
	        System.out.println("following partitions committed");
	        for(TopicPartition tp:currentOffsets.keySet())
	        	System.out.println(tp.partition());
	        
	        consumer.commitSync(currentOffsets);
	        currentOffsets.clear();
		
	}

	public void addOffset(String topic, int partition, long offset) {
		// TODO Auto-generated method stub
		currentOffsets.put(new TopicPartition(topic, partition) , new OffsetAndMetadata(offset, "Commit"));
		
	}

	public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
		// TODO Auto-generated method stub
		return currentOffsets;
	}

}
