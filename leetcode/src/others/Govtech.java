package others;
import java.util.Arrays;

public class Govtech {
    public int findMin(int[] workers) {

        Arrays.sort(workers);
        int n = workers.length;
        int[] even_pre = new int[n/2];
        int[] odd_pre = new int[n/2];
        int[] odd_suf = new int[n/2];

        // find the even prefix, suffix sum
        for (int i = 0; i < n-1; i+=2) {
            int ind = (i / 2);

            if (ind > 0) {
                even_pre[ind] = even_pre[ind - 1] + workers[i + 1] - workers[i];
            } else {
                even_pre[ind] = workers[i + 1] - workers[i];
            }
        }

        for (int i = 1; i < n; i += 2) {
            int pre_ind = (i - 1) / 2;
            
            if (pre_ind > 0) {
                odd_pre[pre_ind] = odd_pre[pre_ind - 1] + workers[i+1] - workers[i];
            } else {
                odd_pre[pre_ind] = workers[i+1] - workers[i]; 
            }
        }

        for (int i = n - 1; i >= 1; i -= 2) {
            int suf_ind = (i - 1) / 2;

            if (i < n - 1) {
                odd_suf[suf_ind] = odd_suf[suf_ind+1] + workers[i] - workers[i - 1];
            } else {
                odd_suf[suf_ind] = workers[i] - workers[i - 1];
            }
        }

        int min_cost = Integer.MAX_VALUE;

        // try removing even pos
        for (int i = 0; i < n ; i+=2) {
            int pair_ind = i / 2;

            if (i == 0) {
                min_cost = Math.min(min_cost, odd_suf[pair_ind]);
                continue;
            }

            if (i == n-1) {
                min_cost = Math.min(min_cost, even_pre[pair_ind - 1]);
                continue;
            }
            
            min_cost = Math.min(min_cost, even_pre[pair_ind - 1] + odd_suf[pair_ind]);
        }

        // try removing odd pos
        for (int i = 0; i < n / 2; i++) {
            int odd_i = (i * 2) + 1;
            System.out.print("odd_i: " + odd_i);
            System.out.println(min_cost);

            if (odd_i == 1) {
                min_cost = Math.min(min_cost, workers[odd_i + 1] - workers[odd_i - 1] + odd_suf[i + 1]);
                continue;
            }

            if (odd_i == n - 2) { 
                min_cost = Math.min(min_cost, even_pre[i - 1] + workers[odd_i + 1] - workers[odd_i - 1]);
                continue;
            }

            min_cost = Math.min(min_cost, even_pre[i - 1] + workers[odd_i+ 1] - workers[odd_i - 1] + odd_suf[i + 1]);
        }

        System.out.println(Arrays.toString(even_pre));
        System.out.println(Arrays.toString(odd_pre));
        System.out.println(Arrays.toString(odd_suf));



        // find the odd suffix sum
        return min_cost;
    }
}