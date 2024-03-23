//import java.util.*;
//import tester.Tester;
//
//
//class ListOfLists<T> implements Iterable<T>{
//  ArrayList<ArrayList<T>> listOfLists;
//
//  ListOfLists() { 
//    this.listOfLists = new ArrayList<>();
//  }
//
//  //EFFECT: Adds a new ArrayList<T> to the end of the list of lists
//  void addNewList() {
//    this.listOfLists.add(new ArrayList<T>());
//  }
//
//  void add(int index, T object) {
//    if (index < 0 || index >= listOfLists.size()) {
//      throw new IndexOutOfBoundsException("Invalid index: " + index);
//    }
//    this.listOfLists.get(index).add(object);
//  }
//
//  ArrayList<T> get(int index) {
//    if (index < 0 || index >= listOfLists.size()) {
//      // if invalid index, throw new IDOB exception
//      throw new IndexOutOfBoundsException("Invalid index: " + index);
//    }
//    return this.listOfLists.get(index);
//  }
//
//  @Override
//  public Iterator<T> iterator() {
//    return new ListOfListsIterator<T>(this);
//  }
//
//}
//
//public class ListOfListsIterator<T> implements Iterator<T> {
//  int outerIndex = 0;
//  int innerIndex = 0;
//  ListOfLists<T> list;
//
//  ListOfListsIterator(ListOfLists<T> list) {
//    this.list = list;
//  }
//
//
//  @Override
//  public boolean hasNext() {
//    return outerIndex < list.get(0).size();
//  }
//
//  // Returns the next item in the iterator
//  // Effect: moves the pointer to the next diagonal item in the matrix
//  public T next() {
//    if (!this.hasNext()) {
//      throw new NoSuchElementException("No more items");
//    }
//
//    T elem = this.next();
//    if (this.hasNext()) {
//      for (int i = 0; i < list.get(0).size(); i++) {
//        list.get(i).get(i);
//      }
//    }
//    return elem;
//  }
//}
//
//
//class ExamplesMatrix {
//  ListOfLists<Integer> matrix = new ListOfLists<Integer>();
//  ListOfListsIterator<Integer> list = new ListOfListsIterator<Integer>(matrix);
//  void testMatrix(Tester t) {
//    matrix.addNewList();
//    matrix.addNewList();
//    matrix.addNewList();
//    matrix.add(0, 1);
//    matrix.add(0, 0);
//    matrix.add(0, 0);
//    matrix.add(1, 0);
//    matrix.add(1, 1);
//    matrix.add(1, 0);
//    matrix.add(2, 0);
//    matrix.add(2, 0);
//    matrix.add(2, 1);
//    t.checkExpect(matrix.hasNext(), true);
//    t.checkExpect(matrix.next(), 1);
//    t.checkExpect(matrix.hasNext(), true);
//    t.checkExpect(matrix.next(), 1);
//    t.checkExpect(matrix.hasNext(), true);
//    t.checkExpect(matrix.next(), 1);
//    t.checkExpect(matrix.hasNext(), false);
//  }
//}
//
//
//
//
//
//
