import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

/*
 *  Extra Credit Attempts:
 * 1. Allowed the user to restart the game without restarting the program
 * 2. Added a score feature to see how many moves the user has made
 * 3. Allowed the user to traverse manually and notifies at completion of the game
 * 4. Allow the user to press "t" to toggle the viewing of the visited paths
 */

// Represents a node in the maze
class Vertex {
  ArrayList<Edge> outEdges;
  int x;
  int y;
  Color color;

  Vertex(int x, int y) {
    this.outEdges = new ArrayList<Edge>();
    this.x = x;
    this.y = y;
    this.color = Color.white;
  }

  // Draws a vertex
  public WorldImage drawVertex(int vertexSize) {
    return new RectangleImage(vertexSize, vertexSize, OutlineMode.SOLID, this.color);
  }

  // Changes the color of a vertex
  // EFFECT: Changes the color of a Vertex instance to the given color.
  public void changeColor(Color given) {
    this.color = given;
  }

}

// Represents an Edge in between two nodes in the maze
class Edge implements Comparable<Edge> {
  Vertex from;
  Vertex to;
  int weight;

  Edge(Vertex from, Vertex to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  // Draws a horizontal edge
  WorldImage drawEdgeHorizontal(int unit) {
    return new RectangleImage(unit, 2, OutlineMode.SOLID, Color.gray);
  }

  // Draws a vertical edge
  WorldImage drawEdgeVertical(int unit) {
    return new RectangleImage(2, unit, OutlineMode.SOLID, Color.gray);
  }

  // Overrides compareTo to compare this edges weight with the given edges weight
  @Override
  public int compareTo(Edge other) {
    return Integer.compare(this.weight, other.weight);
  }
}

// Represents the player
class Player {
  Color color;
  int x;
  int y;
  int radius;
  ArrayList<Vertex> visited;

  Player(int x, int y, int radius) {
    this.color = Color.black;
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.visited = new ArrayList<Vertex>();
  }

  // Draws the player
  public WorldImage drawPlayer() {
    return new CircleImage(this.radius, OutlineMode.SOLID, this.color);
  }

  // Returns the x position of where the player should be placed
  public int xPosition(int unit) {
    return this.x * unit + unit / 2;
  }

  // Returns the y position of where the player should be placed
  public int yPosition(int unit) {
    return this.y * unit + unit / 2;
  }

  //Moves the player based on the clicked button
  public void movePlayer(String key, ArrayList<ArrayList<Vertex>> board, 
      ArrayList<Vertex> visited, ArrayList<Edge> edgesInTree) {
    Vertex currentVertex = board.get(this.y).get(this.x);
    Vertex nextVertex;

    if (key.equals("up") && this.y > 0) {
      nextVertex = board.get(this.y - 1).get(this.x);
      if (edgeExistsInTree(currentVertex, nextVertex, edgesInTree)) {
        this.y--;
        currentVertex.changeColor(Color.green.darker());
        visited.add(nextVertex);
      }
    }
    if (key.equals("down") && this.y < board.size() - 1) {
      nextVertex = board.get(this.y + 1).get(this.x);
      if (edgeExistsInTree(currentVertex, nextVertex, edgesInTree)) {
        this.y++;
        currentVertex.changeColor(Color.green.darker());
        visited.add(nextVertex);
      }
    }
    if (key.equals("right") && this.x < board.get(0).size() - 1) {
      nextVertex = board.get(this.y).get(this.x + 1);
      if (edgeExistsInTree(currentVertex, nextVertex, edgesInTree)) {
        this.x++;
        currentVertex.changeColor(Color.green.darker());
        visited.add(nextVertex);
      }
    }
    if (key.equals("left") && this.x > 0) {
      nextVertex = board.get(this.y).get(this.x - 1);
      if (edgeExistsInTree(currentVertex, nextVertex, edgesInTree)) {
        this.x--;
        currentVertex.changeColor(Color.green.darker());
        visited.add(nextVertex);
      }
    }
  }

  //Check if an edge between two vertices exists in the minimum spanning tree
  public boolean edgeExistsInTree(Vertex from, Vertex to, ArrayList<Edge> edgesInTree) {
    for (Edge e : edgesInTree) {
      if ((e.from.equals(from) && e.to.equals(to)) 
          || (e.from.equals(to) && e.to.equals(from))) {
        return true;
      }
    }
    return false;
  }

  // Determines if the player is on the end square
  public boolean endGame(Vertex end) {
    return (this.x == end.x && this.y == end.y);
  }
}


// Represents the maze grid as graph
// Build a maze using Kruskal's algorithm
class Maze extends World {
  ArrayList<ArrayList<Vertex>> board;
  int sizeRow;
  int sizeCol;
  Random rand;
  int worldSize;
  int unit;
  int vertexSize;
  boolean showPath;
  HashMap<Vertex, Vertex> representatives;
  ArrayList<Edge> edgesInTree;
  ArrayList<Edge> worklist;
  Player player;
  ArrayList<Vertex> visited;
  int score;
  ArrayList<Vertex> visitedBreadth;
  ArrayList<Vertex> visitedDepth;
  boolean animationBreadthDone;
  boolean animationDepthDone;
  int animationBreadthIndex;
  int animationDepthIndex;
  public HashMap<Vertex, Vertex> parentBreadth;
  public HashMap<Vertex, Vertex> parentDepth;


