package com.snail.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;

import com.snail.zk.node.ZKNode;
import com.snail.zk.node.ZKNodeDo;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CountDownLatch connectedSignal = new CountDownLatch(1);
			ZooKeeper zk = new ZooKeeper(Conf.connect, Conf.timeout, new MyWatcher(connectedSignal));
			connectedSignal.await();
			_.out("connected server:"+Conf.connect);
			//初始化目录树
			ZKNodeDo.initDic(zk);
			//创建自己节点
			
			for(int i = 0; i < 50; i++){
				ZKNode node = _.outDicTree(zk);
				Thread.sleep(2000);
			}
			
			
			Thread.sleep(3000000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
