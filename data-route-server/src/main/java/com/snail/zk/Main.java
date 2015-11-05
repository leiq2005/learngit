package com.snail.zk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import com.snail.zk.node.ZKNode;
import com.snail.zk.node.ZKNodeDo;

public class Main {

	private static String ip_port = Conf.CONNECT_1_GYC_TEST;//默认
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//授权码
			_.out("授权信息："+DigestAuthenticationProvider.generateDigest("admin:leiqiang"));
			
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));  
			while(true){
				_.out("请选择命名服务器，回车默认1");
				_.out("1-"+Conf.CONNECT_1_GYC_TEST);
				_.out("2-"+Conf.CONNECT_2_GYC_RES);
				_.out("3-"+Conf.CONNECT_3_DG_TEST);
				_.out("4-All");
				String str = strin.readLine();
				if(!(str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4"))){
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
                	case 4:
                		ip_port = "10.101.6.11:2182,172.19.40.95:2182,10.204.10.71:2182";
                	}
                	break;
				}
			}
			
			CountDownLatch connectedSignal = new CountDownLatch(1);
			ZooKeeper zk = new ZooKeeper(ip_port, Conf.timeout, new MyWatcher(connectedSignal));
			if(connectedSignal.await(10,TimeUnit.SECONDS)){
				_.out("connected server:"+ip_port + " SessionId:"+Long.toHexString(zk.getSessionId()));
			}else{
				_.out("connect error:"+ip_port);
				System.exit(0);
			}
			
			
			//查询服务器状态
			ZKNodeDo.queryStats(zk);
			//授权
			//zk.addAuthInfo("digest", "admin:leiqiang".getBytes());
			
			//设置节点ACL
			ZKNodeDo.setAcl("/lei", zk);
			zk.exists("/lei/test5", true);
			
			//设置认证信息
			//zk.addAuthInfo("digest", "admin:admin123".getBytes());
			zk.addAuthInfo("ip", "172.17.32.49".getBytes());
			
			//初始化目录树
			ZKNodeDo.initDic(zk);
			
			//创建自己节点
            
            while(true){
            	_.out("请输入命令：1-显示目录 2-退出 3-查看节点数据（,/xxx）4-设置修改节点数据 5-创建节点 6-删除节点 7-查询孩子节点");
            	String str = strin.readLine();
            	String path = null;
            	String ss[] = str.split(",");
            	String d = "";
            	if(ss.length == 2)
            	{
            		str = ss[0];
            		path = ss[1];
            	}
            	else if(ss.length == 3)
            	{
            		str = ss[0];
            		path = ss[1];
            		d = ss[2];
            	}
            	if(str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7")){
            		int cmd = Integer.parseInt(str);
                	switch(cmd)
                	{
                	case 1: 
                		ZKNode node = _.outDicTree(zk);
                		break;
                	case 2:
                		System.exit(0);
                		break;
                	case 3: 
                		Stat stat = new Stat();
                		byte[] data = zk.getData(path, true, stat);
                		
                		_.out(path+"   DATA=["+new String(data)+"]");
                		_.out(path+"   Stat=["+stat+"]");
                		break;
                	case 4: 
                		zk.setData(path, d.getBytes(), -1);
                		_.out("数据设置成功");
                		break;
                	case 5: 
                		String p = zk.create(path, d.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                		_.out("创建节点成功"+p);
                		break;
                	case 6: 
                		zk.delete(path, -1);
                		_.out("删除节点成功");
                		break;
                	case 7: 
                		_.outList(zk.getChildren(path, new MyWatcher()));
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
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
