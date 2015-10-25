package com.snail.zk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import com.snail.zk.node.ZKNode;

/**
 * 输出
 * @author leiqiang
 *
 */
public class _ {
	public static void out(String str){
		System.out.println(preFix() + str);
	}
	private static String preFix(){
		return "->";
	}
	public static void outList(String[] list){
		for(String str : list){
			out(str);
		}
	}
	public static void outList(List<String> list){
		for(String str : list){
			out(str);
		}
	}
	public static void outDicTree(ZKNode node){
		
	}
	/**
	 * 输出目录树
	 * @param zk
	 * @return
	 */
	public static ZKNode outDicTree(ZooKeeper zk){
		ZKNode parent = new ZKNode(null, "/", null);
		
		List<String> list = new ArrayList<String>();
		outDicTree(zk, parent, list);

		String[] a = new String[list.size()];
		Arrays.sort(list.toArray(a), 0, list.size(), new Comparator<String>(){
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		_.out("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		_.out("[输出已排序 Num:"+a.length+"]");
		_.outList(a);
		_.out("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return parent;
	}
	private static void outDicTree(ZooKeeper zk,ZKNode parent,List<String> dicList){
		try {
			
			ZKNode child = null;
			String childpath = null;
			List<String> list = zk.getChildren(parent.getPath(), null);
			if(list != null && list.size() > 0){
				for(String str : list){
					childpath = "/"+str;
					if(!parent.getPath().equals("/")){
						childpath = parent.getPath() + childpath;
					}
					child = new ZKNode(parent, null);
					child.setPath(childpath);
					parent.addChildren(child);
					
					dicList.add(childpath);
					
					outDicTree(zk,child, dicList);
				}
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
