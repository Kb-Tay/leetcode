package neetcode.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        // how to find height of tree
        // recurse and return depth from leaf nodes

        // graph can only have 2 min height trees at most??      
        // Topological sort until we have 2 / 1 node remaining

        List<List<Integer>> adjList = new ArrayList<>(n);
        int[] inDegrees = new int[n];
        LinkedList<Integer> deque = new LinkedList<>();
        int count = n;

        for (int i = 0; i < n; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int[] e : edges) {
          adjList.get(e[0]).add(e[1]);
          adjList.get(e[1]).add(e[0]);
          
          inDegrees[e[0]]++;
          inDegrees[e[1]]++;
        }

        for (int i = 0; i < n; i++) {
          if (inDegrees[i] == 1) deque.add(i);
        }

        while (!deque.isEmpty() && count > 2) {
          int node = deque.poll();
          count--;

          for (int neigh : adjList.get(node)) {
            inDegrees[neigh]--;

            if (inDegrees[neigh] == 1) deque.add(neigh);
          }
        }
    }
}