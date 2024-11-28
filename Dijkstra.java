import java.util.*;

class Node {
    int node, dis;
    public Node(int node, int dis) {
        this.node = node;
        this.dis = dis;
    }
}

public final class Dijkstra {
    public static int[] dij(List<List<Node>> adj, int n) {
        int[] d = new int[n];
        boolean[] vis = new boolean[n];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.dis - b.dis));

        pq.add(new Node(0, 0));
        vis[0] = true;

        while (!pq.isEmpty()) {
            var cur = pq.poll();

            for (var nb : adj.get(cur.node)) {
                if (!vis[nb.node] && d[nb.node] < nb.dis + cur.dis) {
                    d[nb.node] = nb.dis + cur.dis;
                    vis[nb.node] = true;
                    pq.add(nb);
                }
            }
        }

        return d;
    }

    public static void main(String[] args) {
        int[][] edges = new int[5][3];
    }
}