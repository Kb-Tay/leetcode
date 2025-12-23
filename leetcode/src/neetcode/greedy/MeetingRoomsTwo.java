package neetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomsTwo {

    public static void main(String[] args) {

        int[] test = new int[]{1, 2, 5, 6};
        int t = Arrays.binarySearch(test, 7);

    }

    public int minMeetingRooms(List<Interval> intervals) {
        // binary search??
        // if unable to put into the same meeting room, then create new slot -> find the slot with the closest ending time
        
        List<List<Interval>> rooms = new ArrayList<>();
        List<Integer> endTime = new ArrayList<>();

        for (Interval in : intervals) {
            int room = binSearch(endTime, in.end);
        }

        return 1;
    }

    public int roomSearch(List<List<Interval>> rooms, int i) {
        // rooms.get(i)
        

        return 1;
    }

    public int binSearch(List<Integer> endTime, int end) {
        int l = 0;
        int r = endTime.size();
        
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (endTime.get(mid) == end) {
                return mid;
            } else if (endTime.get(mid) < end) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (l == endTime.size()) {
            return endTime.size() - 1; 
        } else {
            return l;
        }
    }

    class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }; 
}