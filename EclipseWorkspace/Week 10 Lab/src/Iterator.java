import java.util.ArrayList;
import java.util.Arrays;
import tester.Tester;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents an iterable list of lists.
 * T is the type of objects stored in the lists.
 */
class ListOfLists<T> implements Iterable<T> {
  ArrayList<ArrayList<T>> listOfLists;

  ListOfLists() { 
    this.listOfLists = new ArrayList<>();
  }

  //EFFECT: Adds a new ArrayList<T> to the end of the list of lists
  void addNewList() {
    this.listOfLists.add(new ArrayList<T>());
  }

  /**
   * Adds the given object to the end of the list at the specified index
   * throws IndexOutOfBoundsException if the index is out of range
   */
  void add(int index, T object) {
    if (index < 0 || index >= listOfLists.size()) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    this.listOfLists.get(index).add(object);
  }

  /**
   * Returns the list at the specified index in the list of lists.
   * throws IndexOutOfBoundsException if the index is out of range 
   */
  ArrayList<T> get(int index) {
    if (index < 0 || index >= listOfLists.size()) {
      // if invalid index, throw new IDOB exception
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    return this.listOfLists.get(index);
  }

  //Returns how many lists are in the arraylist
  public int size() {
    return this.listOfLists.size();
  }

  //iterator for ListOfArrayList
  public Iterator<T> iterator() {
    return new ListOfListsIterator<T>(this);
  }

}

//Represents an iterator for ListOfLists<T> that iterates through
//the items in a specified order
class ListOfListsIterator<T> implements Iterator<T> {
  int outerIndex;
  int innerIndex;
  ListOfLists<T> list;

  public ListOfListsIterator(ListOfLists<T> list) {
    this.outerIndex = 0;
    this.innerIndex = 0;
    this.list = list;
  }

  //Whether there are more items
  public boolean hasNext() {
    if (this.outerIndex < this.list.size() && this.innerIndex 
        >= this.list.get(this.outerIndex).size()) {
      this.outerIndex = this.outerIndex + 1;
      this.innerIndex = 0;
    }

    return this.outerIndex < this.list.size();
  }

  //EFFECT: return the next item and also advance the index
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more items");
    }

    else {
      T nextElement = this.list.get(this.outerIndex).get(this.innerIndex);
      this.innerIndex = this.innerIndex + 1;

      if (this.innerIndex >= this.list.get(this.outerIndex).size()) {
        this.outerIndex = this.outerIndex + 1;
        this.innerIndex = 0;
      }

      return nextElement;
    }
  }
}

//Examples and tests for iteration of list of lists
class ExamplesLoLIterators {
  public ListOfLists<Integer> lol = new ListOfLists<Integer>();
  ListOfLists<String> fundies = new ListOfLists<String>();

  //tests the addNewList method and the size method
  void testAddNewListSize(Tester t) {
    ListOfLists<String> testFundies = new ListOfLists<String>();
    t.checkExpect(testFundies.size(), 0);
    testFundies.addNewList();
    t.checkExpect(testFundies.size(), 1);
    testFundies.addNewList();
    t.checkExpect(testFundies.size(), 2);
    testFundies.addNewList();
    t.checkExpect(testFundies.size(), 3);
  }

  //tests the testAdd method
  void testAdd(Tester t) {
    ListOfLists<String> testAdd = new ListOfLists<String>();
    testAdd.addNewList();
    testAdd.add(0, "CS2510");
    t.checkExpect(testAdd.get(0), new ArrayList<String>(Arrays.asList("CS2510")));
    testAdd.add(0, "S2");
    t.checkExpect(testAdd.get(0), new ArrayList<String>(Arrays.asList("CS2510", "S2")));
    testAdd.addNewList();
    testAdd.add(1, "java");
    t.checkExpect(testAdd.get(1), new ArrayList<String>(Arrays.asList("java")));

    // Test for exception cases
    t.checkException(new IndexOutOfBoundsException("Invalid index: -1"),
        testAdd, "add", -1, "test");
    t.checkException(new IndexOutOfBoundsException("Invalid index: 2"),
        testAdd, "add", 2, "test");
  }


  //tests the get method
  void testGet(Tester t) {
    this.fundies.addNewList(); 
    this.fundies.addNewList();
    this.fundies.addNewList();

    this.fundies.add(0, "1");
    this.fundies.add(0, "2");
    this.fundies.add(0, "3");

    this.fundies.add(1, "4");
    this.fundies.add(1, "5");
    this.fundies.add(1, "6");

    this.fundies.add(2, "7");
    this.fundies.add(2, "8");
    this.fundies.add(2, "9");

    t.checkExpect(this.fundies.get(0), new ArrayList<String>(Arrays.asList("1", "2", "3")));
    t.checkExpect(this.fundies.get(1), new ArrayList<String>(Arrays.asList("4", "5", "6")));
    t.checkExpect(this.fundies.get(2), new ArrayList<String>(Arrays.asList("7", "8", "9")));
    t.checkException(new IndexOutOfBoundsException("Invalid index: -1"), this.fundies, "get", -1);
    t.checkException(new IndexOutOfBoundsException("Invalid index: 3"), this.fundies, "get", 3);
    t.checkException(new IndexOutOfBoundsException("Invalid index: 4"), this.fundies, "get", 4);
  }


  void testListOfLists(Tester t) {
    Iterator<Integer> iter = lol.iterator();
    
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();

    //add elements 1,2,3 in first list
    lol.add(0, 1);
    lol.add(0, 2);
    lol.add(0, 3);

    //add elements 4,5,6 in second list
    lol.add(1, 4);
    lol.add(1, 5);
    lol.add(1, 6);

    //add elements 7,8,9 in third list
    lol.add(2, 7);
    lol.add(2, 8);
    lol.add(2, 9);

    //iterator should return elements in order 1,2,3,4,5,6,7,8,9
    int number = 1;
    for (Integer num : lol) {
      t.checkExpect(num, number);
      number = number + 1;
    }

  }
}
