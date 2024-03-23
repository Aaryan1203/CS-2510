import java.util.*;

import tester.Tester;

class EveryOther<T> implements Iterator<T> {
  Iterator<T> iter;

  EveryOther(Iterator<T> iter) {
    this.iter = iter;
  }

  // Does the iterator have a next item
  public boolean hasNext() {
    return this.iter.hasNext();
  }

  // Returns the next item
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more items");
    }
    else {
      T next = this.iter.next();
      if(this.iter.hasNext()) {
         this.iter.next();
      }
      return next;
    }
  }
}

class ExamplesIterator {
  Iterator<Integer> arrayListIter = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)).iterator();
  
//  EveryOther<Integer> list = new EveryOther<Integer>(arrayList);
  ArrayList<String> ints = new ArrayList<String>(Arrays.asList("1", "2", "3"));
  EveryOther<String> everyOther = new EveryOther<String> (ints.iterator());
  String sum = "";
  void testIterator(Tester t) {
    while(everyOther.hasNext()) {
      String elem = everyOther.next();
      sum = sum + elem;
    }
    t.checkExpect(sum, "13");
  }
}
