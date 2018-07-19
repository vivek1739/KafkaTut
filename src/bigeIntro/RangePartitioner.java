package bigeIntro;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class RangePartitioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster arg5) {
		
		List<PartitionInfo> partitions = arg5.partitionsForTopic(topic);
		int numPartitions=partitions.size();
		
		if((Integer)key < 5)
		{
			System.out.println("key : "+key+"will go to parition 1");
			return 1;
		}
		else 
			return 2;
	}
	

}
