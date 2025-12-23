package neetcode.binarySearch;

public class Koko {
    public int minEatingSpeed(int[] piles, int h) {
        
        int min_range = 1;
        int max_range = piles[0];
        
        for (int i = 1; i < piles.length; i++) {
            max_range = Math.max(piles[i], max_range);
        }
        
        return binSearch(min_range, max_range, piles, h);
    }

    public int binSearch(int l, int r, int[] piles, int h) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int hours = hoursTaken(piles, mid);
            
            if (hours <= h) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l - 1; 
    }

    public int hoursTaken(int[] piles, int k) {
        int hours = 0; 
        for (int i = 0; i < piles.length; i++) {
            hours += (int) Math.ceil(piles[i] / k); 
        }

        return hours;
    }
}
