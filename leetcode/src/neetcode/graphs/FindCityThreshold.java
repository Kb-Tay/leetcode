package neetcode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class FindCityThreshold {
    public static void main(String[] args) {


    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // create adj list store 
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        
        for (int[] e : edges) {
            addNode(adjList, e[0], e[1], e[2]);
            addNode(adjList, e[1], e[0], e[2]);
        }

        int minDis = Integer.MAX_VALUE;
        int minNode = 0;

        for (int k : adjList.keySet()) {
            int dest = djikstra(adjList, k, distanceThreshold);

            if (dest < minDis) {
               minDis = dest;
               minNode = k; 
            }

            if (dest == minDis && k > minNode) {
                minNode = k;
            }
        }

        return minNode;
    }
   
    public void addNode(Map<Integer, List<int[]>> adjList, int node, int neigh, int dis) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<>());
        }

        adjList.get(node).add(new int[]{neigh, dis});
    }

    public int djikstra(Map<Integer, List<int[]>> adjList, int start, int threshold) {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        int dest = 0;

        for (int[] neigh : adjList.get(start)) {
            pq.add(neigh);
        }

        while (!pq.isEmpty()) {
            int[] n = pq.poll();
            int node = n[0];
            int dis = n[1];

            if (visited.contains(node)) continue;
            if (dis > threshold) break;

            dest++;

            for (int[] neigh : adjList.get(node)) {
                pq.add(new int[]{neigh[0], dis + neigh[1]});
            }
        }

        return dest - 1; // exclude the node itself
    }
}