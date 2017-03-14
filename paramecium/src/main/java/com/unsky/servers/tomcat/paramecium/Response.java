package com.unsky.servers.tomcat.paramecium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private int BUFFER_SIZE=1024;
	private OutputStream  out;
	private Request request;
	public Response(OutputStream out) {
		this.out=out;
	}

	public void setRequest(Request request) {
		this.request=request;
	}
	public void setStaticResponse() throws IOException {
		byte[] buffer=new byte[BUFFER_SIZE];
		FileInputStream fis=null;
		File file = new File(HttpServer.WEB_ROOT,request.getUrl());
		try {
			if(file.exists()){
				fis=new FileInputStream(file);
				int len=0;
				while((len=fis.read(buffer))>0){
					out.write(buffer, 0, len);
				}
			}else{
				StringBuffer resHtml=new StringBuffer("HTTP/1.1 404  File NOT FOUND!");
				out.write(resHtml.toString().getBytes());
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log("文件没有找到");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("读写异常");
		}finally {
			if(fis!=null){
				fis.close();
			}
		}
	}

}
