import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {

    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new HashMap<>();
    }

    // Add campus location
    public boolean addLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            return false;
        }

        location = location.trim();

        if (adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.put(location, new ArrayList<>());

        return true;
    }

    // Remove campus location
    public boolean removeLocation(String location) {

        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        // Remove the location itself
        adjacencyList.remove(location);

        // Remove the location from other neighbour lists
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        return true;
    }

    // Add connection
    public boolean addConnection(
            String location1,
            String location2) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {

            return false;
        }

        if (location1.equalsIgnoreCase(location2)) {
            return false;
        }

        if (hasConnection(location1, location2)) {
            return false;
        }

        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);

        return true;
    }

    // Remove connection
    public boolean removeConnection(
            String location1,
            String location2) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {

            return false;
        }

        boolean removed1 =
                adjacencyList.get(location1).remove(location2);

        boolean removed2 =
                adjacencyList.get(location2).remove(location1);

        return removed1 && removed2;
    }

    // Check connection
    public boolean hasConnection(
            String location1,
            String location2) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {

            return false;
        }

        return adjacencyList.get(location1)
                .contains(location2);
    }

    // Display graph
    public void displayConnections() {

        if (adjacencyList.isEmpty()) {
            System.out.println("\nNo campus locations found.");
            return;
        }

        System.out.println("\n========== CAMPUS NETWORK ==========");

        for (String location : adjacencyList.keySet()) {

            System.out.print(location + " -> ");

            List<String> neighbours =
                    adjacencyList.get(location);

            if (neighbours.isEmpty()) {

                System.out.println("No connections");

            } else {

                for (int i = 0; i < neighbours.size(); i++) {

                    System.out.print(neighbours.get(i));

                    if (i < neighbours.size() - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println();
            }
        }

        System.out.println("====================================");
    }

    // Display neighbours
    public void displayNeighbours(String location) {

        if (!adjacencyList.containsKey(location)) {
            System.out.println("Location not found.");
            return;
        }

        List<String> neighbours =
                adjacencyList.get(location);

        System.out.println("\nConnections from " + location + ":");

        if (neighbours.isEmpty()) {
            System.out.println("No connected locations.");
            return;
        }

        for (String neighbour : neighbours) {
            System.out.println("- " + neighbour);
        }
    }

    // BFS
    public void bfs(String startLocation) {

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        queue.offer(startLocation);

        visited.add(startLocation);

        System.out.println("\n========== BFS TRAVERSAL ==========");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current);

            List<String> neighbours =
                    adjacencyList.get(current);

            for (String neighbour : neighbours) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);

                    queue.offer(neighbour);
                }
            }

            if (!queue.isEmpty()) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("===================================");
    }

    // DFS
    public void dfs(String startLocation) {

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new HashSet<>();

        System.out.println("\n========== DFS TRAVERSAL ==========");

        dfsRecursive(startLocation, visited);

        System.out.println();
        System.out.println("===================================");
    }

    private void dfsRecursive(
            String location,
            Set<String> visited) {

        visited.add(location);

        System.out.print(location);

        List<String> neighbours =
                adjacencyList.get(location);

        for (String neighbour : neighbours) {

            if (!visited.contains(neighbour)) {

                System.out.print(" -> ");

                dfsRecursive(neighbour, visited);
            }
        }
    }

    // Check whether graph has location
    public boolean hasLocation(String location) {
        return adjacencyList.containsKey(location);
    }
}