package com.snail.zk.node;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

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
}
