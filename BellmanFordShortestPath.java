import java.util.*;

class BellmanFordAlgorithm {
    private int numNodes;
    private List<Edge> edges;

    public BellmanFordAlgorithm(int numNodes) {
        this.numNodes = numNodes;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public List<Integer> findShortestPath(int start, int end) {
        int[] distances = new int[numNodes];
        int[] prev = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        distances[start] = 0;

        // Relax edges repeatedly
        for (int i = 1; i < numNodes; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    prev[v] = u;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;

            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                throw new IllegalArgumentException("Graph contains a negative-weight cycle");
            }
        }

        // Build the shortest path
        List<Integer> path = new ArrayList<>();
        int currNode = end;
        while (currNode != -1) {
            path.add(currNode);
            currNode = prev[currNode];
        }
        Collections.reverse(path);
        return path;
    }

    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
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

    void LocationTable() {
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
                return "Buhahahahhaha";
        }
    }
}

public class BellmanFordShortestPath {
    public static void main(String[] args) {

        GetLandmarkName temp = new GetLandmarkName();
        InstructionsForUser LocationsAndNumbers = new InstructionsForUser();
        LocationsAndNumbers.LocationTable();

        // Create a weighted graph
        BellmanFordAlgorithm bellmanFord = new BellmanFordAlgorithm(36);
        bellmanFord.addEdge(1, 26, 4);
        bellmanFord.addEdge(26, 2, 1);
        bellmanFord.addEdge(2, 19, 26);
        bellmanFord.addEdge(19, 22, 6);
        bellmanFord.addEdge(22, 11, 11);
        bellmanFord.addEdge(11, 4, 6);
        bellmanFord.addEdge(4, 16, 16);
        bellmanFord.addEdge(11, 25, 1);
        bellmanFord.addEdge(25, 20, 20);
        bellmanFord.addEdge(20, 18, 12);
        bellmanFord.addEdge(20, 12, 13);
        bellmanFord.addEdge(12, 8, 16);
        bellmanFord.addEdge(25, 15, 14);
        bellmanFord.addEdge(15, 13, 8);
        bellmanFord.addEdge(15, 24, 14);
        bellmanFord.addEdge(24, 28, 8);
        bellmanFord.addEdge(28, 6, 1);
        bellmanFord.addEdge(6, 3, 0);
        bellmanFord.addEdge(28, 21, 2);
        bellmanFord.addEdge(21, 27, 12);
        bellmanFord.addEdge(28, 27, 13);
        bellmanFord.addEdge(22, 28, 9);
        bellmanFord.addEdge(13, 28, 22);
        bellmanFord.addEdge(27, 17, 2);
        bellmanFord.addEdge(26, 17, 12);
        bellmanFord.addEdge(13, 9, 4);
        bellmanFord.addEdge(17, 10, 19);
        bellmanFord.addEdge(27, 10, 6);
        bellmanFord.addEdge(17, 7, 31);
        bellmanFord.addEdge(10, 7, 6);
        bellmanFord.addEdge(7, 23, 4);
        bellmanFord.addEdge(10, 23, 5);
        bellmanFord.addEdge(23, 5, 11);
        bellmanFord.addEdge(28, 5, 12);
        bellmanFord.addEdge(13, 5, 13);
        bellmanFord.addEdge(5, 29, 10);
        bellmanFord.addEdge(5, 14, 7);

        // Get user input for start and end nodes
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number in the above table, corresponding to your STARTING location: ");
        int startNode = scanner.nextInt();

        while (startNode < 1 || startNode > 29) {
            System.out.print("The value corresponding to the starting location must be an integer between 1 and 29! Re-enter: ");
            startNode = scanner.nextInt();
        }

        System.out.print("Enter the number in the above table, corresponding to your ENDING location: ");
        int endNode = scanner.nextInt();
        scanner.close();

        while (endNode < 1 || endNode > 29) {
            System.out.print("The value corresponding to the ending location must be an integer between 1 and 29! Re-enter: ");
            endNode = scanner.nextInt();
        }

        // Find the shortest path
        List<Integer> shortestPath = bellmanFord.findShortestPath(startNode, endNode);

        // Store the numbers in an array called "CheckPoints"
        int[] checkPoints = new int[shortestPath.size()];
        for (int i = 0; i < shortestPath.size(); i++) {
            checkPoints[i] = shortestPath.get(i);
        }

        // Print the shortest path
        System.out.println("Shortest path from " + temp.getLandmarkName(startNode) + " to " + temp.getLandmarkName(endNode) + ":");

        System.out.print("Initial location -> ");
        for (int node : shortestPath) {
            System.out.print(temp.getLandmarkName(node) + " -> ");
            System.out.print("\n");
        }
        System.out.print("Final destination.");
        System.out.println("\b\b");
    }
}