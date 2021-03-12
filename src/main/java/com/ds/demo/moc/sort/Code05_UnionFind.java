package com.ds.demo.moc.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Code05_UnionFind.java
 * @Package com.ds.demo.moc.sort
 * @Description 并查集
 * @date 2020 11-25 19:34.
 */
public class Code05_UnionFind {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionSet<V> {
		//节点
		public HashMap<V, Node<V>> nodes;
		//记录每一个节点的父亲节点（最顶层）
		public HashMap<Node<V>, Node<V>> parents;
		//记录有多少个子节点以这个节点为顶级父节点, 用于合并两个集合的时候比较大小
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionSet(List<V> values) {
			nodes = new HashMap<>();
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V cur : values) {
				Node<V> node = new Node<>(cur);
				nodes.put(cur, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack <> ();
			while (cur !=  parents.get (cur)) {
				path.push (cur);
				cur = parents.get (cur);
			}
			//将中间节点的父级直接指定到最顶层的父级， 减少每次查找父级的查找次数
			while (!path.isEmpty ()) {
				parents.put (path.pop (), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			Node<V> nodeA = nodes.get (a);
			Node<V> nodeB = nodes.get (b);
			if (nodeA == null || nodeB == null) {
				return false;
			}
			if (nodeA == nodeB) {
				return true;
			}
			return findFather (nodeA) == findFather (nodeB);
		}

		public void union(V a, V b) {
			Node<V> nodeA = nodes.get (a);
			Node<V> nodeB = nodes.get (b);
			Node<V> parentNodeA = findFather (nodeA);
			Node<V> parentNodeB = findFather (nodeB);
			if (nodeA == nodeB || parentNodeA == parentNodeB) {
				return;
			}

			//小的集合合并到大的集合上面
			Node<V> bigNode = sizeMap.get(parentNodeA) >= sizeMap.get (parentNodeB) ? parentNodeA : parentNodeB;
			Node<V> smallNode = bigNode == parentNodeA ? parentNodeB : parentNodeA;
			parents.put (smallNode, bigNode);
			sizeMap.put (bigNode, sizeMap.get (smallNode) + sizeMap.get (bigNode));
			sizeMap.remove (smallNode);
		}
	}

}
