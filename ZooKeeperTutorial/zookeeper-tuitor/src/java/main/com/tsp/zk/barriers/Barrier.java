package com.tsp.zk.barriers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import com.tsp.zk.common.SyncPrimitive;

public class Barrier extends SyncPrimitive {

	private int size;
	
	private String name;
	
	Barrier(String address,String root, int size){
		super(address);
		this.root = root;
		this.size = size;
		
		if(this.zk != null){
			try {
				Stat s = zk.exists(root, false);
				if(s == null){
					zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		try {
			name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
