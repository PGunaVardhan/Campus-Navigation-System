import java.util.*;

class DijkstraAlgorithm {
    private int numNodes;
    private Map<Integer, List<Edge>> graph;

    DijkstraAlgorithm(int numNodes) {
        this.numNodes = numNodes;
        this.graph = new HashMap<>();
        for (int i = 0; i < numNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    public List<Integer> findShortestPath(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        int[] distances = new int[numNodes];
        int[] prev = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        distances[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.node == end) {
                break; // Found the shortest path to the destination
            }

            if (curr.distance > distances[curr.node]) {
                continue; // Skip if a shorter path to this node has already been found
            }

            for (Edge edge : graph.get(curr.node)) {
                int newDistance = distances[curr.node] + edge.weight;
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance;
                    prev[edge.destination] = curr.node;
                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int currNode = end;
        while (currNode != -1) {
            path.add(currNode);
            currNode = prev[currNode];
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}

class InstructionsForUser {

    int NoOFLocations = 29;

    InstructionsForUser() {

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.println("WELCOME TO ROUTE MAP NAVIGATION IN AMRITA VISHWA VIDYAAPEETHAM, COIMBATORE !");
        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void LocationTable() {
        GetLandmarkName getLandmarkNames = new GetLandmarkName();
        Object[][] table = new Object[NoOFLocations][2];
        for (int i = 0; i < 29; i++) {
            table[i][0] = i + 1;
            table[i][1] = getLandmarkNames.getLandmarkName(i + 1);
        }

        // Print the table
        System.out.println("Number\tLandmark");
        for (Object[] row : table) {
            System.out.println(row[0] + "\t" + row[1]);
        }
    }
}

class GetLandmarkName extends InstructionsForUser {
    public String getLandmarkName(int num) {
        if (NoOFLocations == 29) {
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
                    return "This won't be used anyways :)";
            }
        } else {
            return "Something is wrong ! Number of locations does not match number of variables.";
        }
    }
}

public class DijkstraShortestPath {
    public static void main(String[] args) {

        GetLandmarkName temp = new GetLandmarkName();
        InstructionsForUser LocationsAndNumbers = new InstructionsForUser();
        LocationsAndNumbers.LocationTable();

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(36);
        dijkstra.addEdge(1, 26, 4);
        dijkstra.addEdge(26, 2, 1);
        dijkstra.addEdge(2, 19, 26);
        dijkstra.addEdge(19, 22, 6);
        dijkstra.addEdge(22, 11, 11);
        dijkstra.addEdge(11, 4, 6);
        dijkstra.addEdge(4, 16, 16);
        dijkstra.addEdge(11, 25, 1);
        dijkstra.addEdge(25, 20, 20);
        dijkstra.addEdge(20, 18, 12);
        dijkstra.addEdge(20, 12, 13);
        dijkstra.addEdge(12, 8, 16);
        dijkstra.addEdge(25, 15, 14);
        dijkstra.addEdge(15, 13, 8);
        dijkstra.addEdge(15, 24, 14);
        dijkstra.addEdge(24, 28, 8);
        dijkstra.addEdge(28, 6, 1);
        dijkstra.addEdge(6, 3, 0);
        dijkstra.addEdge(28, 21, 2);
        dijkstra.addEdge(21, 27, 12);
        dijkstra.addEdge(28, 27, 13);
        dijkstra.addEdge(22, 28, 9);
        dijkstra.addEdge(13, 28, 22);
        dijkstra.addEdge(27, 17, 2);
        dijkstra.addEdge(26, 17, 12);
        dijkstra.addEdge(13, 9, 4);
        dijkstra.addEdge(17, 10, 19);
        dijkstra.addEdge(27, 10, 6);
        dijkstra.addEdge(17, 7, 31);
        dijkstra.addEdge(10, 7, 6);
        dijkstra.addEdge(7, 23, 4);
        dijkstra.addEdge(10, 23, 5);
        dijkstra.addEdge(23, 5, 11);
        dijkstra.addEdge(28, 5, 12);
        dijkstra.addEdge(13, 5, 13);
        dijkstra.addEdge(5, 29, 10);
        dijkstra.addEdge(5, 14, 7);

        Scanner scanner = new Scanner(System.in);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.print("Enter the number in the above table, corresponding to your STARTING location: ");
        int startNode = scanner.nextInt();

        while (startNode < 1 || startNode > 29) {
            System.out.print(
                    "The value corresponding to the starting location must be an integer between 1 and 29! Re-enter: ");
            startNode = scanner.nextInt();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the number in the above table, corresponding to your ENDING location: ");
        int endNode = scanner.nextInt();
        scanner.close();

        while (endNode < 1 || endNode > 29) {
            System.out.print(
                    "The value corresponding to the ending location must be an integer between 1 and 29! Re-enter: ");
            endNode = scanner.nextInt();
        }

        List<Integer> shortestPath = dijkstra.findShortestPath(startNode, endNode);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("");

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.println(
                "Navigation from " + temp.getLandmarkName(startNode) + " to " + temp.getLandmarkName(endNode) + ":");
        System.out.println(
                "--------------------------------------------------------------------------------------------------");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("You are at -> " + temp.getLandmarkName(startNode));
        for (int node : shortestPath) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("From there, go to " + temp.getLandmarkName(node) + ".");
        }
        System.out.print("That is your Final destination.");
    }
}