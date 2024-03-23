import java.util.*;
import java.util.function.BiFunction;

import tester.Tester;

class ScanLeftIterator<T, U> implements Iterator<T>{
  Iterator<U> iter;
  T base;
  BiFunction<U, T, T> func;

  ScanLeftIterator(Iterator<U> iter, BiFunction<U, T, T> func, T base) {
    this.iter = iter;
    this.base = base;
    this.func = func;
  }

  //Does this have a next
  public boolean hasNext() {
    return this.iter.hasNext();
  }

  // What is the next
  // Effect: Advances iter by callling next, update the base
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more items");
    }

    this.base = func.apply(this.iter.next(), base);
    return this.base;
  }

}

class ExamplesScanLeft {
  ArrayList<String> strings = new ArrayList<String>(Arrays.asList("a", "bb", "ccc"));
  ScanLeftIterator<Integer, String> stringToInt = new ScanLeftIterator<Integer, String>(strings.iterator(), (s, n) -> s.length() + n, 0);
  void testScan(Tester t) {
    t.checkExpect(stringToInt.hasNext(), true);
    t.checkExpect(stringToInt.next(), 1);
    t.checkExpect(stringToInt.hasNext(), true);
    t.checkExpect(stringToInt.next(), 3);
    t.checkExpect(stringToInt.hasNext(), true);
    t.checkExpect(stringToInt.next(), 6);
    t.checkExpect(stringToInt.hasNext(), false);
  }
}





// Effect: remove the items in the given list from the index's of A to B inclusively
class Remove<T> {
  void removeBetween(ArrayList<T> list, int a, int b) {
    for (int i = b; i >= a; i--) {
      list.remove(i);
    }
  }
}

class ExamplesRemove {
  Remove<Integer> remove = new Remove<Integer>();
  ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
  void testRemove(Tester t) {
    remove.removeBetween(ints, 2, 4);
    t.checkExpect(ints, new ArrayList<Integer>(Arrays.asList(1, 2, 6, 7)));
  }
}





