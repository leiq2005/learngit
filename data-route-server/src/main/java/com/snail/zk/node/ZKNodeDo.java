package com.snail.zk.node;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import com.snail.zk.Conf;
import com.snail.zk._;

public class ZKNodeDo {
	public static void initDic(ZooKeeper zk){
		if(zk == null){
			_.out("init error,ZooKeeper is null.");
		}
		try {
			Stat stat = zk.exists("/ClientGroup", true);
			if(stat == null){
				//持久化节点
				String path = zk.create("/ClientGroup", "all client.".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				//监控
				
				_.out("[init] "+path);
			}
			//临时节点
			String path = zk.create("/ClientGroup/"+Conf.SELF_IP_PORT, "client ip:port".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			_.out("[init] "+path);
			
			
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void create(ZooKeeper zk){
		try {
			
			
			zk.create("/RouteClientGroup", "Dlei".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route1", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route1/Game1", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route1/Game2", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route1/Game3", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route1/Game4", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route2", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route3", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/RouteClientGroup/Route4", "Dqiang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setAcl(String path,ZooKeeper zk){
		List<ACL> acls = new ArrayList<ACL>(2);
		  
		try {
			Id admin = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:leiqiang"));
			ACL acl1 = new ACL(ZooDefs.Perms.ALL, admin);
			Id guest = new Id("digest", DigestAuthenticationProvider.generateDigest("guest:guest123"));
			ACL acl2 = new ACL(ZooDefs.Perms.READ, guest);
			Id ipuser = new Id("ip", "172.17.32.49");
			ACL acl3 = new ACL(ZooDefs.Perms.ALL, ipuser);
			
			acls.add(acl1);
			acls.add(acl2);
			acls.add(acl3);
			
			zk.setACL(path, acls, -1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	/**
	 * 查询服务器状态
	 * @param zk
	 */
	public static void queryStats(ZooKeeper zk){
		States s = zk.getState();
		_.out("zookeeper server state:"+s.name());
	}
	public static void queryACL(ZooKeeper zk){
		//zk.getACL("/", zk.getState())
	}
}
