package org.wangjj.practice.leetcode;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.javassist.runtime.Inner;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * ClassName: Solution <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/1/26 下午2:33 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() ==0 ) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int i = 0;
        int j = 0;
        int subLength = 0;
        int[] index = new int[128];
        for (int k = 0; k < index.length; k++) {
            index[k] = -1;
        }
        while(j < s.length()) {
            if (index[s.charAt(j)] != -1 && index[s.charAt(j)] + 1 >= i) {
                i = index[s.charAt(j)] + 1;
            }
            if (j-i+1 > subLength) {
                subLength = j-i+1;
            }
            index[s.charAt(j)] = j;
            j++;
        }
        return subLength;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int preVal = -9999;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mergeLen = len1+len2;
        int[] mergeNums = new int[mergeLen];
        int cur1=0, cur2=0;
        for(int i=0; i<len1+len2; i++) {
            if(cur1<len1 && (cur2>=len2 || nums1[cur1] <= nums2[cur2])) {
                mergeNums[i] = nums1[cur1++];
                continue;
            }
            if(cur2<len2 && (cur1>=len1 || nums1[cur1] > nums2[cur2])) {
                mergeNums[i] = nums2[cur2++];
                continue;
            }
        }
        double ret = 0;
        if(mergeLen % 2 == 0) {
            int mi1 = mergeLen/2-1;
            int mi2 = mi1+1;
            ret = (double)(mergeNums[mi1]+mergeNums[mi2])/2;
        } else {
            ret = mergeNums[(mergeLen-1)/2];
        }
        return ret;
    }

    public static String reverse(String str){
        StringBuffer sb = new StringBuffer();
        for (int x = str.length() -1; x >= 0; x--) {
            sb.append(str.charAt(x));
        }
        return sb.toString();
    }

    private static int isLoop(String str, int start, int end) {
        int s = start;
        int e = end;
        int count = 0;
        while(s>=0 && e<str.length()) {
            if (str.charAt(s) == str.charAt(e)) {
                s--;
                e++;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    //获取最长回文字符串
    public static String longestPalindrome1st(String s) {
        int len = s.length();
        String str = new String();
        boolean[] flag = new boolean[len];
        for(int i = 0 ; i < len ; i++) {
            for(int j = 0 ; j <= i ; j++) {
                flag[j] = (s.charAt(i) == s.charAt(j)) && (i - j < 3 || flag[j + 1]);
                if(flag[j] && i - j + 1 > str.length()) {
                    str = s.substring(j, i + 1);
                }
            }
        }
        return str;
    }
    public static String longestPalindrome2nd(String s) {
        int len = s.length();
        String str = new String();
        int maxHeight = 0;
        int start=0, end=0;
        for(int i = 0 ; i < len ; i++) {
            int height1= isLoop(s, i, i+1);
            if (maxHeight < 2*height1) {
                maxHeight = 2*height1;
                start = i-height1;
                end = start + height1*2-1;
            }
            int height2= isLoop(s, i, i);
            if (maxHeight < 2*height2-1) {
                maxHeight=2*height2-1;
                start = i-height2+1;
                end = start + height2*2-1;
            }
        }

        return s.substring(start, end);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //{1,2,4,7,3,5,6,8}
    //{4,7,2,1,5,3,8,6}
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        return null;
    }
    //链表每K个反转
    public static class ListNode {
        int val;
        ListNode next = null;
    }
    public static int getNodeLength(ListNode node) {
        int count = 0;
        ListNode n = node;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }
    public static ListNode getSubReverse(ListNode head, int k) {
        if (head == null) return null;
        ListNode curNode = head;
        ListNode preNode = null;
        ListNode nextCode = null;
        int count = 0;
        while(curNode != null && count++<k) {
            nextCode = curNode.next;
            if (preNode != null) {
                curNode.next = preNode;
            }
            preNode = curNode;
            curNode = nextCode;
        }

        head.next = nextCode;

        return preNode;
    }
    public static ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode newHead = null;
        int len = getNodeLength(head);
        ListNode beforeHead = head;
        ListNode reverseHead = null;
        ListNode preReverseTail = null;
        int iterCount = len/k;
        for (int i=0; i<iterCount+1; i++) {
            int remainLen = len - i*k;
            if (remainLen>0 && remainLen<=len%k) {
                if(newHead == null) newHead = head;
                break;
            } else {
                reverseHead = getSubReverse(beforeHead, k);
            }
            if (reverseHead == null) break;
            if (preReverseTail != null) preReverseTail.next = reverseHead;
            preReverseTail = beforeHead;
            beforeHead = beforeHead.next;
            if (newHead == null) newHead = reverseHead;
        }

        return newHead;
    }
    public static void printNodeList(ListNode nodes) {
        ListNode iter = nodes;
        StringBuilder sb = new StringBuilder();
        while(iter != null) {
            sb.append(String.valueOf(iter.val));
            iter = iter.next;
            if (iter!=null) sb.append("->");
        }
        System.out.println(sb.toString());
    }
    //快速排序
    public static void quickSort(int[] data) {
        sortInner(data, 0, data.length-1);
    }
    private static void sortInner(int[] data, int s, int e) {
        if (s >= e) return;
        int minCurs = s;
        int maxCurs = e;
        int curCurs = s;
        while(minCurs < maxCurs) {
            if (maxCurs > curCurs) {
                if (data[maxCurs] < data[curCurs]) {
                    int t = data[maxCurs];
                    data[maxCurs] = data[curCurs];
                    data[curCurs] = t;
                    curCurs = maxCurs;
                    minCurs++;
                } else {
                    maxCurs--;
                }
            }
            if (minCurs < curCurs) {
                if (data[minCurs] > data[curCurs]) {
                    int t = data[minCurs];
                    data[minCurs] = data[curCurs];
                    data[curCurs] = t;
                    curCurs = minCurs;
                    maxCurs--;
                } else {
                    minCurs++;
                }
            }
        }
        sortInner(data, s, curCurs-1);
        sortInner(data, curCurs+1, e);
    }
    //冒泡排序
    public static void Maopao(int[] data) {
        int needToScan = data.length;
        while(needToScan > 0){
            for (int j=0; j<needToScan; j++) {
                if (j == data.length-1) break;
                int val = data[j];
                if (val > data[j+1]) {
                    int t = data[j+1];
                    data[j+1] = val;
                    data[j] = t;
                }
            }
            needToScan--;
        }
    }
    //牛逼的LRU
    public static class LruNode{
        int key;
        int val;
        LruNode next;
        LruNode pre;

        public LruNode() {}
        public LruNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    public static class MyLru {
        LruNode head = new LruNode();
        LruNode tail = new LruNode();
        Hashtable<Integer, LruNode> cache = new Hashtable<>();
        int cap = 0;

        public MyLru(int cap) {
            head.next = tail;
            tail.pre = head;
            this.cap = cap;
        }

        public void set(int key, int val) {
            LruNode node = cache.get(key);
            if (node == null) {
                if (cache.size() >= cap) {
                    removeFromList(tail.pre);
                }
                node = new LruNode();
                node.key = key;
                node.val = val;
                cache.put(key, node);
                LruNode top = head.next;
                head.next = node;
                node.pre = head;
                node.next = top;
                top.pre = node;
            } else {
                removeFromList(node);
                addToHead(node);
            }

        }
        private void addToHead(LruNode node) {
            cache.put(node.key, node);
            LruNode next = head.next;
            head.next = node;
            node.pre = head;
            node.next = next;
            next.pre = node;
        }
        private void removeFromList(LruNode node) {
            cache.remove(node.key);
            LruNode pre = node.pre;
            LruNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }
        public int get(int key) {
            LruNode node = cache.get(key);
            if (node == null) return -1;
            removeFromList(node);
            addToHead(node);
            return node.val;
        }

    }
    public static int[] LRU (int[][] operators, int k) {
        // write code here
        MyLru lru = new MyLru(k);
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i=0; i<operators.length; i++) {
            int[] cmd = operators[i];
            int c = cmd[0];
            switch (c) {
                case 1:
                    lru.set(cmd[1], cmd[2]);
                    break;
                case 2:
                    resultList.add(lru.get(cmd[1]));
                    break;
            }
        }
        int[] result = new int[resultList.size()];
        for (int i=0; i<resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    //判断链表中是否有环
    public class ListNode2 {
        int val;
        ListNode2 next;

        ListNode2(int x) {
            val = x;
            next = null;
        }
    }
    public boolean hasCycle(ListNode2 head) {
        boolean ret = false;
        ListNode2 slowCursor = head;
        ListNode2 fastCursor = head;
        if(head == null) return false;
        if(head.next == null) return false;
        while(slowCursor!=null && fastCursor!=null) {
            slowCursor = slowCursor.next;
            if (fastCursor.next == null) break;
            fastCursor = fastCursor.next.next;
            if (slowCursor == fastCursor) {
                ret = true;
                break;
            }
        }
        return ret;
    }
    //三序遍历
    public static int[][] threeOrders (TreeNode root) {
        // write code here
        List<TreeNode> frontOrders = new ArrayList<>();
        frontOrder(root, frontOrders);
        List<TreeNode> middleOrders = new ArrayList<>();
        middleOrder(root, middleOrders);
        List<TreeNode> backOrders = new ArrayList<>();
        backOrder(root, backOrders);

        int[][] result = new int[3][frontOrders.size()];
        for (int i=0; i<frontOrders.size(); i++) {
            result[0][i] = frontOrders.get(i).val;
        }
        for (int i=0; i<middleOrders.size(); i++) {
            result[1][i] = middleOrders.get(i).val;
        }
        for (int i=0; i<backOrders.size(); i++) {
            result[2][i] = backOrders.get(i).val;
        }
        return result;
    }
    //前序遍历
    private static void frontOrder(TreeNode node, List<TreeNode> vals) {
        if (node != null) {
            vals.add(node);
        }
        if (node.left != null) {
            frontOrder(node.left, vals);
        }
        if (node.right != null) {
            frontOrder(node.right, vals);
        }
    }
    //中序遍历
    private static void middleOrder(TreeNode node, List<TreeNode> vals) {
        if (node == null) return;
        if (node.left != null) {
            middleOrder(node.left, vals);
        }
        vals.add(node);
        if (node.right != null) {
            middleOrder(node.right, vals);
        }
    }
    //后序遍历
    private static void backOrder(TreeNode node, List<TreeNode> vals) {
        if (node == null) return;
        if (node.left != null) {
            backOrder(node.left, vals);
        }
        if (node.right != null) {
            backOrder(node.right, vals);
        }
        vals.add(node);
    }
    //二分查找
    public static int upper_bound_ (int n, int v, int[] a) {
        // write code here
        if (a[n-1] < v) {
            return -1;
        }
        if (a[0] > v) {
            return 1;
        }
        int index = binaryFind(v, 0, n-1, a);
        for (int i=index-1; i>=0; i--) {
            if (a[i] == v) {
                index--;
            } else {
                break;
            }
        }
        return index+1;
    }
    private static int binaryFind(int v, int s, int e, int[]a) {
        if (s > e) return -1;
        int middleIndex = (s+e)/2;
        if (a[middleIndex] < v) {
            return binaryFind(v, middleIndex+1, e, a);
        } else if(a[middleIndex] > v) {
            return binaryFind(v, s, middleIndex-1, a);
        } else if (a[middleIndex] == v) {
            return middleIndex;
        }
        return -1;
    }
    //找出最小的k个数
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > input.length) return result;
        quickSort(input);
        for (int i=0; i<k; i++) {
            result.add(input[i]);
        }
        return result;
    }
    //层序遍历二叉树
    public static ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> ele = new ArrayList<>();
            LinkedList<TreeNode> tq = new LinkedList<>();
            for (TreeNode node : queue) {
                ele.add(node.val);
                if (node.left != null) {
                    tq.offer(node.left);
                }
                if (node.right != null) {
                    tq.offer(node.right);
                }
            }
            queue.clear();
            queue = tq;
            result.add(ele);
        }
        return result;
    }
    //链表排序
    private static ListNode mergeSortNode(ListNode head, ListNode tail) {
        if (head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;
        ListNode left = mergeSortNode(head, slow);
        ListNode right = mergeSortNode(rightHead, tail);
        ListNode curLeftNode = left;
        ListNode curRightNode = right;
        ListNode innerHead = new ListNode();
        ListNode pre = innerHead;
        while (curLeftNode != null || curRightNode!=null) {
            if(curLeftNode != null && curRightNode!=null) {
                if (curLeftNode.val > curRightNode.val) {
                    pre.next = curRightNode;
                    pre = curRightNode;
                    curRightNode = curRightNode.next;
                } else {
                    pre.next = curLeftNode;
                    pre = curLeftNode;
                    curLeftNode = curLeftNode.next;
                }
            }

            if (curLeftNode == null) {
                pre.next = curRightNode;
                break;
            }
            if (curRightNode == null) {
                pre.next = curLeftNode;
                break;
            }
        }
        return innerHead.next;
    }
    //寻找第k大
    private static void quickSortForK(int[] a, int s, int e, int k) {
        if (s >= e) return;
        int left = s;
        int right = e;
        int cursor = s;
        while (left < right) {
            if (right > cursor) {
                if (a[cursor] > a[right]) {
                    int t = a[right];
                    a[right] = a[left];
                    a[left] = t;
                    left++;
                    cursor = right;
                } else {
                    right--;
                }
            }
            if (left < cursor) {
                if (a[cursor] < a[left]) {
                    int t = a[cursor];
                    a[cursor] = a[left];
                    a[left] = t;
                    right--;
                    cursor = left;
                } else {
                    left++;
                }
            }
        }
        if (cursor == a.length-k) return;
        if (cursor > a.length-k) {
            quickSortForK(a, s, cursor-1,k);
        }
        if (cursor < a.length-k) {
            quickSortForK(a, cursor+1, e,k);
        }
//        quickSortForK(a, s, cursor-1,k);
//        quickSortForK(a, cursor+1, e,k);
    }
    public static int findKth(int[] a, int n, int K) {
        // write code here
        quickSortForK(a, 0, n-1, K);
        return a[n-K];
    }
    //青蛙跳台阶
    public static int JumpFloor(int target) {
        if (target <= 0) return 1;
        int type1 = 0, type2 = 0;
        if (target - 1 >= 0) {
            type1 = JumpFloor(target-1);
        }
        if (target - 2 >= 0) {
            type2 = JumpFloor(target-2);
        }
        return type1+type2;
    }
    //两数之和
    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public static int[] twoSum (int[] numbers, int target) {
        // write code here
        int[] result = new int[2];
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i=0; i<numbers.length; i++) {
            Integer index = cache.get(target - numbers[i]);
            if (null != index && index!=i) {
                if(index > i) {
                    result[0] = i + 1;
                    result[1] = index + 1;
                } else {
                    result[0] = index + 1;
                    result[1] = i + 1;
                }
                break;
            }
            if (!cache.containsKey(numbers[i])) cache.put(numbers[i], i);
        }
        return result;
    }
    //最大子数组累加和
    public static int maxsumofSubarray (int[] arr) {
        // write code here
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int startIndex = 0;
        for (int i=0; i<arr.length; i++) {
            cur += arr[i];
            if (cur < 0) {
                startIndex = i+1;
                cur = 0;
            } else {
                if (max < cur) max = cur;
            }
        }
        return max;
    }
    //合并两个
    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        ListNode head = new ListNode();
        ListNode leftCurNode = l1;
        ListNode rightCurNode = l2;
        ListNode preNode = head;
        while (leftCurNode!=null || rightCurNode!=null) {
            if (leftCurNode!=null && rightCurNode!=null) {
                if (leftCurNode.val > rightCurNode.val) {
                    preNode.next = rightCurNode;
                    rightCurNode = rightCurNode.next;
                } else {
                    preNode.next = leftCurNode;
                    leftCurNode = leftCurNode.next;
                }
                preNode = preNode.next;
            }
            if (leftCurNode == null) {
                preNode.next = rightCurNode;
                break;
            }
            if (rightCurNode == null) {
                preNode.next = leftCurNode;
                break;
            }
        }
        return head.next;
    }
    //两个栈实现队列
    static Stack<Integer> stack1 = new Stack<Integer>();
    static Stack<Integer> stack2 = new Stack<Integer>();

    public static void push(int node) {
        stack1.push(node);
    }

    public static int pop() {
        Integer ret = null;
        while (true) {
            try {
                Integer val = stack1.pop();
                if (stack1.empty()) {
                    ret = val;
                    break;
                }
                stack2.push(val);

            } catch(Exception e) {
                break;
            }
        }
        while(true) {
            try {
                stack1.push(stack2.pop());
                if (stack2.empty()) break;

            } catch (Exception e) {
                break;
            }
        }
        return ret;
    }
    //最长无重复子串
    public static int maxLength (int[] arr) {
        // write code here
        int number = 0;
        int left=0;
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int right=0; right<arr.length; right++) {
            Integer dupIndex = cache.get(arr[right]);
            if (number < right - left) {
                number = right-left;
            }
            if (null != dupIndex && dupIndex >= left) {
                left = dupIndex+1;
            }
            cache.put(arr[right], right);
        }
        return number;
    }
    //合并两个有序数组
    private static void moveArray(int arr[], int start, int end) {
        for (int i=end; i>start; i--) {
            arr[i] = arr[i-1];
        }
    }
    public static void merge(int A[], int m, int B[], int n) {
        int bIndex = 0;
        int i = 0;
        int len = m;
        for (i=0; i<len; i++) {
            if (bIndex >= n) break;
            if (A[i] > B[bIndex]) {
                moveArray(A, i, len);
                A[i] = B[bIndex++];
                len++;
            }
        }
        while(bIndex<n) {
            A[i++] = B[bIndex++];
        }
        return ;
    }
    //寻找链表环的入口点
    public static ListNode detectCycle(ListNode head) {
        ListNode ent = null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode tmp = head;
                while (tmp != slow) {
                    tmp = tmp.next;
                    slow = slow.next;
                }
                return tmp;
            }
        }
        return ent;
    }
    //匹配括号序列 '(',')','{','}','['和']',
    public static boolean isValid (String s) {
        // write code here
        Stack<String> stack = new Stack<>();
        try {
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                        stack.push(String.valueOf(c));
                        break;
                    case ')':
                        if (!"(".equals(stack.pop())) return false;
                        break;
                    case '{':
                        stack.push(String.valueOf(c));
                        break;
                    case '}':
                        if (!"{".equals(stack.pop())) return false;
                        break;
                    case '[':
                        stack.push(String.valueOf(c));
                        break;
                    case ']':
                        if (!"[".equals(stack.pop())) return false;
                        break;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return stack.empty();
    }
    //删除链表的倒数第n个节点并返回链表的头指针
    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        int len = getNodeLength(head);

        if (len == n) return head.next;

        int count=0;
        ListNode curNode = head;

        while (count++ < len-n-1) {
            curNode = curNode.next;
        }

        ListNode next = curNode.next.next;
        curNode.next = next;

        return head;
    }
    //最长公共子串
    public static String LCS (String str1, String str2) {
        // write code here
        int start = -1;
        int max = 0;
        int[][] matrix = new int[str1.length()][str2.length()];
        for (int i=0; i<str1.length(); i++) {
            for (int j=0; j<str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i > 0 && j > 0 && matrix[i-1][j-1] >= 1) {
                        matrix[i][j] = matrix[i-1][j-1]+1;
                    } else {
                        matrix[i][j] = 1;
                    }
                    if (max < matrix[i][j]) {
                        max = matrix[i][j];
                        start = i - max + 1;
                    }
                }
            }
        }
        if (max > 0) return str1.substring(start, start+max);
        return "-1";
    }
    //两个链表相加
    public static ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode cur = head1;
        while (cur != null) {
            stack1.push(cur);
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            stack2.push(cur);
            cur = cur.next;
        }
        int incr = 0;
        ListNode retHead = null;
        ListNode retPre = null;
        while (!stack1.empty() || !stack2.empty()) {
            ListNode valNode = new ListNode();
            int v3 = 0;
            if (!stack1.empty() && !stack2.empty()) {
                ListNode l1 = stack1.pop();
                ListNode l2 = stack2.pop();
                v3 = l1.val + l2.val + incr;

            } else if (!stack1.empty()) {
                ListNode l1 = stack1.pop();
                v3 = l1.val + incr;

            } else if (!stack2.empty()) {
                ListNode l2 = stack2.pop();
                v3 = l2.val + incr;
            }
            valNode.val = v3;
            if (valNode.val >= 10) {
                valNode.val = valNode.val%10;
                incr = 1;
            } else {
                incr = 0;
            }
            if (retPre != null) {
                retPre.next = valNode;
            } else {
                retHead = valNode;
            }
            retPre = valNode;
        }
        //反转链表
        ListNode rePre = null;
        ListNode reCur = retHead;
        while (reCur != null) {
            ListNode next = reCur.next;
            reCur.next = rePre;
            rePre = reCur;
            reCur = next;

        }
        return rePre;
    }
    //寻找二叉树两个节点的公共祖先
    public static int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        if(root == null){
            return 0;
        }

        //如果找到需要查找的值后，对于他的父节点那层循环来说，返回值即是找到的这个值。
        if(root.val == o1 || root.val == o2){
            return root.val;
        }

        int left = lowestCommonAncestor(root.left,o1,o2);
        int right= lowestCommonAncestor(root.right,o1,o2);

        //如果左右节点总有一个为0，说明只找到一个节点，所以返回值永远是找到的那个节点的值；
        //当找到了最近公共祖先节点，即这个祖先节点的left和right分别是我们要找到o1，o2的值。则会返回当前这个公共祖先节点的值。
        if(left == 0){
            return right;
        }
        if(right == 0){
            return left;
        }
        return root.val;
    }
    /**
     * max water
     * @param arr int整型一维数组 the array
     * @return long长整型
     */
    public long maxWater (int[] arr) {
        // write code here
        int cap = 0;
        int leftEdge = 0;
        int rightEdge = 0;
        for (int i=0; i<arr.length; i++) {
            int val = arr[i];
            if (leftEdge == 0) {
                leftEdge = val;
                continue;
            }
            if (val > leftEdge) {
                leftEdge = val;
                continue;
            }
            if (val < leftEdge) {
                cap += leftEdge - val;
            }
        }
        return cap;
    }
    public static void main(String[] args) {
//        String str = "abcabcbb";
//        System.out.println(lengthOfLongestSubstring(str));
        //System.out.println(String.valueOf(findMedianSortedArrays(new int[]{1,5}, new int[]{2,7,6})));
//        System.out.println(longestPalindrome2nd("abc1234321ab"));
        //TreeNode node = reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8}, new int[]{4,7,2,1,5,3,8,6});
//        System.out.println(node);
        //每k个反转链表
//        int[] data = new int[]{1,2,3,4,5,6,7};
//        ListNode head = null;
//        ListNode preNode = null;
//        for (int i=0; i<data.length; i++) {
//            ListNode tn = new ListNode();
//            tn.val = data[i];
//            if (preNode == null) {
//                head = tn;
//            } else {
//                preNode.next = tn;
//            }
//            preNode = tn;
//        }
//        printNodeList(head);
//        ListNode reversNode = reverseKGroup(head, 2);
//        printNodeList(reversNode);
//        int[] data = new int[]{5,1,6,2,5};
//        quickSort(data);
//        System.out.println(data.toString());
//        System.out.println(Arrays.toString(LRU(new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}}, 3)));
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        int[][] rest = threeOrders(root);
//        JSON.toJSONString(rest);
//        System.out.println(upper_bound_(10, 2, new int[]{1,1,2,3,7,7,7,9,9,10}));
//        ArrayList<Integer> result = GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8}, 10);

//        ListNode head = null, pre=null;
//        int[] data = new int[]{4,2,1,3};
//        for (int i=0; i<data.length; i++) {
//            ListNode node = new ListNode();
//            node.val = data[i];
//            if (head == null) head = node;
//            if (pre != null) {
//                pre.next = node;
//            }
//            pre = node;
//        }
//        ListNode tail = head;
//        while(tail.next != null) {tail = tail.next;}
//        ListNode nods = mergeSortNode(head, tail);

//        int[] data = new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
//        System.out.println(findKth(data, data.length, 24));
        //青蛙跳台阶
//        System.out.println(JumpFloor(4));
//        System.out.println(Arrays.toString(twoSum(new int[]{3,2,4}, 6)));
//        System.out.println(maxsumofSubarray(new int[]{1, -2, 3, 5, -2, 6, -1}));
//        push(1);push(2);push(3);
//        Integer ret = null;
//        while (null != (ret=pop())) {
//            System.out.println(ret);
//        }
//        System.out.println(maxLength(new int[]{2,2}));

//        int[] A = new int[10];
//        A[0] = 1;
//        A[1] = 2;
//        A[2] = 5;
//        int[] B = new int[]{3,4};
//        merge(A, 3, B, 2);

//        System.out.println(isValid("([)]"));

//        System.out.println(LCS("1AB2345CD", "12345EF"));


    }

}
