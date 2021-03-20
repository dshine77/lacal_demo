package com.ds.demo.moc.common;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @version V1.0
 * @Title: IpCheckSolution.java
 * @Package com.ds.demo.moc.common
 * @Description
 * @date 2021 03-20 17:07.
 */
public class IpCheckSolution {

	public static Map<String, ArrayDeque<Long>> fiveSecMap = new ConcurrentHashMap<> ();
	public static Map<String, ArrayDeque<Long>> oneMinuteMap = new ConcurrentHashMap <> ();
	public static Map<String, ArrayDeque<Long>> oneHourMap = new ConcurrentHashMap <> ();

	public Boolean check (String ip){
		if(!checkTime (ip, fiveSecMap, 1000 * 60L, 5*1000L, 1000)){
			return false;
		}
		if(!checkTime (ip, oneMinuteMap, 1000 * 60L * 10, 60 * 1000L, 10000)){
			return false;
		}
		if(!checkTime (ip, oneHourMap, 1000 * 60L * 24 , 60 * 60 *1000L, 50000)){
			return false;

		}
		return true;

	}

	private Boolean checkTime(String ip, Map <String, ArrayDeque <Long>> map, Long skipTime, Long checkTime, Integer length) {
		synchronized (map.get(ip)) {
			long time = System.currentTimeMillis ();
			if (map.get (ip) == null) {
				ArrayDeque queue = new ArrayDeque ();
				queue.offer (time);
				map.put (ip, queue);
			} else {
				ArrayDeque <Long> queue = map.get (ip);
				if (queue.size () < length) {
					queue.offer (time);
				}
				if (queue.size () == length) {
					Long first = queue.getFirst ();
					if ((time - first) <checkTime) {
						return (time - queue.getLast ()) > skipTime;
					}else {
						queue.pollFirst ();
						queue.offer (time);
					}
				}
			}
			return true;
		}

	}

}
