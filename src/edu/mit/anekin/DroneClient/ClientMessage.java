package edu.mit.anekin.DroneClient;

import java.io.Serializable;

public class ClientMessage implements Serializable{

	private static final long serialVersionUID = -8598788652294683918L;

	private final String host;
	private final int port;
	private final byte[] bytes; 
	
	
	public ClientMessage(String host, int port, byte[] bytes){
		super();
		this.host = host;
		this.port = port;
		this.bytes = bytes; 
		
	}
	
	public String getHost(){
		return host;
	}
	
	public int getPort(){
		return port;
	}
	
	public byte[] getBytes(){
		return bytes;
	}

}
