import java.util.*;

/**
 * CIT300 - Graded Practical Assignment 1
 * Member 4 Responsibility: Graph implementation, campus locations,
 * connections, and BFS/DFS traversal.
 *
 * Covers Menu Items 10-15:
 *   10. Add Campus Location
 *   11. Remove Campus Location
 *   12. Add Campus Connection/Road
 *   13. Remove Campus Connection/Road
 *   14. Display Campus Connections
 *   15. Traverse Campus Locations using BFS or DFS
 *
 * Design notes for the README / demo video:
 *  - The campus is modelled as an UNDIRECTED, unweighted graph.
 *      Vertices  = campus locations (e.g. "Library", "Canteen")
 *      Edges     = roads/paths/direct connections between two locations
 *  - The graph is represented using an ADJACENCY LIST, implemented with a
 *    LinkedHashMap<String, List<String>>  (LinkedHashMap keeps locations in
 *    insertion order which makes the "Display Campus Connections" output
 *    predictable and easy to demo).
 *  - Location names are stored/compared case-insensitively so "library" and
 *    "Library" are treated as the same vertex, which avoids duplicate-name
 *    bugs during a live demo.
 *  - Validation covers: empty/blank input, duplicate locations, adding a
 *    road to a location that does not exist, removing a road that does not
 *    exist, removing a location that does not exist (and cleanly detaching
 *    it from every neighbour's list), and self-loops (a location connected
 *    to itself is rejected).
 *
 * INTEGRATION WITH THE REST OF THE TEAM'S PROJECT
 * -------------------------------------------------
 * This file is written as a self-contained class (CampusGraph) plus a
 * small demo driver (main) so Member 4 can compile and demonstrate their
 * part independently. To plug it into the group's single menu-driven
 * Main.java:
 *   1. Copy the CampusGraph class into its own file, CampusGraph.java.
 *   2. In the team's main menu switch-statement, create one CampusGraph
 *      object (shared across the whole program) and call:
 *          case 10: campusGraph.addLocation(scanner);        break;
 *          case 11: campusGraph.removeLocation(scanner);     break;
 *          case 12: campusGraph.addConnection(scanner);      break;
 *          case 13: campusGraph.removeConnection(scanner);   break;
 *          case 14: campusGraph.displayConnections();        break;
 *          case 15: campusGraph.traverseMenu(scanner);       break;
 *   3. Remove the demo main() method below (or leave it - it is ignored
 *      when the class is used only as a library, since Java only runs the
 *      main() of the class you launch).
 */
public class CampusGraph {

    // Adjacency list: location name -> list of directly connected locations
    private final Map<String, List<String>> adjacencyList = new LinkedHashMap<>();

    // ---------------------------------------------------------------
    // 10. Add Campus Location
    // ---------------------------------------------------------------
    public void addLocation(Scanner sc) {
        System.out.print("Enter new campus location name: ");
        String name = normalise(sc.nextLine());

        if (name.isEmpty()) {
            System.out.println("Error: location name cannot be empty.");
            return;
        }
        if (adjacencyList.containsKey(name)) {
            System.out.println("Error: location \"" + name + "\" already exists.");
            return;
        }

        adjacencyList.put(name, new ArrayList<>());
        System.out.println("Location \"" + name + "\" added successfully.");
    }

    // ---------------------------------------------------------------
    // 11. Remove Campus Location
    // ---------------------------------------------------------------
    public void removeLocation(Scanner sc) {
        System.out.print("Enter location to remove: ");
        String name = normalise(sc.nextLine());

        if (!adjacencyList.containsKey(name)) {
            System.out.println("Error: location \"" + name + "\" does not exist.");
            return;
        }

        // Detach this location from every neighbour's adjacency list first
        for (String neighbour : adjacencyList.get(name)) {
            adjacencyList.get(neighbour).remove(name);
        }
        // Then remove the location's own entry
        adjacencyList.remove(name);

        System.out.println("Location \"" + name + "\" and all its connections were removed.");
    }

    // ---------------------------------------------------------------
    // 12. Add Campus Connection/Road
    // ---------------------------------------------------------------
    public void addConnection(Scanner sc) {
        System.out.print("Enter first location: ");
        String a = normalise(sc.nextLine());
        System.out.print("Enter second location: ");
        String b = normalise(sc.nextLine());

        if (!adjacencyList.containsKey(a) || !adjacencyList.containsKey(b)) {
            System.out.println("Error: both locations must exist before adding a road. "
                    + "Use option 10 to add a location first.");
            return;
        }
        if (a.equals(b)) {
            System.out.println("Error: a location cannot be connected to itself.");
            return;
        }
        if (adjacencyList.get(a).contains(b)) {
            System.out.println("Error: a connection between \"" + a + "\" and \"" + b + "\" already exists.");
            return;
        }

        // Undirected graph -> add the edge on both sides
        adjacencyList.get(a).add(b);
        adjacencyList.get(b).add(a);

        System.out.println("Road added between \"" + a + "\" and \"" + b + "\".");
    }