  Maze(int sizeCol, int sizeRow) {
    this.sizeRow = sizeRow;
    this.sizeCol = sizeCol;
    this.worldSize = 500;
    this.unit = this.worldSize / (Math.max(this.sizeCol, this.sizeRow));
    this.vertexSize = this.unit - this.unit / 5;
    this.board = this.makeBoard();
    this.rand = new Random();
    this.visited = new ArrayList<Vertex>();
    this.score = 0;
    this.showPath = true;
    this.visitedBreadth = new ArrayList<Vertex>();
    this.visitedDepth = new ArrayList<Vertex>();
    this.setEdges();
    this.representatives = new HashMap<Vertex, Vertex>();
    this.edgesInTree = new ArrayList<Edge>();
    this.worklist = new ArrayList<Edge>();
    this.initializeRepresentatives();
    this.generateWorklist();
    this.kruskalAlgorithm();
    this.player = new Player(0, 0, this.unit / 3);
    this.board.get(0).get(0).changeColor(Color.green);
    this.board.get(sizeRow - 1).get(sizeCol - 1).changeColor(Color.pink);
    this.animationBreadthDone = false;
    this.animationDepthDone = false;
    this.animationBreadthIndex = 0;
    this.animationDepthIndex = 0;
    this.parentBreadth = new HashMap<Vertex, Vertex>();
    this.parentDepth = new HashMap<Vertex, Vertex>();
  }

  // Constructor for tests
  Maze(int sizeCol, int sizeRow, Random rand) {
    this.sizeRow = sizeRow;
    this.sizeCol = sizeCol;
    this.worldSize = 500;
    this.unit = this.worldSize / (Math.max(this.sizeCol, this.sizeRow));
    this.vertexSize = this.unit - this.unit / 5;
    this.board = this.makeBoard();
    this.rand = rand;
    this.visited = new ArrayList<Vertex>();
    this.score = 0;
    this.setEdges();
    this.representatives = new HashMap<Vertex, Vertex>();
    this.edgesInTree = new ArrayList<Edge>();
    this.worklist = new ArrayList<Edge>();
    this.initializeRepresentatives();
    this.generateWorklist();
    this.kruskalAlgorithm();
    this.player = new Player(0, 0, this.unit / 3);
    this.board.get(0).get(0).changeColor(Color.green);
    this.board.get(sizeRow - 1).get(sizeCol - 1).changeColor(Color.pink);
  }

  // Initialize every node's representative to itself
  // EFFECT: setting every vertex as its own representative.
  void initializeRepresentatives() {
    for (int row = 0; row < this.sizeRow; row++) {
      for (int col = 0; col < this.sizeCol; col++) {
        Vertex vertex = this.board.get(row).get(col);
        this.representatives.put(vertex, vertex);
      }
    }
  }

  // Generate the worklist of all edges
  // EFFECT: Generates the worklist ArrayList containing all the edges
  void generateWorklist() {
    for (int row = 0; row < this.sizeRow; row++) {
      for (int col = 0; col < this.sizeCol; col++) {
        Vertex vertex = this.board.get(row).get(col);
        for (Edge e : vertex.outEdges) {
          this.worklist.add(e);
        }
      }
    }

    Collections.sort(this.worklist);
  }


  // Find the representative for the given vertex
  Vertex find(Vertex vertex) {
    while (!representatives.get(vertex).equals(vertex)) {
      vertex = representatives.get(vertex);
    }
    return vertex;
  }

  // Union the two disjoint sets
  // EFFECT: Unions two disjoint sets by updating the representative
  void union(Vertex rep1, Vertex rep2) {
    representatives.put(rep1, rep2);
  }

  // Kruskal's algorithm for generating the maze
  // EFFECT: updating the ArrayList with the edges that are part of the MST
  void kruskalAlgorithm() {
    while (this.edgesInTree.size() < this.sizeRow * this.sizeCol - 1) {

      Edge nextEdge = worklist.remove(0);
      Vertex fromRep = find(nextEdge.from);
      Vertex toRep = find(nextEdge.to);

      if (!fromRep.equals(toRep)) {
        edgesInTree.add(nextEdge);
        union(fromRep, toRep);
      }
    }
  }

  // Check if an edge between two vertices exists in the minimum spanning tree
  boolean edgeExistsInTree(Vertex from, Vertex to) {
    for (Edge e : this.edgesInTree) {
      if ((e.from.equals(from) && e.to.equals(to)) 
          || (e.from.equals(to) && e.to.equals(from))) {
        return true;
      }
    }
    return false;
  }

  // Builds the initial maze grid
  ArrayList<ArrayList<Vertex>> makeBoard() {
    ArrayList<ArrayList<Vertex>> board = new ArrayList<ArrayList<Vertex>>();

    for (int row = 0; row < this.sizeRow; row++) {
      ArrayList<Vertex> vertexRow = new ArrayList<Vertex>();

      for (int col = 0; col < this.sizeCol; col++) {
        Vertex v = new Vertex(col, row);
        vertexRow.add(v);
      }

      board.add(vertexRow);
    }

    return board;
  }

