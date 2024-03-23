import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

interface IBT<T> extends Iterable<T>{
  //is this tree a node?
  boolean isNode();
}

class Leaf<T> implements IBT<T> {

  //creates an iterator for this leaf traverses breadth first
  public Iterator<T> iterator() {
    return new BFIter<T>(this);
  }

  //is this leaf a node?
  public boolean isNode() {
    return false;
  }

}

class Node<T> implements IBT<T> {
  T data;
  IBT<T> left;
  IBT<T> right;
  Node(T data, IBT<T> left, IBT<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  //creates an iterator for this node traverses breadth first
  public Iterator<T> iterator() {
    return new BFIter<T>(this);
  }

  //is this node a node?
  public boolean isNode() {
    return true;
  }
}

//relaxing the rules on type-checking and casting
class BFIter<T> implements Iterator<T> {
  ArrayList<IBT<T>> worklist;

  BFIter(IBT<T> tree) {
    this.worklist = new ArrayList<IBT<T>>();
    this.addIfNode(tree);
  }

  //EFFECT: adds a tree to this.worklist if it's a node
  void addIfNode(IBT<T> tree) {
    if (tree.isNode()) {
      this.worklist.add(tree);
    }
  }

  //is there anotehr item to produce
  public boolean hasNext() {
    return this.worklist.size() > 0;
  }
  //get the next item
  //first node is removed from the worklist
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more itmes");
    }
    else {
      Node<T> currentNode = (Node<T>)this.worklist.remove(0);
      this.addIfNode(currentNode.left);
      this.addIfNode(currentNode.right);
      return currentNode.data;
    }
  }

}

//relaxing the rules on type-checking and casting
class DIter<T> implements Iterator<T> {
ArrayList<IBT<T>> worklist;

DIter(IBT<T> tree) {
  this.worklist = new ArrayList<IBT<T>>();
  this.addIfNode(tree);
}

//EFFECT: adds a tree to this.worklist if it's a node
void addIfNode(IBT<T> tree) {
  if (tree.isNode()) {
    this.worklist.add(0, tree);
  }
}

//is there anotehr item to produce
public boolean hasNext() {
  return this.worklist.size() > 0;
}
//get the next item
//first node is removed from the worklist
public T next() {
  if (!this.hasNext()) {
    throw new NoSuchElementException("no more itmes");
  }
  else {
    Node<T> currentNode = (Node<T>)this.worklist.remove(0);
    this.addIfNode(currentNode.right);
    this.addIfNode(currentNode.left);
    return currentNode.data;
  }
}

}

class Examples {
  IBT<String> leaf = new Leaf<String>();
  IBT<String> gNode = new Node<String>("G", this.leaf, this.leaf);
  IBT<String> fNode = new Node<String>("F", this.leaf, this.leaf);
  IBT<String> eNode = new Node<String>("E", this.leaf, this.leaf);
  IBT<String> dNode = new Node<String>("D", this.leaf, this.leaf);
  IBT<String> cNode = new Node<String>("C", this.fNode, this.gNode);
  IBT<String> bNode = new Node<String>("B", this.dNode, this.eNode);
  IBT<String> aNode = new Node<String>("A", this.bNode, this.cNode);

  void testBTIter(Tester t) {
    BFIter<String> bfiter = new BFIter<String>(aNode);
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "A");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "B");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "C");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "D");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "E");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "F");
    t.checkExpect(bfiter.hasNext(), true);
    t.checkExpect(bfiter.next(), "G");
    t.checkExpect(bfiter.hasNext(), false);    
  }

}









