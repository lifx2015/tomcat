package com.unsky.servers.tomcat.paramecium;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	public static final String WEB_ROOT="G:"+File.separator+"webapp";
	public static final String SHUTDOWN_CMD="SHUTDOWN";
	private boolean shutdown=true;
	private int port=80;
	public void await() throws IOException{
		ServerSocket serverSocket=new ServerSocket(port);
		while(shutdown){
			Logger.log("有连接接入！");
			Socket client=serverSocket.accept();
			InputStream ins = client.getInputStream();
			OutputStream out = client.getOutputStream();
			Request request=new Request(ins);
			request.parse();
			Response response=new Response(out);
			response.setRequest(request);
			response.setStaticResponse();
			client.close();
//			/shutdown=request.getUrl().equals(SHUTDOWN_CMD);
			 	
		}
	}
	public static void main(String[] args) throws IOException {
		new HttpServer().await();
	}
	
}
