package com.snail.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class MyWatcher implements Watcher {

	CountDownLatch one;
	public MyWatcher(){
	}
	public MyWatcher(CountDownLatch one){
		this.one = one;
	}
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		_.out("MyWatcher:"+arg0);
		KeeperState zkState = arg0.getState();
		EventType eventType = arg0.getType();
		switch(zkState)
		{
			case Disconnected:
				_.out("MWZKS=Disconnected");
				//重连
				break;
			case SyncConnected:
				switch(eventType)
				{
					case None:
						_.out("MWE=None");
						if(one != null){
							one.countDown();
						}
						_.out("MWZKS=SyncConnected");
						break;
					case NodeCreated:
						_.out("MWE=NodeCreated");
						break;
					case NodeDeleted:
						_.out("MWE=NodeDeleted");
						break;
					case NodeDataChanged:
						_.out("MWE=NodeDataChanged");
						break;
					case NodeChildrenChanged:
						_.out("MWE=NodeChildrenChanged");
						break;
					default:
						_.out("未知事件状态码="+eventType);
				}
				break;
			case AuthFailed:
				_.out("MWZKS=AuthFailed");
				break;
			case ConnectedReadOnly:
				_.out("MWZKS=ConnectedReadOnly");
				break;
			case SaslAuthenticated:
				_.out("MWZKS=SaslAuthenticated");
				break;
			case Expired:
				_.out("MWZKS=Expired");
				break;
			default:
				_.out("未知服务器状态码="+zkState);
		}
	}
}
