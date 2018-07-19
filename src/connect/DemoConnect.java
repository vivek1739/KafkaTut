package connect;

import java.util.HashMap;

public class DemoConnect {
	public static void main(String[] args) {
	
		MongodbSinkTask mongodbSinkTask=new MongodbSinkTask();
		mongodbSinkTask.start(new HashMap<String,String>());
		
		
	}

}
