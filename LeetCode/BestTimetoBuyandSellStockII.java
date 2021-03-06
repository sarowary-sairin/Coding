/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple 
transactions at the same time (ie, you must sell the stock before you buy again).
*/

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int start = 0, prev = prices[start], max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] >= prev) {
                prev = prices[i];
            }
            else {
                max += prev - prices[start];
                start = i;
                prev = prices[start];
            }
        }
        if(start < prices.length) {
            max += prev - prices[start];
        }
        return max;
    }
}
/*
    Second Round
*/
class Solution2 {
    public int maxProfit(int[] prices) {
        int start = 0, prev = 0, max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[prev] > prices[i]) {
                int profit = prices[prev] - prices[start];
                max += profit;
                start = i;
            }
            prev = i;
        }
        if(start < prices.length) {
            max += prices[prev] - prices[start];
        }
        return max;
    }
}
/*
    Third Round
*/
class Solution3 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int buy = 0, max = 0, n = prices.length;
        for(int i = 1; i < n; i++) {
            if(prices[i] < prices[i - 1]) {
                max += prices[i - 1] - prices[buy];
                buy = i;
            }
        }
        if(buy < n) {
            max += prices[n - 1] - prices[buy];
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] prices = {1, 2, 1, 3, 0, 3};
        System.out.println(solution.maxProfit(prices));
    }
}
