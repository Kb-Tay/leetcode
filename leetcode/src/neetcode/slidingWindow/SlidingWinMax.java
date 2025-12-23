package neetcode.slidingWindow;

import java.util.LinkedList;
import java.util.List;

public class SlidingWinMax {
  public int[] maxSlidingWindow(int[] nums, int k) {
    // Notice:
    // encounter a new largest number, only need to keep track of the largest 
    //      in the next k elements

    // store largest number + position in two separate deques
    // window slide

    LinkedList<Integer> pos_deque = new LinkedList<>();
    LinkedList<Integer> max_deque = new LinkedList<>();
    int[] ans = new int[nums.length - k + 1];

    for (int l = 0; l < k; l++) {
      if (max_deque.isEmpty()) {
        pos_deque.add(nums[l]);
        max_deque.add(l);
        continue;
      }

      if (nums[l] > max_deque.peekFirst()) {
        max_deque = new LinkedList<>();
        pos_deque = new LinkedList<>();

        max_deque.add(nums[l]);
        pos_deque.add(l);
      }
    }

    ans[0] = max_deque.peekFirst();

    for (int l = k; l + k - 1 < nums.length; l++) {
      if (pos_deque.peekFirst() < l) {
        max_deque.pollFirst();
        pos_deque.pollFirst();
      }

      int r = l + k - 1;

      // new largest, dump entire deque
      if (nums[r] >= max_deque.peekFirst()) {
        max_deque = new LinkedList<>();
        pos_deque = new LinkedList<>();
      } 

      max_deque.add(nums[r]);
      pos_deque.add(r);

      ans[l - k + 1] = max_deque.peekFirst();
    }

    return ans;
  }
}