  // Set the edges between nodes in the maze grid
  // EFFECT: updates the outEdges field of each Vertex instance in the grid.
  public void setEdges() {
    for (int row = 0; row < this.sizeRow; row++) {
      for (int col = 0; col < this.sizeCol; col++) {
        Vertex vertex = this.board.get(row).get(col);

        if (col < this.sizeCol - 1) {
          vertex.outEdges.add(new Edge(vertex, this.board.get(row).get(col + 1), 
              this.rand.nextInt()));
        }

        if (row < this.sizeRow - 1) {
          vertex.outEdges.add(new Edge(vertex, this.board.get(row + 1).get(col), 
              this.rand.nextInt()));
        }
      }
    }
  }

  // Draws the maze
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.worldSize, this.worldSize);

    for (int row = 0; row < this.sizeRow; row++) {
      for (int col = 0; col < this.sizeCol; col++) {
        Vertex vertex = this.board.get(row).get(col);
        int x = vertex.x * this.unit;
        int y = vertex.y * this.unit;

        scene.placeImageXY(vertex.drawVertex(this.vertexSize), 
            x + this.unit / 2, y + this.unit / 2);

        if (col < this.sizeCol - 1 && !edgeExistsInTree(vertex,
            this.board.get(row).get(col + 1))) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeVertical(this.unit);
          scene.placeImageXY(line, x + this.unit, y + this.unit / 2);
        }

        if (row < this.sizeRow - 1 && !edgeExistsInTree(vertex,
            this.board.get(row + 1).get(col))) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeHorizontal(this.unit);
          scene.placeImageXY(line, x + this.unit / 2, y + this.unit);
        }