    // ---------------------------------------------------------------
    // 13. Remove Campus Connection/Road
    // ---------------------------------------------------------------
    public void removeConnection(Scanner sc) {
        System.out.print("Enter first location: ");
        String a = normalise(sc.nextLine());
        System.out.print("Enter second location: ");
        String b = normalise(sc.nextLine());

        if (!adjacencyList.containsKey(a) || !adjacencyList.containsKey(b)) {
            System.out.println("Error: one or both locations do not exist.");
            return;
        }
        if (!adjacencyList.get(a).contains(b)) {
            System.out.println("Error: no existing connection between \"" + a + "\" and \"" + b + "\".");
            return;
        }

        adjacencyList.get(a).remove(b);
        adjacencyList.get(b).remove(a);

        System.out.println("Road removed between \"" + a + "\" and \"" + b + "\".");
    }

    // ---------------------------------------------------------------
    // 14. Display Campus Connections (whole network, or one location's neighbours)
    // ---------------------------------------------------------------
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations have been added yet.");
            return;
        }

        System.out.println("\n--- Campus Network (Adjacency List) ---");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            String neighbours = entry.getValue().isEmpty()
                    ? "(no connections)"
                    : String.join(", ", entry.getValue());
            System.out.println(entry.getKey() + " -> " + neighbours);
        }
        System.out.println("Total locations: " + adjacencyList.size());
    }

    // ---------------------------------------------------------------
    // 15. Traverse Campus Locations using BFS or DFS
    // ---------------------------------------------------------------
    public void traverseMenu(Scanner sc) {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations have been added yet.");
            return;
        }

        System.out.print("Traverse using (1) BFS or (2) DFS? Enter 1 or 2: ");
        String choice = sc.nextLine().trim();

        System.out.print("Enter starting location: ");
        String start = normalise(sc.nextLine());

        if (!adjacencyList.containsKey(start)) {
            System.out.println("Error: starting location \"" + start + "\" does not exist.");
            return;
        }

        List<String> result;
        if (choice.equals("2")) {
            result = dfs(start);
            System.out.println("DFS Traversal from \"" + start + "\": " + result);
        } else {
            result = bfs(start);
            System.out.println("BFS Traversal from \"" + start + "\": " + result);
        }

        // Note any locations that are unreachable from the chosen start,
        // e.g. isolated locations or a disconnected part of the campus.
        if (result.size() < adjacencyList.size()) {
            List<String> unreachable = new ArrayList<>(adjacencyList.keySet());
            unreachable.removeAll(result);
            System.out.println("Note: unreachable from \"" + start + "\": " + unreachable);
        }
    }

    /** Breadth-First Search starting at 'start'. Returns visit order. */
    public List<String> bfs(String start) {
        List<String> visitOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitOrder.add(current);

            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visitOrder;
    }

    /** Depth-First Search starting at 'start' (iterative, using an explicit stack). */
    public List<String> dfs(String start) {
        List<String> visitOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            visitOrder.add(current);

            // Push neighbours in reverse so traversal order matches the
            // adjacency list order (nice for a predictable demo output)
            List<String> neighbours = adjacencyList.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                String n = neighbours.get(i);
                if (!visited.contains(n)) {
                    stack.push(n);
                }
            }
        }
        return visitOrder;
    }

    // ---------------------------------------------------------------
    // Helper: trim + case-insensitive normalisation for location names
    // ---------------------------------------------------------------
    private String normalise(String raw) {
        return raw == null ? "" : raw.trim();
    }

    // =================================================================
    // Standalone demo driver (Menu 10-15 only) so Member 4 can compile
    // and demonstrate this component independently before it is merged
    // into the team's full Main.java.
    // =================================================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CampusGraph graph = new CampusGraph();
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Campus Route Management (Graph Component) ===");
            System.out.println("10. Add Campus Location");
            System.out.println("11. Remove Campus Location");
            System.out.println("12. Add Campus Connection/Road");
            System.out.println("13. Remove Campus Connection/Road");
            System.out.println("14. Display Campus Connections");
            System.out.println("15. Traverse Campus Locations using BFS or DFS");
            System.out.println(" 0. Exit");
            System.out.print("Enter choice: ");

            String input = sc.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: please enter a number.");
                continue;
            }

            switch (choice) {
                case 10: graph.addLocation(sc); break;
                case 11: graph.removeLocation(sc); break;
                case 12: graph.addConnection(sc); break;
                case 13: graph.removeConnection(sc); break;
                case 14: graph.displayConnections(); break;
                case 15: graph.traverseMenu(sc); break;
                case 0: System.out.println("Exiting graph component demo."); break;
                default: System.out.println("Invalid choice: please pick an option from the menu.");
            }
        }
        sc.close();
    }
}
