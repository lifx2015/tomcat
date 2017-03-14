package com.unsky.servers.tomcat.paramecium;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	private String url;
	private InputStream ins;
	
	public Request(InputStream ins) {
		super();
		this.ins = ins;
	}

	public static void main(String[] args) {
	}

	public void parse() throws IOException {
		StringBuffer request=new StringBuffer();
		int len=0;
		byte[] buffer=new byte[1024];
		len=ins.read(buffer);
		request.append(new String(buffer,0,len));
		Logger.log(request.toString());
		this.url=parseUrl(request);
		
		
	}
	/**
	 * 解析路径
	 * @param request
	 * @return
	 */
	private String parseUrl(StringBuffer request){
		
		int start=request.indexOf(" ");
		if(start!=-1){
			int end = request.indexOf(" ", start+1);
			if(end>start){
				return request.subSequence(start+1, end).toString();
			}
		}
		return null;
	}
	public InputStream getIns() {
		return ins;
	}

	public void setIns(InputStream ins) {
		this.ins = ins;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
