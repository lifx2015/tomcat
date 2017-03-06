package com.unsky.servers.tomcat.paramecium.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket client;
	public Client() throws UnknownHostException, IOException {
		client=new Socket("127.0.0.1", 8081);
	}
	public void sendRequest() throws IOException{
		OutputStream out = client.getOutputStream();
		
		InputStream in = client.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		System.out.println(reader.readLine());
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
		writer.write("Send A Request");
		writer.flush();
		writer.close();
		reader.close();
		client.close();
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client=new Client();
		client.sendRequest();
	}
	
}
