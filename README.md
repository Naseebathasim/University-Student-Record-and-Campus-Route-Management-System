### Group Member Details

| Name | Student ID | Assigned Responsibility |
|---|---|---|
| MMF.Mifra | 23DA2-0833 | Graph implementation, campus locations, connections, and BFS/DFS traversal |

### Individual Contribution — MMF.Mifra

I implemented the graph component of the University Student Record and Campus
Route Management System (Menu options 10–15), covering:

- Modelling campus locations as vertices and roads/paths as edges in an
  undirected graph, represented using an adjacency list
  (LinkedHashMap<String, List<String>> in CampusGraph.java).
- Add Campus Location (10) and Remove Campus Location (11), including
  cleanly detaching a removed location from every neighbour's adjacency list.
- Add Campus Connection/Road (12) and Remove Campus Connection/Road (13),
  with validation against self-loops, duplicate roads, and roads between
  locations that don't exist.
- Display Campus Connections (14), printing the full network as an
  adjacency list with a total location count.
- Traverse Campus Locations using BFS or DFS (15), implemented both
  traversals (BFS with a queue, DFS iteratively with a stack) and let the
  user choose either at runtime, with a note listing any locations
  unreachable from the chosen start (disconnected parts of the campus).
- Input validation and error handling throughout: blank input, duplicate
  IDs/locations, missing records/locations, and invalid/unavailable
  connections.

Files: CampusGraph.java
