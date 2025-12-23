package daily;

import java.util.Arrays;

class StockStrat3652 {
    public static void main(String[] args) {
        int[] price = new int[]{4, 2, 8};
        int[] strategy = new int[]{-1, 0, 1};

        StockStrat3652 s = new StockStrat3652();

        System.out.println(s.maxProfit(price, strategy, 2));
    }

    public long maxProfit(int[] prices, int[] strategy, int k) {
        // prefix & sum -> keep track of the sum outside window
        // suffix & sum -> 

        // keep track of the sum in first half
        int n = prices.length;
        
        long[] sufSum = new long[n];
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (strategy[i] == -1) {
                sum -= prices[i];
            } else if (strategy[i] == 1) {
                sum += prices[i];
            }

            sufSum[i] = sum;
        }
        long ans = sum;

        sum = 0;
        long[] preSum = new long[n];
        for (int i = 0; i < n; i++) {
            if (strategy[i] == -1) {
                sum -= prices[i];
            } else if (strategy[i] == 1) {
                sum += prices[i];
            }

            preSum[i] = sum;
        }

        int win2 = 0;
        for (int i = k/2; i < k; i++) {
            win2 += prices[i];
        }
        
        int l = 0;
        for (int r = k - 1; r < n; r++) {
            long strat1 = l == 0 ? sufSum[l + k/2] : preSum[l - 1] + sufSum[l + k / 2];
            long strat2 = r == n - 1 ? win2 + preSum[l + k/2 - 1] : win2 + preSum[l + k/2 - 1] + sufSum[r + 1];

            System.out.println("s1: " + strat1 + " s2: " + strat2);


            ans = Math.max(ans, strat1);
            ans = Math.max(ans, strat2);

            if (r + 1 < n) {
                // update window 2
                win2 -= prices[l + k/2];
                win2 += prices[r + 1];
            }

            l++;
       }

       return ans;
    }
}