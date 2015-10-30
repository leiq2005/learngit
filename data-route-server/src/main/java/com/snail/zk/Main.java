package com.snail.zk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import com.snail.zk.node.ZKNode;
import com.snail.zk.node.ZKNodeDo;

public class Main {

	private static String ip_port = Conf.CONNECT_1_GYC_TEST;//默认
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//授权码
			_.out(DigestAuthenticationProvider.generateDigest("admin:leiqiang"));
			
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));  
			while(true){
				_.out("请选择命名服务器，回车默认1");
				_.out("1-"+Conf.CONNECT_1_GYC_TEST);
				_.out("2-"+Conf.CONNECT_2_GYC_RES);
				_.out("3-"+Conf.CONNECT_3_DG_TEST);
				String str = strin.readLine();
				if(!(str.equals("1") || str.equals("2") || str.equals("3"))){
					if("".equals(str)){
						break;
					}
					_.out("命令错误，请重新输入");
				}else{
					int sn = Integer.parseInt(str);
                	switch(sn)
                	{
                	case 1: 
                		ip_port = Conf.CONNECT_1_GYC_TEST;
                		break;
                	case 2: 
                		ip_port = Conf.CONNECT_2_GYC_RES;
                		break;
                	case 3: 
                		ip_port = Conf.CONNECT_3_DG_TEST;
                		break;	
                	}
                	break;
				}
			}
			
			CountDownLatch connectedSignal = new CountDownLatch(1);
			ZooKeeper zk = new ZooKeeper(ip_port, Conf.timeout, new MyWatcher(connectedSignal));
			connectedSignal.await();
			_.out("connected server:"+ip_port + " SessionId:"+Long.toHexString(zk.getSessionId()));
			
			//查询服务器状态
			ZKNodeDo.queryStats(zk);
			//授权
			zk.addAuthInfo("digest", "admin:leiqiang".getBytes());
			
			//设置节点ACL
			ZKNodeDo.setAcl("/lei", zk);
			
			
			//设置认证信息
			//zk.addAuthInfo("digest", "admin:admin123".getBytes());
			zk.addAuthInfo("ip", "172.17.32.49".getBytes());
			
			//初始化目录树
			ZKNodeDo.initDic(zk);
			
			//创建自己节点
            
            while(true){
            	_.out("请输入命令：1-显示目录 2-退出");
            	String str = strin.readLine();
            	if(str.equals("1") || str.equals("2")){
            		int cmd = Integer.parseInt(str);
                	switch(cmd)
                	{
                	case 1: 
                		ZKNode node = _.outDicTree(zk);
                		break;
                	case 2:
                		System.exit(0);
                		break;
                	}
            	}
            	else{
            		_.out("命令错误，请重新输入");
            	}
            }
			
			//Thread.sleep(3000000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
