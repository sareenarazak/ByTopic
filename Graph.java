import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    public static void main(String[] args) {
        // No of vertices
        int v = 12;

        // Adjacency list for storing which vertices are connected
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        addEdge(adj, 0, 9);
        addEdge(adj, 0, 1);

        addEdge(adj, 9, 8);
        addEdge(adj, 8, 7);
        addEdge(adj, 1, 8);

        addEdge(adj, 7, 10);
        addEdge(adj, 7, 11);
        addEdge(adj, 7, 3);
        addEdge(adj, 3, 2);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 5);
        addEdge(adj, 6, 5);
        addEdge(adj, 7, 6);

        // DFS print nodes
        System.out.println(dfs(adj));
        System.out.println(bfs(adj));

    }

    private static void addEdge(List<List<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    private static List<Integer> bfs(List<List<Integer>> graph) {
        if(graph == null || graph.isEmpty()) return null;
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            result.add(current);
            for(int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
            }

        }
    }
        return result;
    }



    private static List<Integer> dfs(List<List<Integer>> graph) {
        if(graph == null || graph.isEmpty()) return null;
        List<Integer> result = new ArrayList<>();
        dfsHelper(graph, result, 0, new boolean[graph.size()]);
        return result;
    }

    private static void dfsHelper(List<List<Integer>> graph, List<Integer> nodes, int currentNode, boolean[] visited) {
        if(visited[currentNode]) return;
        nodes.add(currentNode);
        visited[currentNode] = true;

        for(int neighbor : graph.get(currentNode)) {
            dfsHelper(graph, nodes, neighbor, visited);
        }
    } 

}