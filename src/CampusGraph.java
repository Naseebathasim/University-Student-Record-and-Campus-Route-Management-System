import java.util.*;

public class CampusGraph {

    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new HashMap<>();
    }

    // ---------- ADD LOCATION ----------
    public void addLocation(String location) {
        if (adjacencyList.containsKey(location)) {
            System.out.println("Location already exists: " + location);
            return;
        }
        adjacencyList.put(location, new ArrayList<>());
        System.out.println("Location added: " + location);
    }

    // ---------- REMOVE LOCATION ----------
    public void removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            System.out.println("Location not found: " + location);
            return;
        }
        adjacencyList.remove(location);
        for (List<String> neighbors : adjacencyList.values()) {
            neighbors.remove(location);
        }
        System.out.println("Location removed: " + location);
    }

    // ---------- ADD CONNECTION (EDGE) ----------
    public void addConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            System.out.println("Both locations must exist before connecting.");
            return;
        }
        if (adjacencyList.get(loc1).contains(loc2)) {
            System.out.println("Connection already exists between " + loc1 + " and " + loc2);
            return;
        }
        adjacencyList.get(loc1).add(loc2);
        adjacencyList.get(loc2).add(loc1);
        System.out.println("Connection added: " + loc1 + " <-> " + loc2);
    }

    // ---------- REMOVE CONNECTION (EDGE) ----------
    public void removeConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            System.out.println("Both locations must exist.");
            return;
        }
        adjacencyList.get(loc1).remove(loc2);
        adjacencyList.get(loc2).remove(loc1);
        System.out.println("Connection removed: " + loc1 + " <-> " + loc2);
    }

    // ---------- DISPLAY CAMPUS NETWORK ----------
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("=== Campus Network (Adjacency List) ===");
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " -> " + adjacencyList.get(location));
        }
    }

    // ---------- BFS TRAVERSAL ----------
    public void bfsTraversal(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS Traversal from " + start + ": ");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // ---------- DFS TRAVERSAL ----------
    public void dfsTraversal(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new HashSet<>();
        System.out.print("DFS Traversal from " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (String neighbor : adjacencyList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public boolean hasLocation(String location) {
        return adjacencyList.containsKey(location);
    }
}