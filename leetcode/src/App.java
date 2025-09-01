import java.util.Arrays;
import java.util.Scanner;

import key_algos.Quickselect;

public class App {
    public static void main(String[] args) throws Exception {
        // Solution soln = new Solution();
        // System.out.println(soln.subarraySum(new int[]{1, 1, 1}, 2));
        
        Quickselect m = new Quickselect();
        int[] test = new int[]{5, 4, 6, 8, 9, 10, 11, 12};
        int[] test1 = new int[]{3, 7, 8, 5, 2, 1, 9, 5, 4};
        
        
        // Solution soln = new Solution();
        // int[] test = new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        // System.out.println(soln.longestOnes(test, 2));

        // Govtech soln = new Govtech();

        // int[] test1 = new int[]{3, 4, 5, 8, 13, 14, 16, 20, 21}; // 3, 4, 8, 10, 14
        // // int[] test2 = new int[]{1, 3, 7, 9, 10, 15, 20};
        // int ans = soln.findMin(test1);
        // System.out.println("ans: " + ans);

        // 1 2 3 4 5 6 7 8 9
        // suppose remove even pos - take even pre, odd suff
        // suppose remove odd - even prem odd suff

        // 0 1 2 3 4 5 6
        // 1 3 1 1
    }
}
