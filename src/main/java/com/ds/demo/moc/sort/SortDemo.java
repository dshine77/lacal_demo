package com.ds.demo.moc.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Administrator
 * @version V1.0
 * @Title: SortDemo.java
 * @Package com.ds.demo.moc.sort
 * @Description
 * @date 2020 11-16 16:56.
 */
@Slf4j
public class SortDemo {


/*	public static void main(String[] args) {
	    int[] befor = new int[]{4,3,2,5,67,8,9,23,12,76,8,5};
		//SortDemo.printArr(SortDemo.bubbleSort(befor));
		SortDemo.quickSort (befor);
		SortDemo.printArr(befor);
	}*/

	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j) {return;}
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 不定义额外变量，交换2个变量的值
	 * 或_运_算：有1为1 ，都为0则为0
	 * 异或运算：相同为0，不同为1（可记成无进位相加）
	 * 同或运算：相同为1，不同为0（补充）
	 *
	 * i和j是一个位置的话，会出错
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap_new(int[] arr, int i, int j) {
		if (i == j) {return;}
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	/**
	 * 1、是否具有稳定性在于对于相等值的处理，在两个位置的值相等时，不改变他们的相对位置。
	 *
	 */

	/**
	 * 1、冒泡排序
	 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
	 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
	 * 针对所有的元素重复以上的步骤，除了最后一个；
	 * 重复步骤1~3，直到排序完成。
	 *
	 * 稳定性 √ ， 时间复杂度 O(n^2) 空间复杂度 O(1)
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort(int [] arr) {

		for (int i = 0; i < arr.length -1; i++) {
			for (int j = 0; j < arr.length - i -1; j++) {
				if (arr[j] > arr[j + 1]){
					swap(arr, j, j + 1);
				}
			}
		}
		return arr;
	}

	/**
	 * 2、选择排序
	 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再
	 * 从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
	 *
	 * 稳定性 √  时间复杂度 O(n^2)  空间复杂的 O(1)
	 *
	 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
	 * 唯一的好处可能就是不占用额外的内存空间了吧。
	 *
	 * @param arr
	 * @return
	 */
	public static int[] selectionSort(int [] arr) {
		int minIndex;
		for (int i = 0; i < arr.length -1 ; i++) {
			minIndex = i;
			for (int j = i+1; j < arr.length ; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap (arr, i, minIndex);
		}
		return arr;
	}

	/**
	 * 3、插入排序
	 *
	 * 原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
	 * 从第一个元素开始，该元素可以认为已经被排序；
	 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
	 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
	 * 将新元素插入到该位置后；
	 * 重复步骤2~5。
	 *
	 * 稳定性 √  时间复杂度 O(n^2)  空间复杂的 O(1)
	 * @param arr
	 * @return
	 */
	public static int[] insertSort(int [] arr) {
		if (arr == null || arr.length < 2) {
			return arr;
		}
		for (int i = 0; i < arr.length-1 ; i++) {
			//默认第一个元素是排好序的，从第二个开始往前看
			//将当前位置的数值保存起来
			int current = arr[i+1];
			int pre = i;
			while (pre >= 0 && arr[pre] > current) {
				arr[pre + 1] = arr[pre];
				pre --;
			}
			arr[pre +1] = current;
		}
		return arr;
	}

	/**
	 * 4、归并排序
	 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；
	 * 即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
	 *
	 * 不断二分递归，直到只有一个元素，那么这个元素肯定是有序的，合并这两个分别有序的集合
	 *
	 * 递归，是利用系统栈保存递归过程中的中间结果， 入栈， 计算， 出栈的过程
	 *
	 * 把长度为n的输入序列分成两个长度为n/2的子序列；
	 * 对这两个子序列分别采用归并排序；
	 * 将两个排序好的子序列合并成一个最终的排序序列。
	 *
	 * 稳定性 √  时间复杂度：O(nlogn）  空间复杂度：O(N)
	 *
	 * 实质：把前面比较行为的结果保存下来，与更大的范围做比较 ，减少比较的次数（求小和数问题， 降序对问题）
	 *
	 * @param arr
	 * @return
	 */
	public static void mergeSort(int [] arr, int L, int R) {
		if (arr == null || L == R) {return ;}
		//int mid = L + ((R - L) >> 1);
		mergeSort(arr, L, L + (R-L)/2);
		mergeSort (arr, L + (R-L)/2 + 1, R);
		merge(arr, L , L + (R-L)/2, R);
 	}

	private static void merge(int[] arr, int l, int m, int r) {
		int[] left = new int[m -l + 1];
		int[] right = new int[r - m];

		int first = l;
		for (int i = 0; i < left.length; i++) {
			left[i] = arr[l];
			l ++;
		}

		for (int i = 0; i < right.length; i++) {
			right[i] = arr[m + 1];
			m ++;
		}

		int i = 0;
		int j = 0;

		while (i < left.length || j < right.length) {
			if (i < left.length && j < right.length) {
				if (left[i] <= right[j]) {
					arr[first ++] = left[i ++];
				} else {
					arr[first ++] = right[j ++];
				}
			}
			else if (i == left.length) {
				arr[first ++] = right[j ++];
			}
			else if (j == right.length) {
				arr[first ++] = left[i ++];
			}
		}
	}

	public static void merge_new(int[] arr, int L, int M, int R) {
		int[] help = new int[R - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = M + 1;
		while (p1 <= M && p2 <= R) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 要么p1越界了，要么p2越界了
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
	}

	public static void merge_new2(int[] arr, int L, int M, int R) {
		//多拷贝一个数组位置放置哨兵元素，作为边界值判断
		int [] A = Arrays.copyOfRange (arr, L, M+1);
		int [] B = Arrays.copyOfRange (arr, M, R+1); //不会越界， copyOfRange方法具有保护作用， 如果越界了会填充默认值

		A[A.length - 1] = B[B.length-1] = Integer.MIN_VALUE;

		int i = 0, j=0;
		for (int k = 0; k < arr.length; k++) {
			if (A[i] < B[j]) {
			   arr[k] = A[i++];
			} else {
				arr[k] = B[j++];
			}
		}
	}


	/**
	 * 5、快速排序（随机排序）
	 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
	 *
	 * 从数列中挑出一个元素，称为 “基准”（pivot）；
	 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
	 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
	 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
	 *
	 *  稳定性：×  时间复杂度：O(NlogN)  空间复杂度：O(NlogN)
	 *
	 * @param arr

	 */
	public static void quickSort(int [] arr) {

		if (arr == null || arr.length<= 1) {
			return;
		}

		//process1(arr, 0, arr.length-1);
		//process2(arr, 0, arr.length-1);
		process3(arr, 0, arr.length-1);
	}

	/**
	 *
	 * @param arr
	 * @param L
	 * @param R
	 */
	private static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		//分区 L..R partition arr[R]  [   <=arr[R]   arr[R]    >arr[R]  ]
		int M = partition(arr, L, R);
		process1 (arr, L, M-1);
		process1 (arr, M+1, R);
	}

	private static int partition(int[] arr, int l, int r) {
		if (l > r) {
			return -1;
		}
		if (r == l) {
			return l;
		}
		int keyValue = arr[r];
		int lessEqualIndex = l - 1;
		for (int i = l; i <= r; i++) {
			if (arr[i] <= keyValue) {
				lessEqualIndex ++;
				swap (arr, i, lessEqualIndex);
			}
		}
		return lessEqualIndex;
	}

	/**
	 *  优化1： 再一次的迭代不再包括等于的区域，减少比较的次数
	 * @param arr
	 * @param L
	 * @param R
	 */
	private static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		//分区 L..R partition arr[R]  [   <=arr[R]   arr[R]    >arr[R]  ]
		int[] ints = partition2(arr, L, R);
		process2 (arr, L, ints[0] -1);
		process2 (arr, ints[1] + 1, R);
	}

	/**
	 * arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
	 *
	 *  <arr[R])  ==arr[R]  (> arr[R]
	 *  return  返回等于区域的左右边界
	 *
	 */
	private static int[] partition2(int[] arr, int l, int r) {
		if (l > r) {
			return new int[]{-1, -1};
		}
		if (r == l) {
			return new int[]{l, r};
		}
		int less = l - 1;
		int more = r + 1;
		int index = l;
		int keyValue = arr[r];
		while (index < more) {
			if (arr[index] < keyValue) {
				less ++;
				swap (arr, index, less);
				index ++;
			} else if (arr[index] == keyValue) {
				index ++;
			} else {
				swap (arr, index, --more);
			}
		}
		//L...less | less+1....more-1 | more....R
		return new int[]{less + 1, more -1};
	}

	/**
	 * 优化2（最终版）： 随机选一个数做划分
	 *                （时间复杂度最好的情况是分区之后做划分的元素刚好处于数组的中间位置，
	 *                随机选一个数做划分，将最好情况和最差情况变成概率事件，优化时间复杂度）
	 *
	 *                把每一种情况 都列出来，会有每种情况下的复杂度，但概率都是1/N， 那么所有情况都考虑，
	 *                时间复杂度就是这种概率模型下的长期期望
	 * @param arr
	 * @param L
	 * @param R
	 */
	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		//随机选一个数与最后一个数做交换
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = partition2 (arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}


	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {

			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			int[] arr4 = copyArray(arr1);
			process1(arr1, 0, arr1.length-1);
			process2(arr2, 0, arr2.length-1);
			process3(arr3, 0, arr3.length-1);
			if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
				printArray (arr4);
				printArray (arr1);
				printArray (arr2);
				printArray (arr3);

				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Oops!");

	}
}
