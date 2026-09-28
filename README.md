# University Student Record and Campus Route Management System

## CIT300 - Data Structures and Algorithms

This project is a Java console-based application developed for the
CIT300 Graded Practical Assignment 1.

The system demonstrates the practical use of:

- Linked List
- Stack
- Queue
- Binary Search Tree (BST)
- Hashing
- Graph
- BFS
- DFS

## System Features

1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS/DFS
16. Exit

## Project Structure

```text
src/
├── Student.java
├── StudentLinkedList.java
├── ActionRecord.java
├── ActionStack.java
├── ServiceRequest.java
├── ServiceQueue.java
├── BSTNode.java
├── StudentBST.java
├── StudentHashTable.java
├── CampusGraph.java
├── InputValidator.java
└── Main.java

## Group Members & Contributions

| Member Name | Student ID | Assigned Responsibility | Individual Contribution |
| :--- | :--- | :--- | :--- |
| **MTF.Naseeba** *(Leader)* | [23DA2-0998] | Integration, Validation & Core Setup | Initialized repository, set up `.gitignore`/`README.md`, implemented `Main.java` menu integration and `InputValidator.java`. |
| **UKR.Madhuri Nishevidha** | [23DA2-0520] | Stacks & Queues | Implemented `ActionRecord.java`, `ActionStack.java`, `ServiceRequest.java`, and `ServiceQueue.java`. |
| **RF.Shafna** | [23DA2-1024] | Trees & Hashing | Implemented `BSTNode.java`, `StudentBST.java`, and `StudentHashTable.java`. |
| **MMF.Mifra** | [23DA2-0833] | Graph Component | Implemented `CampusGraph.java` with adjacency representation and BFS/DFS traversal. |