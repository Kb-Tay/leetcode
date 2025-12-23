package daily;

import java.util.Arrays;

class TwoBestOverlap2054 {
    public static void main(String[] args) {
        TwoBestOverlap2054 t = new TwoBestOverlap2054();
        int[][] test = new int[][]{{1,3,2}, {4,5,2}, {2,4,3}};
        int[][] test2 = new int[][]{{1,3,2},{4,5,2},{1,5,5}};
        t.maxTwoEvents(test2); 
    }

    public int maxTwoEvents(int[][] events) { 
        // sort by start time => binary search start time > end time
        // store the max value of the event with their ending time 
        // sort by start time, then i need max value of all intervals that end earlier?
        
        // sort by end time  
        int[][] endTimeValue = new int[events.length][2];

        Arrays.sort(events, (a, b) -> {
            return a[1] - b[1];
        });

        for (int i = 0; i < events.length; i++) {
            endTimeValue[i][0] = events[i][1];
            endTimeValue[i][1] = i > 0 ? Math.max(events[i][2], endTimeValue[i - 1][1]) : events[i][2];
        }


        for (int[] e : endTimeValue) {
            System.out.println(Arrays.toString(e));
        }

        //sort by start time 
        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        int ans = 0;

        for (int i = 0; i < events.length; i++) {
            int ind = binSearch(endTimeValue, events[i][1]);
            
            if (ind == events.length || (ind == 0 && endTimeValue[0][0] <= events[i][1])) {
                ans = Math.max(ans, events[i][1]);
                continue;
            }       
            
            ans = Math.max(endTimeValue[ind][1] + events[i][2], ans);
       }

       return ans;
    }

    public int binSearch(int[][] endTimeValue, int target) {
        int l = 0;
        int r = endTimeValue.length; 

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (endTimeValue[l][0] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}