package bige;

import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Initializer;

public class LatestAggregator<K,V,T> implements Aggregator<String, String, String>, Initializer<String> {

	@Override
	public String apply() {
		// TODO Auto-generated method stub
		return "UNKNOWN";
	}

	@Override
	public String apply(String key, String curVal, String aggr) {
		// TODO Auto-generated method stub
		return curVal;
	}

}