        if (row == this.sizeRow - 1) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeHorizontal(this.unit);
          scene.placeImageXY(line, x + this.unit / 2, y + this.unit);
        }
        if (col == this.sizeCol - 1) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeVertical(this.unit);
          scene.placeImageXY(line, x + this.unit, y + this.unit / 2);
        }
      }
    }

    // Draws the reset button
    WorldImage resetButton = new RectangleImage(this.worldSize / 6, 
        this.worldSize / 10, OutlineMode.SOLID, Color.pink);
    WorldImage resetText = new TextImage("Reset", Color.white);
    scene.placeImageXY(resetButton, this.worldSize + 50, this.worldSize / 10);
    scene.placeImageXY(resetText, this.worldSize + 50, this.worldSize / 10);

    // Draws the breadth first button
    WorldImage breadthFirstButton = new RectangleImage(this.worldSize / 4, 
        this.worldSize / 8, OutlineMode.SOLID, Color.green.darker());
    WorldImage breadthFirstText = new TextImage("Breadth First", this.unit / 1.5, Color.white);
    scene.placeImageXY(breadthFirstButton, this.worldSize / 5, 
        this.worldSize + this.worldSize / 10);
    scene.placeImageXY(breadthFirstText, this.worldSize / 5, 
        this.worldSize + this.worldSize / 10);

    // Draws the depth first button
    WorldImage depthFirstButton = new RectangleImage(this.worldSize / 4, 
        this.worldSize / 8, OutlineMode.SOLID, Color.green.darker());
    WorldImage depthFirstText = new TextImage("Depth First", this.unit / 1.5, Color.white);
    scene.placeImageXY(depthFirstButton, this.worldSize / 2 + this.worldSize / 8, 
        this.worldSize + this.worldSize / 10);
    scene.placeImageXY(depthFirstText, this.worldSize / 2 + this.worldSize / 8, 
        this.worldSize + this.worldSize / 10);

    // Draws the score
    WorldImage scoreText = new TextImage("Score: " + this.score, Color.black);
    scene.placeImageXY(scoreText, this.worldSize + 50, this.worldSize / 5);

    // Draws the player
    scene.placeImageXY(this.player.drawPlayer(), 
        this.player.xPosition(this.unit), this.player.yPosition(this.unit));

    // Draws the endText
    WorldImage endText = new TextImage("You won!", Color.black);
    if (this.player.endGame(this.board.get(sizeRow - 1).get(sizeCol - 1))) {
      scene.placeImageXY(endText, this.worldSize + 50, this.worldSize / 3);
    }

    if (this.showPath) {
      if (this.visitedBreadth.size() > 1 && this.board.get(0).get(0).color.equals(Color.blue)) {
        for (Vertex v : this.visitedBreadth) {
          v.changeColor(new Color(173, 216, 230));
          colorShortestPath(parentBreadth, this.board.get(sizeRow - 1).get(sizeCol - 1));
        }
      }

      if (this.visitedDepth.size() > 1 && this.board.get(0).get(0).color.equals(Color.blue)) {
        for (Vertex v : this.visitedDepth) {
          v.changeColor(new Color(173, 216, 230));
          colorShortestPath(parentBreadth, this.board.get(sizeRow - 1).get(sizeCol - 1));
        }
      }
    }

    if (!this.showPath) {
      for (int row = 0; row < this.sizeRow; row++) {
        for (int col = 0; col < this.sizeCol; col++) {
          this.board.get(row).get(col).changeColor(Color.white);
        }
      }
      colorShortestPath(parentBreadth, this.board.get(sizeRow - 1).get(sizeCol - 1));
    }

    return scene;
  }

  public void onTick() {
    if (!animationBreadthDone) {
      if (animationBreadthIndex < visitedBreadth.size()) {
        Vertex v = visitedBreadth.get(animationBreadthIndex);
        v.changeColor(new Color(173, 216, 230));
        animationBreadthIndex++;
      } else {
        animationBreadthDone = true;
        colorShortestPath(parentBreadth, this.board.get(sizeRow - 1).get(sizeCol - 1));
      }
    }

    if (!animationDepthDone) {
      if (animationDepthIndex < visitedDepth.size()) {
        Vertex v = visitedDepth.get(animationDepthIndex);
        v.changeColor(new Color(173, 216, 230));
        animationDepthIndex++;
      } else {
        animationDepthDone = true;
        colorShortestPath(parentDepth, this.board.get(sizeRow - 1).get(sizeCol - 1));
      }
    }

    this.score = this.visited.size();
  }

  //Determines which key the user pressed and moves the player accordingly
  public void onKeyEvent(String key) {
    if (key.equals("up")) {
      this.player.movePlayer("up", this.board, this.visited, this.edgesInTree);
    }
    if (key.equals("down")) {
      this.player.movePlayer("down", this.board, this.visited, this.edgesInTree);
    }
    if (key.equals("right")) {
      this.player.movePlayer("right", this.board, this.visited, this.edgesInTree);
    }
    if (key.equals("left")) {
      this.player.movePlayer("left", this.board, this.visited, this.edgesInTree);
    }
    if (key.equals("t")) {
      this.showPath = !this.showPath;
    }
  }

  // Checks where the user clicked
  public void onMousePressed(Posn posn) {

    // When reset button is clicked
    if (posn.x >= this.worldSize + 50 - this.worldSize / 12
        && posn.x <= this.worldSize + 50 + this.worldSize / 12
        && posn.y <= this.worldSize / 10 + this.worldSize / 20
        && posn.y >= this.worldSize / 10 - this.worldSize / 20) {
      this.resetGame();
    }

    // When the user clicked the depth first Search button
    if (posn.x >= this.worldSize / 2 + this.worldSize / 8 - this.worldSize / 8
        && posn.x <= this.worldSize / 2 + this.worldSize / 8 + this.worldSize / 8
        && posn.y <= this.worldSize + this.worldSize / 10 + this.worldSize / 16
        && posn.y >= this.worldSize + this.worldSize / 10 - this.worldSize / 16) {
      this.resetBoard();
      this.depthFirstSearch(this.board.get(0).get(0));
      this.animationDepthDone = false;
      this.animationDepthIndex = 0;
    }

    // When the user clicked the breadth first Search button
    if (posn.x >= this.worldSize / 5 - this.worldSize / 8
        && posn.x <= this.worldSize / 5 + this.worldSize / 8
        && posn.y <= this.worldSize + this.worldSize / 10 + this.worldSize / 16
        && posn.y >= this.worldSize + this.worldSize / 10 - this.worldSize / 16) {
      this.resetBoard();
      this.breadthFirstSearch(this.board.get(0).get(0));
      this.animationBreadthDone = false;
      this.animationBreadthIndex = 0;
    }
  }

  // Resets the board 
  public void resetBoard() {
    for (int row = 0; row < this.sizeRow; row++) {
      for (int col = 0; col < this.sizeCol; col++) {
        this.board.get(row).get(col).changeColor(Color.white);
      }
    }
    this.board.get(0).get(0).changeColor(Color.green);
    this.board.get(sizeRow - 1).get(sizeCol - 1).changeColor(Color.pink);
  }

  // Resets the game
  public void resetGame() {
    this.visitedBreadth = new ArrayList<Vertex>();
    this.visitedDepth = new ArrayList<Vertex>();
    this.board = this.makeBoard();
    this.rand = new Random();
    this.visited = new ArrayList<Vertex>();
    this.score = 0;
    this.setEdges();
    this.representatives = new HashMap<Vertex, Vertex>();
    this.edgesInTree = new ArrayList<Edge>();
    this.worklist = new ArrayList<Edge>();
    this.initializeRepresentatives();
    this.generateWorklist();
    this.kruskalAlgorithm();
    this.player = new Player(0, 0, this.unit / 3);
    this.board.get(0).get(0).changeColor(Color.green);
    this.board.get(sizeRow - 1).get(sizeCol - 1).changeColor(Color.pink);
  }

  // Implement depthFirstSearch to find the shortest path
  public void depthFirstSearch(Vertex start) {
    Stack<Vertex> stack = new Stack<>();
    parentDepth = new HashMap<Vertex, Vertex>();

    stack.push(start);
    this.visitedDepth.add(start);

    while (!stack.isEmpty()) {
      Vertex current = stack.pop();

      for (Edge e : this.edgesInTree) {
        if (e.from.equals(current) && !this.visitedDepth.contains(e.to)) {
          stack.push(e.to);
          parentDepth.put(e.to, current);
          this.visitedDepth.add(current);
        } 
        else if (e.to.equals(current) && !this.visitedDepth.contains(e.from)) {
          stack.push(e.from);
          parentDepth.put(e.from, current);
          this.visitedDepth.add(current);
        }
      }
    }
  }

  // Implement breadthFirstSearch to find the shortest path
  public void breadthFirstSearch(Vertex start) {
    Queue<Vertex> queue = new LinkedList<>();
    parentBreadth = new HashMap<Vertex, Vertex>();

    queue.add(start);
    this.visitedBreadth.add(start);

    while (!queue.isEmpty()) {
      Vertex current = queue.poll();

      for (Edge e : this.edgesInTree) {
        if (e.from.equals(current) && !this.visitedBreadth.contains(e.to)) {
          queue.add(e.to);
          parentBreadth.put(e.to, current);
          this.visitedBreadth.add(current);
        } 
        else if (e.to.equals(current) && !this.visitedBreadth.contains(e.from)) {
          queue.add(e.from);
          parentBreadth.put(e.from, current);
          this.visitedBreadth.add(current);
        }
      }
    }
  }

  // Color the shortest path 
  public void colorShortestPath(HashMap<Vertex, Vertex> parent, Vertex target) {
    Vertex current = target;

    while (parent.containsKey(current)) {
      current.changeColor(Color.blue);
      current = parent.get(current);
    }
  }
}

