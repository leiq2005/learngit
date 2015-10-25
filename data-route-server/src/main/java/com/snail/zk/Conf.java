package com.snail.zk;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Conf {
	public static String connect = "localhost:2181";
	public static String IP_PORT = null;
	static int timeout = 3000;
	static{
		try {
			InetAddress a = InetAddress.getLocalHost();
			IP_PORT = a.getHostAddress() + System.currentTimeMillis();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		_.out(connect);;
		
	}
}
