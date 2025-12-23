package neetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    int cap;
    int size;
    Llist tail; 
    Map<Integer, Llist> map;
    Map<Integer, Llist> counterHead;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.size = 0;
        this.tail = new Llist();
        this.tail.prev = new Llist();
        this.tail.prev.counter = Integer.MAX_VALUE;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // update counter of key
        Llist n = map.get(key);
        removeCounter(n);
        removeLlist(n);

        n.counter++;

        if (n.counterHead.containsKey(n.counter)) {
            Llist dest = counterHead.get(n.counter);
            counterHead.put(n.counter, n);
            addBefore(n, dest);
        } else {
            counterHead.put(n.counter, n);
        }
    }
    
    public void put(int key, int value) {
        // check if key exist,
        // check the size >= capacity before insert? else eject the LFU
        if (map.containsKey(key)) {
            Llist n = map.get(key);
            removeCounter(n);
            removeLlist(n);
        }

        if (size >= capacity) {
            removeCounter(tail.prev);
            removeLlist(tail.prev);
        }

        Llist new_n = new Llist(key, value);

        if (counterHead.containsKey(new_n.counter)) {
            Llist dest = counterHead.get(n.counter);
            counterHead.put(n.counter, n);
            addBefore(n, dest);
        } else {
            addBefore(new_n, tail);
        }
    }

    public removeCounter(Llist n) {
        Llist tmp = counterHead.get(n.counter);

        if (tmp == n) {
            if (tmp.next.counter == n.counter) {
                counterHead.put(n.counter, tmp.next.counter);
            } else {
                counterHead.remove(n.counter);
            }
        }
    }

    public removeLlist(Llist n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.prev = null;
        n.next = null;
    }

    public addBefore(Llist l, Llist s) {
        s.prev.next = l;
        l.prev = s.prev;
        s.prev = l;
        l.next = s;
    }

    class Llist() {
        int key;
        int value;
        int counter;
        Llist next; //smaller
        Llist prev; //larger

        public Llist() {
            this.counter = 0;
        }

        public Llist(int k, int v) {
            this.key = k;
            this.value = v;
            this.counter = 0;
        }
    }
}