// Examples and tests for Maze
class ExamplesMaze {
  Maze maze = new Maze(20, 20);

  // Calling bigBang
  void testBigBang(Tester t) {
    this.maze.bigBang(600, 600, 0.1);
  }

  // Tests the drawEdgeHorizontal method
  void testDrawEdgeHorizontal(Tester t) {
    Vertex a = new Vertex(1, 1);
    Vertex b = new Vertex(2, 2);
    Edge edge = new Edge(a, b, 2);
    WorldImage edgeA = new RectangleImage(10, 2, OutlineMode.SOLID, Color.gray);
    WorldImage edgeB = new RectangleImage(1000, 2, OutlineMode.SOLID, Color.gray);

    t.checkExpect(edge.drawEdgeHorizontal(10), edgeA);
    t.checkExpect(edge.drawEdgeHorizontal(1000), edgeB);
  }

  // Tests the drawEdgeVertical method
  void testDrawEdgeVertical(Tester t) {
    Vertex a = new Vertex(1, 1);
    Vertex b = new Vertex(2, 2);
    Edge edge = new Edge(a, b, 2);
    WorldImage edgeA = new RectangleImage(2, 10, OutlineMode.SOLID, Color.gray);
    WorldImage edgeB = new RectangleImage(2, 1000, OutlineMode.SOLID, Color.gray);

    t.checkExpect(edge.drawEdgeVertical(10), edgeA);
    t.checkExpect(edge.drawEdgeVertical(1000), edgeB);
  }

  // Tests the drawVertex method
  void testDrawVertex(Tester t) {
    Vertex a = new Vertex(1, 1);
    t.checkExpect(a.drawVertex(8), new RectangleImage(8, 8, OutlineMode.SOLID, Color.white));
    Vertex b = new Vertex(10, 10);
    t.checkExpect(b.drawVertex(16), new RectangleImage(16, 16, OutlineMode.SOLID, Color.white));
    Vertex c = new Vertex(5, 5);
    t.checkExpect(c.drawVertex(24), new RectangleImage(24, 24, OutlineMode.SOLID, Color.white));
  }

  // Tests the changeColor method
  void testChangeColor(Tester t) {
    Vertex a = new Vertex(1, 2);
    Vertex b = new Vertex(2, 2);

    Vertex c = new Vertex(3, 2);
    a.changeColor(Color.green);
    t.checkExpect(a.color, Color.green);
    b.changeColor(Color.blue);
    t.checkExpect(b.color, Color.blue);
    c.changeColor(Color.pink);
    t.checkExpect(c.color, Color.pink);
  }

  // tests the compareTo method
  void testCompareTo(Tester t) {
    Vertex one = new Vertex(2, 2);
    Vertex two = new Vertex(3, 3);

    Edge edge1 = new Edge(one, two, 2);
    Edge edge2 = new Edge(one, two, 3);
    Edge edge3 = new Edge(one, two, 2);
    Edge edge4 = new Edge(one, two, 4);
    Edge edge5 = new Edge(one, two, 5);

    t.checkExpect(edge1.compareTo(edge5), -1);
    t.checkExpect(edge2.compareTo(edge5), -1);
    t.checkExpect(edge3.compareTo(edge2), -1);
    t.checkExpect(edge4.compareTo(edge4), 0);
    t.checkExpect(edge5.compareTo(edge1), 1);
  }

  // Tests the endGame method
  void testEndGame(Tester t) {
    Player p1 = new Player(2, 3, 30);
    Vertex v1 = new Vertex(2, 3);
    t.checkExpect(p1.endGame(v1), true);
    Player p2 = new Player(3, 4, 30);
    Vertex v2 = new Vertex(2, 3);
    t.checkExpect(p2.endGame(v2), false);
    Player p3 = new Player(8, 6, 30);
    Vertex v3 = new Vertex(8, 6);
    t.checkExpect(p3.endGame(v3), true);
  }

  // Tests the drawPlayer method
  void testDrawPlayer(Tester t) {
    Player p1 = new Player(0, 0, 2);
    Player p2 = new Player(10, 20, 10);

    WorldImage p1Image = new CircleImage(2, OutlineMode.SOLID, Color.black);
    WorldImage p2Image = new CircleImage(10, OutlineMode.SOLID, Color.black);

    t.checkExpect(p1.drawPlayer(), p1Image);
    t.checkExpect(p2.drawPlayer(), p2Image);

  }

  // Tests the xPosition method
  void testXPosition(Tester t) {
    Player p1 = new Player(10, 15, 20);
    Player p2 = new Player(15, 10, 15);
    Player p3 = new Player(20, 15, 12);
    Player p4 = new Player(30, 25, 27);

    t.checkExpect(p1.xPosition(10), 105);
    t.checkExpect(p2.xPosition(15), 232);
    t.checkExpect(p3.xPosition(2), 41);
    t.checkExpect(p4.xPosition(30), 915);
  }

