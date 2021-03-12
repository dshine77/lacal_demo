package com.ds.demo.moc.sort;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Administrator
 * @version V1.0
 * @Title: LRUCache.java
 * @Package com.ds.demo.moc.sort
 * @Description
 * @date 2021 03-12 19:40.
 */
public class LRUCache<K, V> implements Iterable<K> {

	//实现Iterable让缓存可以迭代输出

	/**
	 * 定义缓存最大size
	 */
	Integer MAX_SIZE ;

	/**
	 * 使用LinkedHashMap作为缓存的容器，
	 * 1、可以让缓存结构具有对列的特性，先进先出；
	 * 2、具有map 映射的特点， key作为缓存的键值， value为实际的数据
	 */
	LinkedHashMap<K,V> cache = new LinkedHashMap <> ();

	public LRUCache(Integer MAX_SIZE) {
		this.MAX_SIZE = MAX_SIZE;
	}

	public LRUCache() {
		this.MAX_SIZE = 10;
	}

	public void cache(K key, V value) {
		//如果已经存在key值, 从容器中删除，然后插入尾部
		if (cache.containsKey (key)) {
			cache.remove (key);
		} else if (cache.size () >= MAX_SIZE) {
			//如果容器满了，则从最先插入端删除一个元素
			Iterator <K> iterator = cache.keySet ().iterator ();
			K first = iterator.next ();
			cache.remove (first);
		}

		cache.put (key, value);
	}

	@Override
	public Iterator<K> iterator() {
		Iterator <Map.Entry <K, V>> iterator = cache.entrySet ().iterator ();
		return new Iterator <K> () {
			@Override
			public boolean hasNext() {
				return iterator.hasNext ();
			}

			@Override
			public K next() {
				return iterator.next ().getKey ();
			}
		};
	}


	public static void main(String[] args) {
		LRUCache<String, Integer> lruCache = new LRUCache <> (3);

		lruCache.cache("a", 1);
		lruCache.cache("b", 2);
		lruCache.cache("c", 3);
		lruCache.cache("d", 4);
		lruCache.cache("b", 5);

		System.out.println(StreamSupport.stream (lruCache.spliterator (), false)
		.map (x-> x.toString ()).collect (Collectors.joining ("<-")));
	}
}
