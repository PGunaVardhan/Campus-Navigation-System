import java.util.Arrays;

public class FloydWarshallAlgorithm {
    private int[][] graph;
    private int numNodes;

    public FloydWarshallAlgorithm(int numNodes) {
        this.numNodes = numNodes;
        graph = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }
    }

    public void addEdge(int source, int destination, int weight) {
        graph[source][destination] = weight;
    }

    public void findShortestPath(int start, int end) {
        int[][] dist = new int[numNodes][numNodes];
        int[][] next = new int[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, numNodes);
            Arrays.fill(next[i], -1);
        }

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = k;
                    }
                }
            }
        }

        if (dist[start][end] == Integer.MAX_VALUE) {
            System.out.println("No path exists between the given nodes.");
            return;
        }

        System.out.println("Shortest path from " + start + " to " + end + ": " + start + " -> ");
        printPath(start, end, next);
        System.out.println("");
        System.out.println("Distance: " + dist[start][end]);
    }

    private void printPath(int start, int end, int[][] next) {
        if (next[start][end] == -1) {
            System.out.print(end);
            return;
        }

        printPath(start, next[start][end], next);
        System.out.print(" -> " + end);
    }

    public static void main(String[] args) {
        // Create a graph
        FloydWarshallAlgorithm floydWarshall = new FloydWarshallAlgorithm(4);
        floydWarshall.addEdge(0, 1, 3);
        floydWarshall.addEdge(0, 2, 6);
        floydWarshall.addEdge(1, 2, 2);
        floydWarshall.addEdge(1, 3, 1);
        floydWarshall.addEdge(2, 3, 4);

        // Get user input for start and end nodes
        int startNode = 0;
        int endNode = 3;

        // Find the shortest path
        floydWarshall.findShortestPath(startNode, endNode);
    }
}
