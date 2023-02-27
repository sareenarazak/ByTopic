import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    public static void main(String[] args) {
        // Adjacency list for storing which vertices are connected
        List<List<Integer>> unDiretedGraph = new ArrayList<>();
        createUndirectedGraph(unDiretedGraph, 12);

        // DFS print nodes
        System.out.println(dfs(unDiretedGraph));
        System.out.println(bfs(unDiretedGraph));
        System.out.println(shortestPath(unDiretedGraph, 0, 2)); //[0, 9, 8, 7, 3, 2]
        System.out.println(shortestPath(unDiretedGraph, 0, 1)); // [0,2]

        List<List<Integer>> directedGraph = new ArrayList<>();
        createDirectedGraph(directedGraph, 13);
        System.out.println(topoSortDFS(directedGraph));
        System.out.println(topoSortBFS(directedGraph));

    }


    private static void createUndirectedGraph(List<List<Integer>> adj, int n) {
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

           // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        addUndirectedEdge(adj, 0, 9);
        addUndirectedEdge(adj, 0, 1);

        addUndirectedEdge(adj, 9, 8);
        addUndirectedEdge(adj, 8, 7);
        addUndirectedEdge(adj, 1, 8);

        addUndirectedEdge(adj, 7, 10);
        addUndirectedEdge(adj, 7, 11);
        addUndirectedEdge(adj, 7, 3);
        addUndirectedEdge(adj, 3, 2);
        addUndirectedEdge(adj, 3, 4);
        addUndirectedEdge(adj, 3, 5);
        addUndirectedEdge(adj, 6, 5);
        addUndirectedEdge(adj, 7, 6);
    }

    private static void addUndirectedEdge(List<List<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    private static void createDirectedGraph(List<List<Integer>> adj, int n) {
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

           // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        addDirectedEdge(adj, 0, 3);
        addDirectedEdge(adj, 1, 3);

        addDirectedEdge(adj, 2, 1);
        addDirectedEdge(adj, 2, 0);

        addDirectedEdge(adj, 3, 6);
        addDirectedEdge(adj, 3, 7);

        addDirectedEdge(adj, 4, 0);
        addDirectedEdge(adj, 4, 3);
        addDirectedEdge(adj, 4, 5);

        addDirectedEdge(adj, 5, 9);
        addDirectedEdge(adj, 5, 10);

        addDirectedEdge(adj, 6, 8);

        addDirectedEdge(adj, 7, 8);
        addDirectedEdge(adj, 7, 9);

        addDirectedEdge(adj, 8, 11);

        addDirectedEdge(adj, 9, 11);
        addDirectedEdge(adj, 9, 12);

        addDirectedEdge(adj, 10, 9);

    }

    private static void addDirectedEdge(List<List<Integer>> adj, int s, int d) {
        adj.get(s).add(d);
    }

    private static List<Integer> topoSortBFS(List<List<Integer>> graph) {
        List<Integer> result = new LinkedList<>();
        int n = graph.size();
        int[] inDegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            for(int neighbor : graph.get(i)) {
                inDegree[neighbor]++;
            }
        }

        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 0) q.offer(i);

        }

        while (!q.isEmpty()) {
            int current = q.poll();
            result.add(current);
            for(int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0) q.offer(neighbor);
            }
        }
        return result;
    }

    private static List<Integer> topoSortDFS(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        List<Integer> result = new LinkedList<>();
        for(int i = 0 ; i < n; i++) {
            if(!visited[i]) {

                topoDFSHelper(graph ,i, result, visited);
            }
        }
        return result;
    }

    private static void topoDFSHelper(List<List<Integer>> graph, int node, List<Integer> result, boolean[] visited) {
        if(visited[node]) return;

        visited[node] = true;
        for(int neighbor : graph.get(node)) {
            topoDFSHelper(graph, neighbor, result, visited);
        }
        result.add(0, node);
    }

    private static List<Integer> shortestPath(List<List<Integer>> graph, int source, int dest) {
        if(graph == null || graph.isEmpty()) return null;

        List<Integer> path = new LinkedList<>();
        Integer[] parents = bfsWithPath(graph, source);
        
        int end = dest;

        path.add(end);
        while (parents[end] != null) {
            path.add(0, parents[end]);
            end = parents[end];
        }
        if(path.get(0) != source) return null;
        return path;
    }

    private static Integer[] bfsWithPath(List<List<Integer>> graph, int s) {
       Integer[] parent =  new Integer[graph.size()];
       boolean[] visited = new boolean[graph.size()];

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            int current = q.poll();
            for(int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                    parent[neighbor] = current;
            }
        }
    }
    return parent;
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