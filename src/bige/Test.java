
package bige;

public class Test {
public static void main(String[] args) {
	
	
	String jmx1 = System.getProperty("com.sun.management.jmxremote");
	String jmx2 = System.getProperty("com.sun.management.jmxremote.port");
	String jmx3 = System.getProperty("com.sun.management.jmxremote.authenticate");
	String jmx4 = System.getProperty("com.sun.management.jmxremote.ssl");
	String jmx5 = System.getProperty("java.rmi.server.hostname");
	System.out.println(jmx3);
	
}
}