  // Tests the yPosition method
  void testYPosition(Tester t) {
    Player p1 = new Player(10, 15, 20);
    Player p2 = new Player(15, 10, 15);
    Player p3 = new Player(20, 15, 12);
    Player p4 = new Player(30, 25, 27);

    t.checkExpect(p1.yPosition(10), 155);
    t.checkExpect(p2.yPosition(15), 157);
    t.checkExpect(p3.yPosition(2), 31);
    t.checkExpect(p4.yPosition(30), 765);
  }

  // Tests the onKeyEvent method
  void testOnKeyEvent(Tester t) {
    Maze maze = new Maze(10, 10, new Random(3));

    t.checkExpect(maze.player.x, 0);
    maze.onKeyEvent("right");
    t.checkExpect(maze.player.x, 1);

    maze.onKeyEvent("left");
    t.checkExpect(maze.player.x, 0);

    maze.onKeyEvent("up");
    t.checkExpect(maze.player.y, 0);

    maze.onKeyEvent("right");
    t.checkExpect(maze.player.y, 0);
    t.checkExpect(maze.player.x, 1);

    maze.onKeyEvent("down");
    t.checkExpect(maze.player.y, 1);
  }

  //tests for makeBoard
  void testMakeBoard(Tester t) {
    Maze maze1 = new Maze(1, 1);
    ArrayList<ArrayList<Vertex>> expectedBoard1 = new ArrayList<ArrayList<Vertex>>();
    ArrayList<Vertex> expectedRow1 = new ArrayList<Vertex>();
    Vertex expectedVertex1 = new Vertex(0, 0);
    expectedVertex1.color = Color.pink;
    expectedRow1.add(expectedVertex1);
    expectedBoard1.add(expectedRow1);
    t.checkExpect(maze1.board, expectedBoard1);
  }

  //Tests that each vertex has the expected number of edges
  void testSetEdges(Tester t) {
    Maze maze = new Maze(3, 3);
    for (ArrayList<Vertex> row : maze.board) {
      for (Vertex vertex : row) {
        int expectedEdgeCount = 0;

        // Checking if there are the right number of edges for each vertex
        if (vertex.x < maze.sizeRow - 1) {
          expectedEdgeCount++;
        }
        if (vertex.y < maze.sizeCol - 1) {
          expectedEdgeCount++;
        }
        t.checkExpect(vertex.outEdges.size(), expectedEdgeCount);

        // Checking if the board contains every edge
        for (Edge edge : vertex.outEdges) {
          t.checkExpect(edge.from, vertex);
          t.checkExpect(maze.board.get(0).contains(edge.to)
              || maze.board.get(1).contains(edge.to)
              || maze.board.get(2).contains(edge.to), true);
        }
      }
    }
  }

  // Tests the reset method
  void testResetBoard(Tester t) {
    Maze maze = new Maze(10, 10);
    maze.onKeyEvent("up");
    maze.onKeyEvent("down");
    maze.onKeyEvent("left");
    maze.onKeyEvent("right");
    t.checkExpect(maze.board.get(0).get(0).color, Color.green.darker());
    maze.resetBoard();
    t.checkExpect(maze.board.get(0).get(0).color, Color.green);
  }

  //Tests for resetGame method
  void testResetGame(Tester t) {
    Maze maze = new Maze(10, 10);
    maze.resetGame();

    t.checkExpect(maze.visitedBreadth.size(), 0);
    t.checkExpect(maze.visitedDepth.size(), 0);
    t.checkExpect(maze.board.size(), 10);
    t.checkExpect(maze.board.get(0).size(), 10);
    t.checkExpect(maze.visited.size(), 0);
    t.checkExpect(maze.score, 0);
    t.checkExpect(maze.board.get(0).get(0).color, Color.green);
    t.checkExpect(maze.board.get(9).get(9).color, Color.pink);
  }

  //Tests for depthFirstSearch method
  void testDepthFirstSearch(Tester t) {
    Maze maze = new Maze(10, 10);
    maze.resetGame();
    maze.depthFirstSearch(maze.board.get(0).get(0));

    t.checkExpect(maze.parentDepth.size(), 99);

    Vertex current = maze.board.get(maze.sizeRow - 1).get(maze.sizeCol - 1);

    t.checkExpect(current.color, Color.pink);
  }

  //Tests for breadthFirstSearch method
  void testBreadthFirstSearch(Tester t) {
    Maze maze = new Maze(10, 10);
    maze.resetGame();
    maze.breadthFirstSearch(maze.board.get(0).get(0));

    t.checkExpect(maze.parentBreadth.size(), 99);

    Vertex current = maze.board.get(maze.sizeRow - 1).get(maze.sizeCol - 1);

    t.checkExpect(current.color, Color.pink);
  }

