import java.util.*;

class FloydWarshallAlgorithm {
    private int[][] graph;
    private int numNodes;

    FloydWarshallAlgorithm(int numNodes) {
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

    public List<Integer> findShortestPath(int start, int end) {
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
            return new ArrayList<>(); // No path exists between the given nodes
        }

        List<Integer> path = new ArrayList<>();
        path.add(start);
        buildPath(start, end, next, path);
        return path;
    }

    private void buildPath(int start, int end, int[][] next, List<Integer> path) {
        if (next[start][end] == -1) {
            path.add(end);
            return;
        }

        buildPath(start, next[start][end], next, path);
        buildPath(next[start][end], end, next, path);
    }

}

class InstructionsForUser {
    InstructionsForUser() {
        System.out.println("WELCOME TO ROUTE MAP NAVIGATION IN AMRITA VISHWA VIDYAAPEETHAM, COIMBATORE !");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void locationTable() {
        GetLandmarkName getLandmarkNames = new GetLandmarkName();
        Object[][] table = new Object[29][2];
        for (int i = 0; i < 29; i++) {
            table[i][0] = i + 1; // Number
            table[i][1] = getLandmarkNames.getLandmarkName(i + 1); // Landmark name
        }

        // Print the table
        System.out.println("Number\tLandmark");
        for (Object[] row : table) {
            System.out.println(row[0] + "\t" + row[1]);
        }
    }
}

class GetLandmarkName {
    public String getLandmarkName(int num) {
        switch (num) {
            case 1:
                return "College main gate";
            case 2:
                return "ATM 1 (near main gate)";
            case 3:
                return "ATM 2 (near Anokha junction)";
            case 4:
                return "ATM 3 (near AB2)";
            case 5:
                return "Main ground";
            case 6:
                return "Amentits complex";
            case 7:
                return "Main canteen";
            case 8:
                return "IT canteen";
            case 9:
                return "MBA canteen";
            case 10:
                return "Academic Block 1 (AB1)";
            case 11:
                return "Academic Block 2 (AB2)";
            case 12:
                return "Academic Block 3 (AB3)";
            case 13:
                return "Amrita School of Business (ASB)";
            case 14:
                return "YB Annexe (1st year boys hostel)";
            case 15:
                return "Kapila Bhavanam (4th year boys hostel)";
            case 16:
                return "Vasishta Bhavanam (2nd and 3rd year boys hostel)";
            case 17:
                return "Mythreyi Bhavanam (1st year girls hostel)";
            case 18:
                return "Aditi Bhavanam (2nd and 3rd year girls hostel)";
            case 19:
                return "Gargi Bhavanam (4th year girls hostel)";
            case 20:
                return "Swimming pool";
            case 21:
                return "Anokha hub";
            case 22:
                return "Staff quarters";
            case 23:
                return "Amriteshwari hall";
            case 24:
                return "Aashram";
            case 25:
                return "Central library";
            case 26:
                return "Amrita Post Office (APO)";
            case 27:
                return "Amazon delivery center (AB1 car parking)";
            case 28:
                return "Anokha cross road junction";
            case 29:
                return "D-Ground";
            default:
                return "Unknown";
        }
    }
}

public class FloydWarshallShortestPath {
    public static void main(String[] args) {

        GetLandmarkName temp = new GetLandmarkName();
        InstructionsForUser locationsAndNumbers = new InstructionsForUser();
        locationsAndNumbers.locationTable();

        // Create a weighted graph
        FloydWarshallAlgorithm floydWarshall = new FloydWarshallAlgorithm(36);
        floydWarshall.addEdge(1, 26, 4);
        floydWarshall.addEdge(26, 2, 1);
        floydWarshall.addEdge(2, 19, 26);
        floydWarshall.addEdge(19, 22, 6);
        floydWarshall.addEdge(22, 11, 11);
        floydWarshall.addEdge(11, 4, 6);
        floydWarshall.addEdge(4, 16, 16);
        floydWarshall.addEdge(11, 25, 1);
        floydWarshall.addEdge(25, 20, 20);
        floydWarshall.addEdge(20, 18, 12);
        floydWarshall.addEdge(20, 12, 13);
        floydWarshall.addEdge(12, 8, 16);
        floydWarshall.addEdge(25, 15, 14);
        floydWarshall.addEdge(15, 13, 8);
        floydWarshall.addEdge(15, 24, 14);
        floydWarshall.addEdge(24, 28, 8);
        floydWarshall.addEdge(28, 6, 1);
        floydWarshall.addEdge(6, 3, 0);
        floydWarshall.addEdge(28, 21, 2);
        floydWarshall.addEdge(21, 27, 12);
        floydWarshall.addEdge(28, 27, 13);
        floydWarshall.addEdge(22, 28, 9);
        floydWarshall.addEdge(13, 28, 22);
        floydWarshall.addEdge(27, 17, 2);
        floydWarshall.addEdge(26, 17, 12);
        floydWarshall.addEdge(13, 9, 4);
        floydWarshall.addEdge(17, 10, 19);
        floydWarshall.addEdge(27, 10, 6);
        floydWarshall.addEdge(17, 7, 31);
        floydWarshall.addEdge(10, 7, 6);
        floydWarshall.addEdge(7, 23, 4);
        floydWarshall.addEdge(10, 23, 5);
        floydWarshall.addEdge(23, 5, 11);
        floydWarshall.addEdge(28, 5, 12);
        floydWarshall.addEdge(13, 5, 13);
        floydWarshall.addEdge(5, 29, 10);
        floydWarshall.addEdge(5, 14, 7);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number in the above table, corresponding to your STARTING location: ");
        int startNode = scanner.nextInt();

        while (startNode < 1 || startNode > 29) {
            System.out.print(
                    "The value corresponding to the starting location must be an integer between 1 and 29! Re-enter: ");
            startNode = scanner.nextInt();
        }

        System.out.print("Enter the number in the above table, corresponding to your ENDING location: ");
        int endNode = scanner.nextInt();
        scanner.close();

        while (endNode < 1 || endNode > 29) {
            System.out.print(
                    "The value corresponding to the ending location must be an integer between 1 and 29! Re-enter: ");
            endNode = scanner.nextInt();
        }

        // Find the shortest path
        List<Integer> shortestPath = floydWarshall.findShortestPath(startNode, endNode);

        // Print the shortest path
        System.out.println(
                "Shortest path from " + temp.getLandmarkName(startNode) + " to " + temp.getLandmarkName(endNode) + ":");
        System.out.print("Initial location -> ");
        for (int node : shortestPath) {
            System.out.println(temp.getLandmarkName(node) + " -> ");
        }
        System.out.print("Final destination.");
        System.out.println("\b\b");
    }
}