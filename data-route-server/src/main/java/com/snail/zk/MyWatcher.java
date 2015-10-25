package com.snail.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class MyWatcher implements Watcher {

	CountDownLatch one;
	public MyWatcher(CountDownLatch one){
		this.one = one;
	}
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		EventType eventType = arg0.getType();
		KeeperState state = arg0.getState();
		if(state == state.SyncConnected){
			one.countDown();
			_.out(state.toString()+   "    "+eventType.toString());
		}
		else{
			_.out(state.toString());
		}
		
	}

}
