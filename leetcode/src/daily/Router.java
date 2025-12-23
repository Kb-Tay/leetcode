package daily;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Router {
    // two ptrs - if h == t + 1, then full => bin search in rotate sorted 
    HashSet<String> pktSet = new HashSet<>();
    int capacity;    

    public Router(int memoryLimit) {
      mem = new int[memoryLimit][3];
      capacity = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
      // hashmap - key can be string of source, des, time - dont add dupl
      String key = String.format("%d%d%d", source, destination, timestamp);
      if (pktSet.contains(key)) {
        return false;
      }

      if (size == capacity) {
        int[] pkt = mem[tail];
        String prevKey = String.format("%d%d%d", pkt[0], pkt[1], pkt[2]);
        pktSet.remove(prevKey);
        
        mem[tail][0] = source;
        mem[tail][1] = destination;
        mem[tail][2] = timestamp;
        
        head = (head + 1) % capacity;
        tail = (tail + 1) % capacity;
      }

      if (size < capacity) {
        mem[tail][0] = source;
        mem[tail][1] = destination;
        mem[tail][2] = timestamp;
        
        tail = (tail + 1) % capacity;
        size++;
      }

      pktSet.add(key);

      return true;
    }
    
    public int[] forwardPacket() {
        if (size == 0) {
            return new int[0];
        }
      int[] pkt = mem[head];
      head = (head + 1) % capacity;
      size--;

      return pkt;
    }
    
    public int getCount(int destination, int startTime, int endTime) {

        if (size == 0) {
            return 0;
        }

        int count = 0;
        int temp = size;
        int i = head;


        while (temp >= 0) {
            if (mem[i][1] == destination && mem[i][2] >= startTime && mem[i][2] <= endTime) count++;
            i = (i + 1) % capacity;
            temp--;
        }

        return count;
    }
}

class Node {
  Node next;
  Node prev;
  int destination;
  int timestamp;
}

// class Router {
//     // two ptrs - if h == t + 1, then full => bin search in rotate sorted 
//     HashSet<String> pktSet = new HashSet<>();
//     int[][] mem; 
//     int tail = 0;   
//     int size = 0; 
//     int capacity;    

//     public Router(int memoryLimit) {
//       mem = new int[memoryLimit][3];
//       capacity = memoryLimit;
//     }
    
//     public boolean addPacket(int source, int destination, int timestamp) {
//       // hashmap - key can be string of source, des, time - dont add dupl
//       String key = String.format("%d%d%d", source, destination, timestamp);
//       if (pktSet.contains(key)) {
//         return false;
//       }

//       if (size == capacity) {
//         int[] pkt = mem[tail];
//         String prevKey = String.format("%d%d%d", pkt[0], pkt[1], pkt[2]);
//         pktSet.remove(prevKey);
        
//         mem[tail][0] = source;
//         mem[tail][1] = destination;
//         mem[tail][2] = timestamp;
        
//         head = (head + 1) % capacity;
//         tail = (tail + 1) % capacity;
//       }

//       if (size < capacity) {
//         mem[tail][0] = source;
//         mem[tail][1] = destination;
//         mem[tail][2] = timestamp;
        
//         tail = (tail + 1) % capacity;
//         size++;
//       }

//       pktSet.add(key);
//       return true;
//     }
    
//     public int[] forwardPacket() {
//       int[] pkt = mem[head];
//       head = (head + 1) % capacity;
//       size--;

//       return pkt;
//     }
    
//     public int getCount(int destination, int startTime, int endTime) {
//         // bin search start time, end 
//         // array with two pointers as queue
//         int l = 0; 
//         int r = capacity; // outside valid range

//         while (l < r) {
//           int mid = l + (r - l) / 2;
          
//           if (mem[mid][3] < startTime && mem[mid][capacity - 1] < startTime) {
//             r = mid;
//             continue;
//           }

//           if (mem[mid][3] >= startTime) {
//             r = mid;
//             continue;
//           }

//           l = mid + 1; 
//         }

//         // target == l
//         // for target valid, either target > head , or target < head && < tail
//         while (!isWithinQueue(l) && mem[l][2] == startTime)  {
//           l = (l + 1) % capacity;
//         }

//         int count = 0;
//         while (isWithinQueue(l) && mem[l][2] == startTime) {
//           count++;
//           l = (l + 1) % capacity;
//         }

//         return count;
//     }

//     public boolean isWithinQueue(int pos) {
//       return pos > head || (pos < head && pos < tail);
//     }
// }
