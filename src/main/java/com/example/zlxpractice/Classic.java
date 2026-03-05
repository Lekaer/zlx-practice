package com.example.zlxpractice;

import java.util.ArrayDeque;
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

    // 斐波那契数列


    // 最长回文子串


    // 爬楼梯 属于动态规划？

    public static void main(String[] args) {
        Classic classic = new Classic();
        String s = "))()(()))()";
        System.out.println(classic.longestValidParentheses(s));
    }
}
