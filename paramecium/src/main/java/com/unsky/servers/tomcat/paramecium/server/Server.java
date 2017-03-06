package com.unsky.servers.tomcat.paramecium.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server;
	public Server() throws IOException {
		setServer(new ServerSocket(8081));
	}
	public void listener() throws IOException{
		while(true){
			System.out.println("is listening");
			Socket client = server.accept();
			dealRquest(client);
		}
		
	}
	public void dealRquest(Socket client ) throws IOException{
		System.out.println("deal request");
		OutputStream out = client.getOutputStream();
		InputStream in = client.getInputStream();
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		System.out.println(reader.readLine());
		writer.write("Reqeust has been done!");
		writer.flush();
		client.close();
	}
	
	public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	
	public static void main(String[] args) throws IOException {
		Server server=new Server();
		server.listener();
	}
}