  // Test for makeScene method
  void testMakeScene(Tester t) {
    Maze testMaze = new Maze(3, 3);
    WorldScene scene = testMaze.makeScene();
    WorldScene expectedScene = new WorldScene(500, 500);

    // Draw the expected scene for later comparison
    for (int row = 0; row < testMaze.sizeRow; row++) {
      for (int col = 0; col < testMaze.sizeCol; col++) {
        Vertex vertex = testMaze.board.get(row).get(col);
        int x = vertex.x * testMaze.unit;
        int y = vertex.y * testMaze.unit;
        testMaze.board.get(0).get(0).color = Color.green;
        testMaze.board.get(2).get(2).color = Color.pink;

        expectedScene.placeImageXY(vertex.drawVertex(133), 
            x + testMaze.unit / 2, y + testMaze.unit / 2);

        if (col < testMaze.sizeCol - 1 
            && !testMaze.edgeExistsInTree(vertex,
                testMaze.board.get(row).get(col + 1))) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeVertical(testMaze.unit);
          expectedScene.placeImageXY(line, x + testMaze.unit, y + testMaze.unit / 2);
        }

        if (row < testMaze.sizeRow - 1 
            && !testMaze.edgeExistsInTree(vertex,
                testMaze.board.get(row + 1).get(col))) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeHorizontal(testMaze.unit);
          expectedScene.placeImageXY(line, x + testMaze.unit / 2, y + testMaze.unit);
        }

        if (row == testMaze.sizeRow - 1) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeHorizontal(testMaze.unit);
          expectedScene.placeImageXY(line, x + testMaze.unit / 2, y + testMaze.unit);
        }
        if (col == testMaze.sizeCol - 1) {
          WorldImage line = new Edge(vertex, vertex, 0).drawEdgeVertical(testMaze.unit);
          expectedScene.placeImageXY(line, x + testMaze.unit, y + testMaze.unit / 2);
        }
      }
    }

    // Draws the reset button
    WorldImage resetButton = new RectangleImage(500 / 6, 
        500 / 10, OutlineMode.SOLID, Color.pink);
    WorldImage resetText = new TextImage("Reset", Color.white);
    expectedScene.placeImageXY(resetButton, testMaze.sizeCol * testMaze.unit + 50, 
        testMaze.sizeRow / 2 * testMaze.unit);
    expectedScene.placeImageXY(resetText, testMaze.sizeCol * testMaze.unit + 50, 
        testMaze.sizeRow / 2 * testMaze.unit);

    // Draws the breadth first button
    WorldImage breadthFirstButton = new RectangleImage(500 / 4, 
        testMaze.worldSize / 8, OutlineMode.SOLID, Color.green.darker());
    WorldImage breadthFirstText = new TextImage("Breadth First", testMaze.unit / 1.5, Color.white);
    expectedScene.placeImageXY(breadthFirstButton, testMaze.sizeRow / 5 * testMaze.unit, 
        testMaze.sizeCol * testMaze.unit + 50);
    expectedScene.placeImageXY(breadthFirstText, testMaze.sizeRow / 5 * testMaze.unit, 
        testMaze.sizeCol * testMaze.unit + 50);

    // Draws the depth first button
    WorldImage depthFirstButton = new RectangleImage(500 / 4, 
        500 / 8, OutlineMode.SOLID, Color.green.darker());
    WorldImage depthFirstText = new TextImage("Depth First", testMaze.unit / 1.5, Color.white);
    expectedScene.placeImageXY(depthFirstButton, testMaze.worldSize / 2 + testMaze.sizeRow * 3, 
        testMaze.sizeCol * testMaze.unit + 50);
    expectedScene.placeImageXY(depthFirstText, testMaze.worldSize / 2 + testMaze.sizeRow * 3, 
        testMaze.sizeCol * testMaze.unit + 50);

    expectedScene.placeImageXY(new Player(0, 0, testMaze.unit / 3).drawPlayer(), 
        83, 83);

    t.checkExpect(scene, expectedScene);
  }


  // Tests for initializeRepresentatives method
  void testInitializeRepresentatives(Tester t) {
    Maze maze = new Maze(2, 2);
    HashMap<Vertex, Vertex> expectedRepresentatives = new HashMap<Vertex, Vertex>();
    for (ArrayList<Vertex> row : maze.board) {
      for (Vertex vertex : row) {
        expectedRepresentatives.put(vertex, vertex);
      }
    }
    maze.initializeRepresentatives();
    t.checkExpect(maze.representatives, expectedRepresentatives);
  }

  // Tests for generateWorklist method
  void testGenerateWorklist(Tester t) {
    // Test with a 1x1 maze
    Maze maze1 = new Maze(1, 1);
    ArrayList<Edge> expectedWorklist1 = new ArrayList<Edge>();
    t.checkExpect(maze1.worklist, expectedWorklist1);

    // Test with a 2x2 maze
    Maze maze2 = new Maze(2, 2);
    maze2.setEdges(); 
    maze2.generateWorklist();

    ArrayList<Edge> worklist2 = maze2.worklist;
    // Check if the worklist is sorted
    t.checkExpect(worklist2.get(0).weight <= worklist2.get(1).weight, true);
    t.checkExpect(worklist2.get(1).weight <= worklist2.get(2).weight, true);

    // Check that all possible edges in the maze are in the worklist
    Vertex topLeft = maze2.board.get(0).get(0);
    Vertex topRight = maze2.board.get(0).get(1);
    Vertex bottomLeft = maze2.board.get(1).get(0);
    Vertex bottomRight = maze2.board.get(1).get(1);

    boolean topLeftToTopRightExists = false;
    boolean topLeftToBottomLeftExists = false;
    boolean topRightToBottomRightExists = false;

    for (Edge edge : worklist2) {
      if ((edge.from == topLeft && edge.to == topRight) 
          || (edge.from == topRight && edge.to == topLeft)) {
        topLeftToTopRightExists = true;
      } else if ((edge.from == topLeft && edge.to == bottomLeft) 
          || (edge.from == bottomLeft && edge.to == topLeft)) {
        topLeftToBottomLeftExists = true;
      } else if ((edge.from == topRight && edge.to == bottomRight) 
          || (edge.from == bottomRight && edge.to == topRight)) {
        topRightToBottomRightExists = true;
      }
    }
    t.checkExpect(topLeftToTopRightExists, true);
    t.checkExpect(topLeftToBottomLeftExists, true);
    t.checkExpect(topRightToBottomRightExists, true);  
  }

  // Tests for the find method
  void testFind(Tester t) {
    Maze maze = new Maze(4, 4);
    maze.initializeRepresentatives();
    Vertex v1 = maze.board.get(0).get(0);
    Vertex v2 = maze.board.get(0).get(1);
    Vertex v3 = maze.board.get(1).get(0);
    Vertex v4 = maze.board.get(1).get(1);

    // Check that every vertex is its own representative initially
    t.checkExpect(maze.find(v1), v1);
    t.checkExpect(maze.find(v2), v2);
    t.checkExpect(maze.find(v3), v3);
    t.checkExpect(maze.find(v4), v4);

    // Check that union of vertices changes their representatives
    maze.union(v1, v2);
    t.checkExpect(maze.find(v1), v2);
    t.checkExpect(maze.find(v2), v2);

    maze.union(v3, v4);
    t.checkExpect(maze.find(v3), v4);
    t.checkExpect(maze.find(v4), v4);

    maze.union(v2, v4);
    t.checkExpect(maze.find(v1), v4);
    t.checkExpect(maze.find(v2), v4);
    t.checkExpect(maze.find(v3), v4);
    t.checkExpect(maze.find(v4), v4);
  }

  // Test kruskalAlgorithm
  void testKruskalAlgorithm(Tester t) {
    Maze maze = new Maze(5, 5);
    maze.initializeRepresentatives();
    maze.generateWorklist();
    maze.kruskalAlgorithm();

    t.checkExpect(maze.edgesInTree.size(), maze.sizeRow * maze.sizeCol - 1);

    // Check if the graph is connected after applying Kruskal's algorithm
    ArrayList<Vertex> visited = new ArrayList<>();
    LinkedList<Vertex> toVisit = new LinkedList<>();
    Vertex startVertex = maze.board.get(0).get(0);
    toVisit.add(startVertex);

    while (!toVisit.isEmpty()) {
      Vertex currentVertex = toVisit.removeFirst();
      if (!visited.contains(currentVertex)) {
        visited.add(currentVertex);
        for (Edge edge : maze.edgesInTree) {
          Vertex nextVertex = null;
          if (edge.from.equals(currentVertex)) {
            nextVertex = edge.to;
          } else if (edge.to.equals(currentVertex)) {
            nextVertex = edge.from;
          }
          if (nextVertex != null && !visited.contains(nextVertex)) {
            toVisit.addLast(nextVertex);
          }
        }
      }
    }
    t.checkExpect(visited.size(), maze.sizeRow * maze.sizeCol);
  }

  // Tests for the union method
  void testUnion(Tester t) {
    Maze maze = new Maze(4, 4);
    maze.initializeRepresentatives();
    Vertex v1 = maze.board.get(0).get(0);
    Vertex v2 = maze.board.get(0).get(1);
    Vertex v3 = maze.board.get(1).get(0);
    Vertex v4 = maze.board.get(1).get(1);

    // Check that representatives are initially correct
    t.checkExpect(maze.representatives.get(v1), v1);
    t.checkExpect(maze.representatives.get(v2), v2);
    t.checkExpect(maze.representatives.get(v3), v3);
    t.checkExpect(maze.representatives.get(v4), v4);

    // Union v1 and v2 to check that representatives are updated correctly
    maze.union(v1, v2);
    t.checkExpect(maze.representatives.get(v1), v2);
    t.checkExpect(maze.representatives.get(v2), v2);

    // Union v3 and v4 to check that representatives are updated correctly
    maze.union(v3, v4);
    t.checkExpect(maze.representatives.get(v3), v4);
    t.checkExpect(maze.representatives.get(v4), v4);
  }


  // Test for edgeExistsInTree method
  // check if it returns the expected results for different pairs of vertices.
  void testEdgeExistsInTree(Tester t) {
    Maze maze = new Maze(4, 4);
    maze.edgesInTree.add(new Edge(maze.board.get(0).get(0),
        maze.board.get(0).get(1), 1));
    maze.edgesInTree.add(new Edge(maze.board.get(0).get(0),
        maze.board.get(1).get(0), 2));
    maze.edgesInTree.add(new Edge(maze.board.get(0).get(1),
        maze.board.get(1).get(1), 3));
    maze.edgesInTree.add(new Edge(maze.board.get(1).get(0),
        maze.board.get(1).get(1), 4));

    t.checkExpect(maze.edgeExistsInTree(maze.board.get(0).get(0),
        maze.board.get(0).get(1)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(0).get(1),
        maze.board.get(0).get(0)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(0).get(0),
        maze.board.get(1).get(0)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(1).get(1),
        maze.board.get(0).get(1)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(1).get(0),
        maze.board.get(1).get(1)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(1).get(1),
        maze.board.get(1).get(0)), true);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(0).get(0),
        maze.board.get(3).get(3)), false);
    t.checkExpect(maze.edgeExistsInTree(maze.board.get(1).get(2),
        maze.board.get(1).get(2)), false);
  }
}