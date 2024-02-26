import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordAlgorithm {
    private List<Edge> edges;
    private int numNodes;

    public BellmanFordAlgorithm(int numNodes) {
        this.numNodes = numNodes;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public List<Integer> findShortestPath(int start, int end) {
        int[] distance = new int[numNodes];
        int[] prev = new int[numNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        distance[start] = 0;

        for (int i = 0; i < numNodes - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    prev[v] = u;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                throw new RuntimeException("Negative cycle found. The graph contains negative cycles.");
            }
        }

        List<Integer> path = new ArrayList<>();
        int currNode = end;
        while (currNode != -1) {
            path.add(currNode);
            currNode = prev[currNode];
        }
        if (distance[end] == Integer.MAX_VALUE) {
            path.clear(); // No path exists between the given nodes
        }
        return path;
    }

    private static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        // Create a graph
        BellmanFordAlgorithm bellmanFord = new BellmanFordAlgorithm(4);
        bellmanFord.addEdge(0, 1, 3);
        bellmanFord.addEdge(0, 2, 6);
        bellmanFord.addEdge(1, 2, 2);
        bellmanFord.addEdge(1, 3, 1);
        bellmanFord.addEdge(2, 3, 4);

        // Get user input for start and end nodes
        int startNode = 0;
        int endNode = 3;

        // Find the shortest path
        List<Integer> shortestPath = bellmanFord.findShortestPath(startNode, endNode);

        // Print the shortest path
        if (shortestPath.isEmpty()) {
            System.out.println("No path exists between the given nodes.");
        } else {
            System.out.print("Shortest path from " + startNode + " to " + endNode + ": ");
            for (int node : shortestPath) {
                System.out.print(node + " -> ");
            }
            System.out.println("\b\b");
        }
    }
}
