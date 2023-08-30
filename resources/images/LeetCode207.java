package algorithm.practice.leetcode;

import java.util.*;

/**
 * 207. Course Schedule: https://leetcode.com/problems/course-schedule/
 * <p>
 * tags: medium, graph, bfs, dfs
 *
 * @author TomAndersen
 */
public class LeetCode207 {
}

/**
 * DFS recursion
 * TC: O(m+n), SC: O(m+n)
 */
class LeetCode207_1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // exclude boundary situation
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // solve problem
        boolean isPossible = true;

        // build graph in adjacency list, and visit all edges in the graph by DFS recursion
        HashMap<Integer, List<Integer>> outEdges = new HashMap<>();
        // record the state of pointers, 0-not visited, 1-visiting, 2-visited
        int[] states = new int[numCourses];

        for (int[] outEdge : prerequisites) {
            List<Integer> outEdgeList = outEdges.getOrDefault(outEdge[0], new ArrayList<>());
            outEdgeList.add(outEdge[1]);
            outEdges.put(outEdge[0], outEdgeList);
        }

        for (int[] edge : prerequisites) {
            if (!dfsRecursion(outEdges, states, edge[0])) {
                isPossible = false;
                break;
            }
        }

        // return result
        return isPossible;
    }

    private boolean dfsRecursion(Map<Integer, List<Integer>> outEdges, int[] states, int x) {

        // if current point is visited or visiting
        if (states[x] == 2) {
            return true;
        }
        else if (states[x] == 1) {
            return false;
        }
        else {
            // visit current point and all subsequent points by dfs recursion
            states[x] = 1;

            List<Integer> subsequentPoints = outEdges.getOrDefault(x, Collections.emptyList());
            for (int point : subsequentPoints) {
                if (!dfsRecursion(outEdges, states, point)) {
                    return false;
                }
            }

            states[x] = 2;
            return true;
        }
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode207_1().canFinish(2, new int[][]{{1, 0}}) == true);
        System.out.println(new LeetCode207_1().canFinish(2, new int[][]{{1, 0}, {0, 1}}) == false);
        System.out.println(new LeetCode207_1().canFinish(3, new int[][]{{1, 0}, {1, 2}, {0, 1}}) == false);
    }
}

/**
 * Topological Sort: adjacency list(map.list) + queue
 * TC: O(m+n), SC: O(m+n)
 */
class LeetCode207_2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // exclude boundary situation
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // build a graph using adjacency list
        boolean res = true;
        int[] inDegrees = new int[numCourses];
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] edge : prerequisites) {
            inDegrees[edge[0]] += 1;
            List<Integer> outPoints = adjList.getOrDefault(edge[1], new ArrayList<>());
            outPoints.add(edge[0]);
            adjList.put(edge[1], outPoints);
        }

        // try to iterate all points by topological level order
        // initialize queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int visitCounter = 0;
        while (!queue.isEmpty()) {
            Integer headPoint = queue.poll();
            visitCounter += 1;

            // iterate all subsequent points of current point
            for (int nextPoint : adjList.getOrDefault(headPoint, Collections.emptyList())) {
                inDegrees[nextPoint] -= 1;
                if (inDegrees[nextPoint] == 0) {
                    queue.offer(nextPoint);
                }
            }
        }

        if (visitCounter != numCourses) {
            res = false;
        }

        // return
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode207_2().canFinish(2, new int[][]{{1, 0}}) == true);
        System.out.println(new LeetCode207_2().canFinish(2, new int[][]{{1, 0}, {0, 1}}) == false);
        System.out.println(new LeetCode207_2().canFinish(3, new int[][]{{1, 0}, {1, 2}, {0, 1}}) == false);
    }
}


/**
 * Topological Sort(BFS): adjacency list(list.list) + queue
 * TC: O(m+n), SC: O(m+n)
 */
class LeetCode207_3 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // exclude boundary situation
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // build graph and iterate by topological order
        boolean res = true;
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegrees = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            inDegrees[edge[0]] += 1;
            adjList.get(edge[1]).add(edge[0]);
        }

        // try to iterate all points by topological level order
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int visitCounter = 0;
        while (!queue.isEmpty()) {
            int head = queue.poll();
            visitCounter += 1;

            for (int next : adjList.get(head)) {
                inDegrees[next] -= 1;
                if (inDegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (visitCounter != numCourses) {
            res = false;
        }

        // return result
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode207_3().canFinish(2, new int[][]{{1, 0}}) == true);
        System.out.println(new LeetCode207_3().canFinish(2, new int[][]{{1, 0}, {0, 1}}) == false);
        System.out.println(new LeetCode207_3().canFinish(3, new int[][]{{1, 0}, {1, 2}, {0, 1}}) == false);
    }
}

/**
 * DFS recursion
 * TC: O(m+n), SC: O(m+n)
 */
class LeetCode207_4 {


}