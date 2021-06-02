import java.util.*;

public class Graph {
    private HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    private int[] locations;
    private int[] visitedNodes;

    public Graph(int nodes) {
        for (int i = 0; i < nodes; i++) {
            map.put(i, new ArrayList<>());
        }
        locations = new int[nodes];
        visitedNodes = new int[nodes];
    }

    public void addEdge(int node, int neighbour) {
        map.get(node).add(neighbour);
        map.get(neighbour).add(node);
    }

    public void addLocations(int node, int items) {
        this.locations[node] = items;
    }

    public int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        if (visitedNodes[start] == 1) {
            return 0;
        }
        queue.add(start);
        int count = locations[start];
        visitedNodes[start] = 1;

        while (!queue.isEmpty()) {
            int place = queue.poll();
            for (int node : map.get(place)) {
                if (visitedNodes[node] != 1) {
                    visitedNodes[node] = 1;
                    queue.add(node);
                    count += locations[node];
                }
            }
        }
        return count;
    }

    public int dfs(int start) {
        if (visitedNodes[start] == 1) {
            return 0;
        }
        visitedNodes[start] = 1;
        int totalPieces = locations[start];
        for (int node : map.get(start)) {
            if (visitedNodes[node] != 1) {
                totalPieces += dfs(node);
            }
        }
        return totalPieces;
    }

    public void printAdjList() {
        for (int i : map.keySet()) {
            System.out.print("Node " + i + ": ");
            for (int j : map.get(i)) {
                System.out.print("-> " + j);
            }
            System.out.println();
        }
    }

    public void printLocations() {
        for (int i = 0; i < locations.length; i++) {
            System.out.println("City " + i + " has " + locations[i] + " pieces");
        }
    }
}
