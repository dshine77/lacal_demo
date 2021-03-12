package com.ds.demo.moc.sort;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Solution.java
 * @Package com.ds.demo.moc.sort
 * @Description
 * @date 2020 12-03 11:06.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Solution {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}


	/**
	 * 已知二叉树的前序遍历和中序遍历， 重建二叉树
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		List<Integer> preList = new ArrayList <> (preorder.length);
		List<Integer> inList = new ArrayList <> (inorder.length);

		for (int i = 0; i < preorder.length; i++) {
			preList.add(preorder[i]);
			inList.add(inorder[i]);
		}

		return rebuildTree(preList, inList);
	}

	private TreeNode rebuildTree(List<Integer> preList, List<Integer> inList) {
		if (inList == null || inList.size () == 0) {return null;}
		int rootVal = preList.remove (0);
		TreeNode root = new TreeNode (rootVal);
		int rootIndex = inList.indexOf (rootVal);
		root.left = rebuildTree (preList, inList.subList (0, rootIndex));
		root.right = rebuildTree (preList, inList.subList (rootIndex + 1, inList.size ()));
		return root;
	}

	/**
	 * 青蛙阶梯问题
	 * @param n
	 * @return
	 */
	public int numWays(int n) {
		int[] nums = new int[n];
		nums[0] = 0;
		nums[1] = 1;
		nums[2] = 2;
		for (int i = 3; i < nums.length; i++) {
			nums[i] = nums[1] + nums[2];
		}
		return nums[n];
	}

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * 输入：[3,4,5,1,2]
	 * 输出：1
	 *
	 * 输入：[2,2,2,0,1]
	 * 输出：0
	 *
	 * @param numbers
	 * @return
	 */
	public int minArray(int[] numbers) {
		return minArray (numbers, 0, numbers.length -1);
	}

	public int minArray(int[] numbers, int s, int e) {
		//二分法查找
		if (s == e) {return numbers[s];}
		if (e - s == 1) {return Math.min (numbers[s], numbers[e]);}
		int r = e;
		int m = s + (e-s)/2;

		int min = numbers[s];

		if (numbers[m] > numbers[r]) {
			return Math.min (min, minArray (numbers, m, r));
		} else if (numbers[m] < numbers[r]){
			return Math.min (min, minArray (numbers, s, m));
		} else {
			return Math.min (min, minArray (numbers, s, e-1));
		}
	}

	/**
	 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
	 *
	 * [["a","b","c","e"],
	 * ["s","f","c","s"],
	 * ["a","d","e","e"]]
	 *
	 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
	 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
	 *
	 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
	 * 输出：true
	 *
	 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
	 * 输出：false
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		if (word.length () == 0 || board.length == 0 || board[0].length == 0) {return false;}
		char first = word.charAt (0);
		int i = 0;
		int j = 0;
		for (; i < board.length; i++) {
			for (; j < board[i].length; j++) {
				if (board[i][j] == word.charAt (0)) {
					break;
				} else {
					return false;
				}
			}
		}
		int t = 0;
		while (t < word.length ()) {
			t++;

		}
        //TODO
		return false;
	}

	/**
	 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
	 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
	 *
	 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	 *
	 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
	 *
	 * @param n
	 * @return
	 */
	public int cuttingRope(int n) {

		//TODO
		return 0;
	}

	public static void main(String[] args) {
	    //int[] nums = {3,4,5,1,2};
		Solution s = new Solution ();
		s.cuttingRope (10);
	}
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<> ();
		if (root == null) {
			return new ArrayList();
		}
		recursion(root, list);
		return list;
	}

	public void recursion(TreeNode root, List<Integer> list){
		if (root == null) {
			return ;
		}
		recursion(root.left, list);
		recursion(root.right, list);
		list.add(root.val);
	}

	public void pos_iteration1(TreeNode root, List<Integer> list){
		Stack<TreeNode> path = new Stack ();
		while (root != null || !path.isEmpty ()) {
			if (root != null) {
				path.push (root);
				list.add (0,root.val);
				root = root.right;
			} else {
				TreeNode cur = path.pop ();
				root = cur.left;
			}
		}
	}

	//递归  占用了额外的空间
	public void pos_iteration2(TreeNode root, List<Integer> list){
		Stack<TreeNode> path = new Stack <>();
		Stack<Integer> stack = new Stack <> ();

		//先序遍历 ->  1.弹出就打印， 然后压入右节点，然后压入左节点   头左右（先序） -> 头右左  -> 左右头（后序）
		//后序遍历 ->  1.弹出就打印， 然后压入左节点，然后压入右节点 2.逆转
		path.push (root);
		while (!path.isEmpty()) {
			root = path.pop ();
			stack.push (root.val);
			if (root.left != null) {
				path.push (root.left);
			}
			if (root.right != null) {
				path.push (root.right);
			}
		}

		while (!stack.isEmpty ()) {
			list.add (stack.pop ());
		}
	}

	public void pos_iteration3(TreeNode root, List<Integer> list){

	}


	public void mid_iteration1(TreeNode root, List<Integer> list){
		Stack<TreeNode> path = new Stack <>();

		while (root != null || !path.isEmpty ()) {
			if (root != null) {
				path.add (root);
				root = root.left;
			} else {
				root = path.pop ();
				list.add (root.val);
				root = root.right;
			}
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null){return null;}
		List<List<Integer>> lists = new LinkedList <> ();
		List<Integer> list;
		Queue<TreeNode> queue = new LinkedList <> ();
		queue.offer (root);
		while (!queue.isEmpty ()) {
			list = new ArrayList <> ();
			int size = queue.size ();
			int i = 0;
			while (i<size) {
				TreeNode cur = queue.peek ();
				list.add (cur.val);
				if (cur.left != null) {
					queue.offer (cur.left);
				}
				if (cur.right != null) {
					queue.offer (cur.right);
				}
				i++;
			}

			lists.add (0, list);

		}
		return lists;
	}

	public static int lengthOfLongestSubstring(String s) {
		Set<Character> collect = new HashSet <> ();
		int head = 0;
		int tail = 0;
		int les = 0;
		while (head < s.length ()) {
			char ch = s.charAt (head ++);
			if(collect.contains (ch)) {
				les = Math.max (les, head - tail -1);
			}
			while (collect.contains (ch)) {
				collect.remove (s.charAt (tail++));
			}
			collect.add (ch);
		}
		return  Math.max (les, head - tail);
	}

	class MyValue{
		Integer count ;

		public MyValue(Integer count) {
			this.count = count;
		}
	}

	public int reversePairs(int[] nums) {
		MyValue myValue = new MyValue (0);
		mergeSort(nums, 0, nums.length -1, myValue);
		return myValue.count;
	}

	public void mergeSort(int [] arr, int L, int R, MyValue myValue) {
		if (arr == null || L == R || arr.length == 0) {return ;}
		//int mid = L + ((R - L) >> 1);
		mergeSort(arr, L, L + (R-L)/2, myValue);
		mergeSort (arr, L + (R-L)/2 + 1, R, myValue);
		myValue.count =myValue.count + merge_new(arr, L , L + (R-L)/2, R);
	}

	private int merge(int[] arr, int l, int m, int r) {
		int count = 0;
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
					arr[first ++] = right[j++];
					count+= left.length - i;
				}
			}
			else if (i == left.length) {
				arr[first ++] = right[j ++];
			}
			else if (j == right.length) {
				arr[first ++] = left[i ++];
				//count+= left.length - i;
			}
		}
		return count;
	}

	private int merge_new(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		int count = 0;
		while (p1 <= m && p2 <= r) {
			count += arr[p1] <= arr[p2] ? 0 : m-p1+1;
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 要么p1越界了，要么p2越界了
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return count;
	}

	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		int max = 0;
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (dp[i-1] < 0) {
				dp[i] = nums[i];
			} else {
				dp[i] = dp[i-1] + nums[i];
			}
			max = Math.max (max, dp[i]);
		}
		return max;
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int sum = m + n - 1;
		int p = m - 1;
		int q = n - 1;
		while (p >= 0 && q >= 0) {
			nums1[sum--] = nums2[q] > nums1[p] ? nums2[q--] : nums1[q--];
		}
		while (p < 0) {
			nums1[sum--] = nums1[q--];
		}
		while (q < 0) {
			nums1[sum--] = nums1[p--];
		}
	}

	public ListNode rotateRight(ListNode head, int k) {
		ListNode node = head;
		int length = 1;
		while (node.next != null) {
			node = node.next;
			length++;
		}
		node.next = head;
		int i = 0;
		while (i<k){
			head = head.next;
			i++;
		}

		ListNode node1 = head;
		while (i<length){
			node1 = node1.next;
		}
		node1.next = null;
		return head;
	}


	  public class ListNode {
	     int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }

	/*public static void main(String[] args) {
	    //firstUniqChar ("cc");
	*//*	System.out.println(lengthOfLongestSubstring("dvdf"));
		System.out.println(lengthOfLongestSubstring("abcabcbb"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));*//*
		int[] a = {-2,1,-3,4,-1,2,1,-5,4};
		Solution s = new Solution ();
		//System.out.println(s.reversePairs(a));

		System.out.println(s.maxSubArray (a));
	}*/

	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for (int i = 0; i < m ; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < n ; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j <n ; j++) {
				dp[i][j] = dp[i-1][j]+dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] dp = new int[m][n];
		for (int i = 0; i < m ; i++) {
			if (obstacleGrid[i][0] == 1) {
				obstacleGrid[i][0] = 0;
			} else {
				obstacleGrid[i][0] = 1;
			}
		}
		for (int i = 0; i < n ; i++) {
			if (obstacleGrid[0][i] == 1) {
				obstacleGrid[0][i] = 0;
			} else {
				obstacleGrid[0][i] = 1;
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j <n ; j++) {
				if (obstacleGrid[0][i] == 1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = dp[i-1][j]+dp[i][j-1];
				}

			}
		}
		return dp[m-1][n-1];

	}

	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m ; i++) {
			grid[i][0] = grid[i-1][0] + grid[i][0];
		}

		for (int i = 0; i < n ; i++) {
			grid[0][i] = grid[0][i-1] + grid[0][i];
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j <n ; j++) {
				grid[i][j] = Math.min (grid[i-1][j], grid[i][j-1]) + grid[i][j];
			}
		}

		return grid[m-1][n-1];

	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList <> ();
		inOrder(root, list);
		return list;
	}

	private void inOrder(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}
		list.add (root.val);
		inOrder (root.left, list);
		inOrder (root.right, list);
	}

	/**
	 * 反转链表  递归
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode node = reverseList (head.next);
		head.next.next = head;
		head.next = null;
		return node;
	}
	/**
	 * 反转链表  迭代
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		ListNode temp;

		while (cur != null) {
			temp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = temp;
		}
		return pre;
	}

/*	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null) {return  head;}
		ListNode node = head;

		ListNode h1 = null;
		ListNode h2 = null;
		ListNode h3 = null;
		int i = 1;
		while (node != null && i < m) {
			node = node.next;
			i ++;
		}
		h1 = node;
		h2 = node.next;

		while (node != null && i <= n) {
			node = node.next;
			i ++;
		}
		h3 = node.next;
		node.next = null;

		h2 = reverseList (h2);
		h1.next = h2;
		while (node != null && i < n)


	}*/

}
