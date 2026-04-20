package com.example.zlxpractice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 经典手撕题
 */

public class Classic {

    // 括号匹配问题
    // 有效的括号
    // 输入：s = "()"
    // 输出：true
    public boolean isValid(String s) {
        // 定义栈
        Stack<Character> stack = new Stack<>();
        // 遍历
        for(char c: s.toCharArray()){
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if(c == ')' && top != '('){return false;}
                if(c == ']' && top != '['){return false;}
                if(c == '}' && top != '{'){return false;}
            }
        }
        return stack.isEmpty();
    }

    // 最长有效括号
    // 输入：s = ")()())"
    // 输出：4
    // 解释：最长有效括号子串是 "()()"
    public int longestValidParentheses(String s) {
        // 还是用栈来匹配，
        // 错误思路：减掉栈中没有匹配上的长度
        // 正确思路：栈中存储下标
        Deque<Integer> stack = new ArrayDeque<>();
        // 哨兵
        stack.push(-1);
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            if(s.charAt(i) == ')'){
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }
                else {
                    max =  Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }


    // 反转数组
    // 输入：s = ["h","e","l","l","o"]
    // 输出：["o","l","l","e","h"]
    // 应该属于双指针问题，很简单，先放到这里
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    // 验证回文串
    // 1.
    // 输入: s = "A man, a plan, a canal: Panama"
    // 输出：true
    // 解释："amanaplanacanalpanama" 是回文串
    public boolean isPalindrome(String s) {
        // 大小写转换
        s = s.toLowerCase();
        // 正则排除非字母
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(s);
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                System.out.println(s.charAt(left) + s.charAt(right));
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 2.回文数
    // 输入：x = -121
    // 输出：false
    // 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数
    public boolean isPalindrome(int x) {
        if (x == 0){
            return true;
        }
        // 负数一定不是回文数
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        // 完全反转x
//        int reversed = 0;
//        int num = x;
//        while (num != 0) {
//            reversed = reversed * 10 + num % 10;
//            num /= 10;
//        }
        // 反转一半x
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }

    // 最长回文子串--LeetCode 5、647
    // 1.回文子串数目
    // 输入：s = "abc"
    // 输出：3
    // 解释：三个回文子串: "a", "b", "c"
    public int countSubstrings(String s) {
        // 利用中心扩展的思路
        // 字符串每个位置都可以作为回文的中心
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            // 1.奇数回文 aba
            count += expand(s, i, i);
            // 2.偶数回文 abba
            count += expand(s, i, i + 1);
        }
        return count;
    }

    // 扩展方法，从中心向两边扩展
    public int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    // 2.最长的回文子串
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数回文长度
            int len1 = expand2(s, i, i);
            // 偶数回文长度
            int len2 = expand2(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 更新最长区间
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand2(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 扩展结束后 left right 已越界
        return right - left - 1;
    }


    // 斐波那契数列 F(n)
    // 输入：n = 2
    // 输出：1
    // 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
    public int fib1(int n) {
        if (n == 0){
            return 0;
        }
        else if (n == 1){
            return 1;
        }
        else{
            return fib1(n-1) + fib1(n-2);
        }
    }

    public int fib2(int n) {
        if (n <= 1){
            return n;
        }
        // 节省空间&降低复杂度
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }


    // 爬楼梯 属于动态规划？---LeetCode 70
    // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
    // 输入：n = 3
    // 输出：3
    // 解释：有三种方法可以爬到楼顶。
    // 1. 1 阶 + 1 阶 + 1 阶
    // 2. 1 阶 + 2 阶
    // 3. 2 阶 + 1 阶
    public int climbStairs(int n) {
        if (n <= 2){
            return n;
        }
        // 节省空间&降低复杂度
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++){
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
        //climbStairs(n-1)+climbStairs(n-2);
    }


    // 二分查找
    // 基本
    // 输入: nums = [-1,0,3,5,9,12], target = 9
    // 输出: 4
    // 解释: 9 出现在 nums 中并且下标为 4
    // 如果target=8，则返回target应该插入的下标位置，即4
    public int searchIndexOf(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }


    // 二分 找左边界
    // 跳出循环的条件不一样（左边正常走，右边移动到mid）
    // 找出给定目标值在数组中的开始位置和结束位置
    // 如果数组中不存在目标值 target，返回 [-1, -1]
    // 输入：nums = [5,7,7,8,8,10], target = 8
    // 输出：[3,4]
    public int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target);
        if(left == nums.length || nums[left] != target){
            return new int[]{-1, -1};
        }
        int right = findBound(nums, target+1) - 1;
        return new int[]{left, right};
    }

    // 搜索边界
    public int findBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }


    // 二分 平方根
    // 输入：x = 8
    // 输出：2
    // 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
    public int mySqrt(int x){
        int left = 0;
        int right = x;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(x == mid * mid){
                return mid;
            }
            if(x < mid * mid){
                right = mid - 1;
            }
            else if(x > mid * mid){
                left = mid + 1;
            }
        }
        return right;
    }


    public static void main(String[] args) {
        Classic classic = new Classic();
        int[] nums = new int[]{1, 4, 4, 6, 7, 9};
//        System.out.println(Arrays.toString(classic.searchRange(nums, 4)));
        System.out.println(classic.mySqrt(8));
    }
}
