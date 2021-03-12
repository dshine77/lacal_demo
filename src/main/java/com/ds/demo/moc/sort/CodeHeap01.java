package com.ds.demo.moc.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Code_Heap01.java
 * @Package com.ds.demo.moc.sort
 * @Description 堆排序
 * @date 2020 11-19 15:37.
 */
public class CodeHeap01 {


	/***
	 *  6、堆排序
	 *   一个数组可以根据一定的规则想象成二叉树的结构
	 *   根据下标[0, 2, 3, 4, 5, 6.....] 我们可以这样 父节点：(i-1)/2, 左孩子：2*i+1, 右孩子：2*i+2
	 *   很多使用舍弃0位置的下标不用，数组下标从1开始， 父节点：i/2 <==> i>>1, 左孩子：2*i <==> i<<1, 右孩子：2*i+1 <==> i<<1 |1 使用位移运算提高计算的效率
	 *
	 *   如果有N个节点， 那么整个二叉树的高度为logN
	 *
	 *   大根堆（每一颗子树，最大的节点是数组的头结点） 、小根堆（每一颗子树，最小的节点是数组的头结点）
	 *
	 *   1.先让整个数组都变成大根堆的结构，建立堆的过程
	 *     1）从上到下的方法，时间复杂度为O（NlogN）
	 *     2）从下到上的额方法，时间复杂度为O（N）
	 *   2.把堆的最大值和堆末尾的值交换，然后减小堆的大小之后，在重新调整为大根堆，一致周而复始，时间复杂度为O（NlogN）
	 *   3.堆的大小减小为0 的时候排序完成
	 *
	 *
	 */
	
	public static class MyMaxHeap{
		private int[] heap;
		private final int limit;
		private int heapSize;

		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void push(int value) {
			if (heapSize == limit) {


				throw new RuntimeException ("the heap is full!!!");
			}
			heap[heapSize] = value;
			heapInsert(heap, heapSize);
			heapSize++;
		}

		private void heapInsert(int[] heap, int index) {
			//每一次将节点和父节点比较， 如果比父节点大， 就替换父节点的位置， 直到堆顶
			//当index == 0 的时候， (index-1)/2 = 0， 所以下面的判断也会退出循环
			while (heap[index] > heap[(index-1)/2]) {
				swap (heap, index, (index-1)/2);
				index = (index-1)/2;
			}
		}

		public int pop(){
			if (heapSize == 0) {
				new RuntimeException ("the heap is empty!!!");
			}
			int popValue = heap[0];
			//弹出堆顶之后， 将最后的一个元素放到堆顶， 然后重新调整为一个大根堆
			swap (heap, 0, --heapSize);
			if (heapSize > 1) {
				heapify(heap, 0, heapSize);
			}
			return popValue;
		}

		// 从index位置，往下看，不断的下沉，
		// 停：我的孩子都不再比我大；已经没孩子了
		public void heapify(int[] heap, int index, int heapSize){
			int left = index*2 + 1;
			while (left < heapSize) {
				//得到左右孩子中较大的位置
				int larger = (left + 1) < heapSize && heap[left + 1] > heap[left] ? (left + 1) : left;
				if (heap[larger] > heap[index]) {
					swap (heap, index, larger);
					index = larger;
					left = index*2 + 1;
				} else {
					break;
				}
			}
		}

		public void swap(int[] arr, int i, int j) {
			if (i == j) {return;}
			arr[i] = arr[i] ^ arr[j];
			arr[j] = arr[i] ^ arr[j];
			arr[i] = arr[i] ^ arr[j];
		}

	}


	/**
	 * 题目1、已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，
	 * 并且k相对于数组长度来说是比较小的。
	 *
	 * 请选择一个合适的策略对数组进行排序
	 */
	public static void sortedArrDistanceLessK(int[] arr, int k) {
		if (k == 0 || arr == null || arr.length <= 1) {
			return;
		}
		//定义一个长度为K的堆结构,默认小根堆
		PriorityQueue<Integer> smallHeap = new PriorityQueue ();
		int i = 0;
		//!!!!for (; i <= k; i++) {  此处需要考虑如果k> 队列的长度的情况
		for (; i <= Math.min (k , arr.length -1); i++) {
			smallHeap.add (arr[i]);
		}

		int j = 0;
		while (!smallHeap.isEmpty ()) {
			arr[j++] = smallHeap.poll ();
			if (i < arr.length ) {
				smallHeap.add (arr[i++]);
			}
		}
	}

	// for test
	public static void comparator(int[] arr, int k) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		// 先排个序
		Arrays.sort(arr);
		// 然后开始随意交换，但是保证每个数距离不超过K
		// swap[i] == true, 表示i位置已经参与过交换
		// swap[i] == false, 表示i位置没有参与过交换
		boolean[] isSwap = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
			if (!isSwap[i] && !isSwap[j]) {
				isSwap[i] = true;
				isSwap[j] = true;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		System.out.println("test begin");
		int testTime = 500000;
		int maxSize = 10;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int k = (int) (Math.random() * maxSize) + 1;
			int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			sortedArrDistanceLessK(arr1, k);
			comparator(arr2, k);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				System.out.println("K : " + k);
				printArray(arr);
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}


	/////////////对数器/////////////////
/*	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;

		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}

		public int pop() {
			int maxIndex = 0;
			for (int i = 1; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}

	}

	public static void main(String[] args) {
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MyMaxHeap my = new MyMaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");

	}*/
}
