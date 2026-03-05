package com.example.zlxpractice;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口问题
 */

public class Window {


    /**
     *
     * 1.固定窗口大小
     */
    // 定长子串中元音的最大数目
    // 固定窗口大小
    public int maxVowels(String s, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            char c =  s.charAt(i);
            if(isVowel(c)){
                count++;
            }
        }
        System.out.println(count);
        int max = count;
        for (int i = k; i < s.length(); i++) {
            char c =  s.charAt(i);
            char left = s.charAt(i-k);
            if(isVowel(c)){
                count++;
            }
            if(isVowel(left)){
                count--;
            }
            System.out.println(c);
            System.out.println(count);
            max = Math.max(max,count);
        }
        return max;
    }

    // 判断字符是否是元音
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // 和为固定值的、长度最小的子数组
    // 输入：target = 7, nums = [2,3,1,2,4,3]
    // 输出：2
    // 解释：子数组 [4,3] 是该条件下的长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口问题
        // 移动右指针，计算符合条件的子数组长度
        // 满足条件后，移动右指针，直到不满足条件，继续移动右指针
        int n = nums.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(right<n){
            sum += nums[right];
            while(sum >= target){
                minLen = Math.min(minLen,right-left+1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        if(minLen == Integer.MAX_VALUE){
            return 0;
        }
        return minLen;
    }

    /**
     *
     * 2.最短子数组/子串
     */

    // 最小覆盖子串
    // 输入：s = "ADOBECODEBANC", t = "ABC"
    // 输出："BANC"
    // 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        // 定义一个map保存t中的字符频率，一个map保存滑动窗口的相应字符频率
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> win = new HashMap<>();
        // 循环t，构造need
        for (char c: t.toCharArray()){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        // value 代表win中与need一致的key的个数
        int value = 0;
        // 滑动窗口，win保存窗口中包含t中字符的频率
        // 如果value与need元素个数相等，则尝试缩短左窗口，直到不相等，再移动右窗口，寻找下一个相等的窗口
        int left = 0;
        int right = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            // value是否要加
            if(need.containsKey(c)){
                // 窗口移动，更新win（map）和value
                win.put(c, win.getOrDefault(c, 0) + 1);
                if(win.get(c) == need.get(c)){
                    value++;
                }
            }
            // 移动左指针，可能需要多次移动所以用while，移动的目的是缩小字符串长度，直到字符串不满足要求
            while(value == need.size()){
                if(right-left < minLen){
                    minLen = right-left;
                    start = left;
                }
                char d = s.charAt(left);
                left++;
                // 窗口移动，更新value和win（map）
                if(need.containsKey(d)){
                    if(win.get(d) == need.get(d)){
                        value--;
                    }
                    win.put(d, win.get(d)-1);
                }
            }
        }
        // 根据start 和 minLen获取最小覆盖子串
        return minLen == Integer.MAX_VALUE? "":s.substring(start, start+minLen);
    }


    /**
     *
     * 3.最长子数组/子串
     */
    // 无重复字符的最长子串
    // 输入: s = "abcabcbb"
    // 输出: 3
    // 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int n = s.length();
        int left = 0;
        // map 用于记录字符以及字符最后出现的位置
        Map<Character, Integer> map = new HashMap<>();

        for(int right = 0; right < n; right++){
            char c = s.charAt(right);

            if (map.containsKey(c)){
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            max = Math.max(max, right - left +1);
        }
        return max;
    }

    // 最大连续1的个数
    // 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
    // 输出：6
    // 解释：[1,1,1,0,0,1,1,1,1,1,1]
    //                 0         0
    // 两个数字从0翻转到 1，最长的子数组长度为 6
    public int longestOnes(int[] nums, int k) {
        // 窗口内有k个0时，右指针移动，记录最长长度
        // 多于k个0时，左指针移动，移动到k个0的位置
        int zeroCnt = 0;
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCnt++;
            }
            if (zeroCnt <= k){
                max = Math.max(max, right - left + 1);
            }
            while (zeroCnt > k) {
                if (nums[left] == 0) {
                    zeroCnt--;
                }
                left++;
            }
            right++;
        }
        return max;
    }


    /**
     *
     *
     * 4.字符计数窗口
     */
    // 字符串的排列
    // 输入：s1 = "ab" s2 = "eidbaooo"
    // 输出：true
    // 解释：s2 包含 s1 的排列之一 ("ba")
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0 || len2 < len1) {
            return false;
        }
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < len1; i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(arr1, arr2)) {
            return true;
        }
        for (int i = len1; i < len2; i++) {
            char right =  s2.charAt(i);
            char left = s2.charAt(i-len1);
            arr2[right - 'a']++;
            arr2[left - 'a']--;
            if (Arrays.equals(arr1, arr2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "weallloveyou";
        //int cnt = new Window().maxVowels(s, k);
        Window window = new Window();
        int[] nums = {0,0,1,1};
        int k = 3;
        String s2 = "eidbaooo";
        String s1 = "ab";
        System.out.println(window.checkInclusion(s1,s2));
    }
}
