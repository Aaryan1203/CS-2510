import java.util.*;

import tester.Tester;


// Class that represents an iterator
class Iter<T> implements Iterator<T> {

  Iterator<T> iter;
  int startIndex;
  int countNumbers;
  int index;

  Iter(Iterator<T> iter, int startIndex, int countNumbers) {
    this.iter = iter;
    this.startIndex = startIndex;
    this.countNumbers = countNumbers;
    this.index = 0;
  }

  // Does this iterator have a next item
  public boolean hasNext() {
    return this.iter.hasNext() && this.index < this.countNumbers;
  }

  // Returns the next item in the iterator
  // Effect: increments the index
  public T next() {
    if (this.index == 0) {
      for (int i = 0; i < this.startIndex; i++) {
        this.next();
      }
    }

    this.index ++;
    return this.next();
  }

}


// Examples and tests for the iterator
class ExmaplesIterator {
  ArrayList<Integer> numbers1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
  Iterator<Integer> iteratorNums1 = new Iter<Integer>(numbers1.iterator(), 0, 2);


  ArrayList<Integer> numbers2 = new ArrayList<Integer>(Arrays.asList());
  Iterator<Integer> iteratorNums2 = new Iter<Integer>(numbers2.iterator(), 2, 0);


  ArrayList<Integer> numbers3 = new ArrayList<Integer>(Arrays.asList(1, 2));
  Iterator<Integer> iteratorNums3 = new Iter<Integer>(numbers3.iterator(), 2, 2);


  ArrayList<Integer> numbers4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
  Iterator<Integer> iteratorNums4 = new Iter<Integer>(numbers4.iterator(), 5, 1);


  void testIterator(Tester t) {
    t.checkExpect(iteratorNums1.hasNext(), true);
    t.checkExpect(iteratorNums1.next(), 1);

    t.checkExpect(iteratorNums1.hasNext(), true);
    t.checkExpect(iteratorNums1.next(), 2);

    t.checkExpect(iteratorNums1.hasNext(), false);

  }
}

// Mutate list1 to have everything that list1 and list2 have in common
class Loops {
  <T> void commons(ArrayList<T> list1, ArrayList<T> list2) {
    ArrayList<T> newList = new ArrayList<T>();

    for (int i = 0; i < list1.size(); i++) {
      T currentNum = list1.get(i);

      for (int j = 0; j < list2.size(); j++) {
        if (currentNum.equals(list2.get(j))) {
          newList.add(currentNum);
        }
      }
    }

    list1 = newList;

  }
}

class LoopsTester {
  //ArrayUtils u = new ArrayUtils();
  ArrayList<Integer> nums1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
  ArrayList<Integer> nums2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
  ArrayList<Integer> nums3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 4, 5, 6));
  ArrayList<Integer> nums4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 4, 4, 5, 6));
  ArrayList<Integer> nums5 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 10, 31));
  
  

}


// I used .equals assuming that equals was overridden in the ArrayUtils class







