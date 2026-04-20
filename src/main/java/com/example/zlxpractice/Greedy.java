package com.example.zlxpractice;

/**
 * 贪心问题
 */
public class Greedy {

    // 跳跃游戏
    // 位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度
    // 判断你是否能够到达最后一个下标
    // 输入：nums = [2,3,1,1,4]
    // 输出：true
    // 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标
    public boolean canJump(int[] nums){
        if (nums == null || nums.length == 0){
            return false;
        }
        // 维护最远距离
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            if(i > max){
                return false;
            }
            if(i+nums[i] > max){
                max = i+nums[i];
            }
        }
        return true;
    }


    // 跳跃游戏
    // 返回最小跳跃次数
    public int jump(int[] nums) {
        int step = 0;
        int maxPos = 0;
        int end = 0;
        for(int i = 0; i < nums.length - 1; i++){
            maxPos = Math.max(maxPos, i + nums[i]);
            if(i == end){
                step++;
                end = maxPos;
            }
        }
        return step;
    }


    // 买股票的最佳时机
    // 数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
    // 某一天 买入，未来某一天 卖出
    // 设计一个算法来计算你所能获取的最大利润
    // 不能获取任何利润，返回 0
    // 输入：[7,1,5,3,6,4]
    // 输出：5
    // 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    // 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    public int maxProfit(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxProfit = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            int profit = prices[i] - min;
            maxProfit = Math.max(maxProfit, profit);
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }


    // 买股票的最大利润
    // 数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
    // 可多次买入后卖出，可同一天卖出再买入，但最多只能持有一股
    // 返回 你能获得的 最大 利润
    // 输入：prices = [7,1,5,3,6,4]
    // 输出：7
    // 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
    // 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
    // 最大总利润为 4 + 3 = 7
    public int maxProfit2(int[] prices){
        int price = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > price){
                profit += prices[i] - price;
            }
            price = prices[i];
        }
        return profit;
    }




    public static void main(String[] args) {
        Greedy greedy = new Greedy();
//        System.out.println(greedy.canJump(new int[]{1,2,0}));
        System.out.println(greedy.maxProfit2(new int[]{4,2,3,1,5,6,7,8,9}));
    }

}
