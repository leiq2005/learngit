package com.snail.zk;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Conf {
	public static String CONNECT_LEIQIANG = "172.19.40.130:2181";
	public static String CONNECT_1_GYC_TEST = "10.101.6.11:2182";
	public static String CONNECT_2_GYC_RES = "172.19.40.95:2182";
	public static String CONNECT_3_DG_TEST = "10.204.10.71:2182";
	public static String SELF_IP_PORT = null;
	static int timeout = 3000;
	static{
		try {
			InetAddress a = InetAddress.getLocalHost();
			SELF_IP_PORT = "SELF_IP_PORT:" + a.getHostAddress() + "-CMS:" + System.currentTimeMillis();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    static private final char COLON = ':';  
    
    /** 
     * 获取当前机器ip地址 
     * 据说多网卡的时候会有问题. 
     */  
    public static String getNetworkAddress() {  
        Enumeration<NetworkInterface> netInterfaces;  
        try {  
            netInterfaces = NetworkInterface.getNetworkInterfaces();  
            InetAddress ip;  
            while (netInterfaces.hasMoreElements()) {  
                NetworkInterface ni = netInterfaces  
                        .nextElement();  
                Enumeration<InetAddress> addresses=ni.getInetAddresses();  
                while(addresses.hasMoreElements()){  
                    ip = addresses.nextElement();  
                    if (!ip.isLoopbackAddress()  
                            && ip.getHostAddress().indexOf(COLON) == -1) {  
                        return ip.getHostAddress();  
                    }  
                }  
            }  
            return "";  
        } catch (Exception e) {  
            return "";  
        }  
    } 
}
