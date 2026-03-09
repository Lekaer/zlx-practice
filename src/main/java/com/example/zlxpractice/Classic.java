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

    // 爬楼梯 属于动态规划？---LeetCode 70

    public static void main(String[] args) {
        Classic classic = new Classic();
        String s = "aaabcbcba";
        System.out.println(classic.longestPalindrome(s));
    }
}
