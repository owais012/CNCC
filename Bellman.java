import java.util.Arrays;

public class Bellman {

    // Function to implement the Bellman-Ford algorithm
    public static int[] bell(int[][] edges, int n, int src) {
        // Initialize distance array
        int[] distance = new int[n];
        Arrays.fill(distance, (int)1e9);
        distance[src] = 0; // Distance to source is 0

        // Traverse all edges (n-1) times
        for (int i = 0; i < n - 1; i++) {
            for (var edge : edges) {
                int u = edge[0], v = edge[1], weight = edge[2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight; // Relax the edge
                }
            }
        }

        // Check for negative weight cycles
        for (var edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return new int[] {-1};
            }
        }

        return distance; // Return shortest distances
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 5},
            {1, 2, -3},
            {2, 3, 2},
            {1, 3, 6}
        };
        int n = 4; // Number of vertices
        int src = 0; // Source vertex

        try {
            int[] distances = bell(edges, n, src);
            for (int i = 0; i < n; i++) {
                System.out.println("Distance from " + src + " to " + i + " is " + distances[i]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


