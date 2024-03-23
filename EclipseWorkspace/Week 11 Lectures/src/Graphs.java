import tester.Tester;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

class Graph {
  ArrayList<Vertex> allVertices;

  Graph(ArrayList<Vertex> verts) {
    this.allVertices = verts;
  }

  //is there a path from the "from" node to the "to" node in this Graph?
  boolean hasPath(Vertex from, Vertex to, ICollection<Vertex> worklist) {
    ArrayList<Vertex> seen = new ArrayList<Vertex>();

    worklist.add(from);

    while (worklist.size() > 0) {
      Vertex next = worklist.remove();
      if (next.equals(to)) { return true; }

      else if (seen.contains(next)) { }

      else {
        for (Edge e : next.outEdges) {
          worklist.add(e.to);
        }
        seen.add(next);

      }

    }

    return false;

  }

  //is there a path from the "from" node to the "to" node in this Graph?
  boolean hasPathDFS(Vertex from, Vertex to) {
    return this.hasPath(from, to, new Stack<Vertex>());
  }

  //is there a path from the "from" node to the "to" node in this Graph?
  boolean hasPathBFS(Vertex from, Vertex to) {
    return this.hasPath(from, to, new Queue<Vertex>());
  }

}

class Vertex {
  String name;
  ArrayList<Edge> outEdges;

  Vertex(String name, ArrayList<Edge> outEdges){
    this.name = name;
    this.outEdges = outEdges;
  }

  // is there a path from this vertex to the given one?
  boolean hasPathTo(Vertex dest, ArrayList<Vertex> seen) {
    for (Edge e : this.outEdges) {
      if (!seen.contains(e.to)) {
        seen.add(e.to);
        if ( e.to == dest // can get there in just one step
            || e.to.hasPathTo(dest, seen)) { // can get there on a path through e.to
          return true;
        }
      }
    }
    return false;
  }
}

interface ICollection<T> {

  // how many items in this collection
  int size();

  // add the given item to the collection
  void add(T other);

  // Effect: an item is removed from the collection
  // Returns the removed item
  T remove();

}

class Queue<T> implements ICollection<T> {
  ArrayDeque<T> items;

  Queue() {
    this.items = new ArrayDeque<T>();
  }

  // Returns the size of the deque
  public int size() {
    return this.items.size();
  }

  @Override
  public void add(T t) {
    this.items.addLast(t);
  }

  // Removes the first item in the ArrayDeque
  public T remove() {
    return this.items.removeFirst();
  }

}

class Stack<T> implements ICollection<T> {
  ArrayDeque<T> items;

  Stack() {
    this.items = new ArrayDeque<T>();
  }

  // Returns the size of the deque
  public int size() {
    return this.items.size();
  }

  @Override
  public void add(T t) {
    this.items.addFirst(t);
  }

  // Removes the first item in the ArrayDeque
  public T remove() {
    return this.items.removeFirst();
  }

}

class Edge {
  Vertex from;
  Vertex to;
  int weight;

  Edge(Vertex from, Vertex to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }
}

class GraphUtils {

  //find the cheapest path from the source to the destination
  ArrayList<Vertex> cheapestPath(Vertex source, Vertex target) {
    ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
    HashMap<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

    unvisited.add(source);

    distances.put(source, 0);

    while (unvisited.size() > 0) {
      Vertex v = unvisited.remove(0);

      for (Edge e : v.outEdges) {
        if (distances.get(e.to) == null || 
            distances.get(e.to) > distances.get(v) + e.weight) {
          distances.put(e.to, distances.get(v) + e.weight);
          predecessors.put(e.to, v);
          unvisited.add(e.to);
        }
      }
    }

//    if (predecessors.get(target) == null) {
//      return -1;
//    }
//    return distances.get(target);
    
    ArrayList<Vertex> path = new ArrayList<Vertex>();
    Vertex step = target;
    path.add(step);
    if (predecessors.get(step) == null) {
      return new ArrayList<Vertex>();
    }
    
    while (step != source) {
      step = predecessors.get(step);
      path.add(0, step);
    }
    
    return path;
  }

}

class ExamplesGraphs {
  Vertex a;
  Vertex b;
  Vertex c;
  Vertex d;
  Vertex e;
  Vertex f;
  Edge aToB;
  Edge eToB;
  Edge cToA;
  Edge bToC;
  Edge bToD;
  Edge dToF;
  Edge cToF;
  Graph g;

  void init() {
    this.a = new Vertex("A", new ArrayList<Edge>());
    this.b = new Vertex("B", new ArrayList<Edge>());
    this.c = new Vertex("C", new ArrayList<Edge>());
    this.d = new Vertex("D", new ArrayList<Edge>());
    this.e = new Vertex("E", new ArrayList<Edge>());
    this.f = new Vertex("F", new ArrayList<Edge>());
    this.aToB = new Edge(this.a, this.b, 1);
    this.eToB = new Edge(this.e, this.b, 3);
    this.cToA = new Edge(this.c, this.a, 2);
    this.bToC = new Edge(this.b, this.c, 10);
    this.bToD = new Edge(this.b, this.d, 1);
    this.dToF = new Edge(this.d, this.f, 5);
    this.cToF = new Edge(this.c, this.f, 1);
    this.a.outEdges.add(this.aToB);
    this.b.outEdges.add(this.bToD);
    this.b.outEdges.add(this.bToC);
    this.e.outEdges.add(this.eToB);
    this.c.outEdges.add(this.cToA);
    this.c.outEdges.add(this.cToF);
    this.d.outEdges.add(this.dToF);
    this.g = new Graph(new ArrayList<Vertex>(Arrays.asList(this.a, this.b,
        this.c, this.d,
        this.e, this.f)));
  }

  void testPath(Tester t) {
    this.init();
    t.checkExpect(this.a.hasPathTo(this.d, new ArrayList<Vertex>()), true);
    t.checkExpect(this.a.hasPathTo(this.e, new ArrayList<Vertex>()),
        false);

    t.checkExpect(this.g.hasPathDFS(this.b, this.e), false);
    t.checkExpect(this.g.hasPathDFS(this.a, this.f), true);
    t.checkExpect(this.g.hasPathBFS(this.b, this.e), false);
    t.checkExpect(this.g.hasPathBFS(this.a, this.f), true);
    t.checkExpect(this.g.hasPath(this.b, this.e, new Queue<Vertex>()), false);
    t.checkExpect(this.g.hasPath(b, f, new Stack<Vertex>()), true);

    t.checkExpect(new GraphUtils().cheapestPath(this.a, this.f),
        new ArrayList<Vertex>(Arrays.asList(a,b,d,f)));
    t.checkExpect(new GraphUtils().cheapestPath(this.a, this.e),
        new ArrayList<Vertex>());

  }
}









