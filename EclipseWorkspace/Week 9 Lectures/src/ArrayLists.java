import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import tester.Tester;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayUtils {
  
  //EFFECT: swaps the items in the list at the given positions
  <T> void swap(ArrayList<T> alist, int index1, int index2) {
    //T oldItemAtIndex2 = alist.get(index2);
    //alist.set(index2, alist.get(index1));
    //alist.set(index1, oldItemAtIndex2);
    alist.set(index2, alist.set(index1, alist.get(index2)));
  }
  //map a function onto every member of the list, returning a list of the results
  <T, U> ArrayList<U> map(ArrayList<T> alist, Function<T, U> fun) {
    return this.mapHelp(alist, fun, 0, new ArrayList<U>());
  }
  //map a function onto every member of the list, returning a list of the results
  //keeps track of the current index
  <T, U> ArrayList<U> mapHelp(ArrayList<T> alist, Function<T, U> fun, int
      currIndex, ArrayList<U> result) {
    if (currIndex >= alist.size()) {
      return result;
    }
    else {
      result.add(fun.apply(alist.get(currIndex)));
      return this.mapHelp(alist, fun, currIndex + 1, result);
    }
  }
  //map a function onto every member of the list, returning a list of the results
  <T, U> ArrayList<U> map2(ArrayList<T> alist, Function<T, U> fun) {
    ArrayList<U> result = new ArrayList<U>();
    for (T t : alist) {
      result.add(fun.apply(t));
    }
    return result;
  }
  //combines the items in the list using the given function
  <T, U> U foldl(ArrayList<T> alist, BiFunction<T, U, U> fun, U base) {
    U result = base;
    for (T item : alist) {
      result = fun.apply(item, result);
    }
    return result;
  }
  //finds the index of the given item in the list, or returns -1 if not in the list
  <T> int find(ArrayList<T> alist, Comparator<T> comp, T target) {
    return this.findHelp(alist, comp, target, 0);
  }
  //finds the index of the given item in the list, or returns -1 if not in the list
  <T> int findHelp(ArrayList<T> alist, Comparator<T> comp, T target, int
      current) {
    if (current >= alist.size()) {
      return -1;
    }
    else if (comp.compare(target, alist.get(current)) == 0) {
      return current;
    }
    else {
      return this.findHelp(alist, comp, target, current + 1);
    }
  }
  //finds the index of the given item in the sorted list, or returns -1 if not in the list
  <T> int binarySearch(ArrayList<T> alist, Comparator<T> comp, T target) {
    return this.binaryHelp(alist, comp, target, 0, alist.size() - 1);
  }
  //finds the index of the given item in the sorted list, or returns -1 if not in the list
  <T> int binaryHelp(ArrayList<T> alist, Comparator<T> comp, T target, int lo,
      int hi) {
    int mid = (lo + hi)/2;
    if (lo > hi) {
      return -1;
    }
    if (comp.compare(alist.get(mid), target) == 0) {
      return mid;
    }
    else if (comp.compare(alist.get(mid), target) > 0) {
      return this.binaryHelp(alist, comp, target, lo, mid - 1);
    }
    else {
      return this.binaryHelp(alist, comp, target, mid + 1, hi);
    }
  }

  //finds the index of the given item in the sorted list, or returns -1 if not in the list
  <T> int binarySearchWhile(ArrayList<T> alist, Comparator<T> comp, T target) {
    int low = 0;
    int high = alist.size() - 1;
    while (high >= low) {
      int mid = (low + high) / 2;

      if (comp.compare(alist.get(mid), target) == 0) {
        return mid;
      }

      else if (comp.compare(alist.get(mid), target) > 0) {
        high = mid - 1;
      }

      else {
        low = mid + 1;
      }
    }

    return -1;
  }
  //find the index of the min item in the given list, or -1 if list is empty
  int findIndexOfMin(ArrayList<String> alist) {
    if (alist.size() == 0) { return -1;}

    int minIndex = 0;

    for (int i = 0; i < alist.size(); i = i + 1) {
      if (alist.get(minIndex).compareTo(alist.get(i)) > 0) {
        minIndex = i;
      }
    }

    return minIndex;
  }

  //make a deck of 52 cards
  ArrayList<Card> makeDeck() {
    ArrayList<String> suits = new ArrayList<String>(Arrays.asList("hearts",
        "diamonds", "spades", "clubs"));
    ArrayList<String> values = new ArrayList<String>(Arrays.asList("ace",
        "two", "three", "four",
        "five", "six", "seven", "eight", "nine", "ten", "j", "q",
        "k"));

    ArrayList<Card> cards = new ArrayList<Card>();
    for (int i = 0; i < suits.size(); i = i + 1) {
      for (int j = 0; j < values.size(); j = j + 1) {
        cards.add(new Card(suits.get(i), values.get(j)));
      }
    }

    return cards;
  }

  //build a list by applying a given function to natural numbers from 0 to n -1
  <U> ArrayList<U> buildList(Function<Integer, U> fun, int n) {
    ArrayList<U> result = new ArrayList<U>();

    for (int i = 0; i < n; i++) {
      result.add(fun.apply(i));
    }

    return result;
  }
}

class TakeN<T> implements Iterator<T> {
  Iterator<T> iter;
  int numItems;
  
  TakeN(Iterator<T> iter, int numItems) {
    this.iter = iter;
    this.numItems = numItems;
  }
  
  
  //are there any more items to produce?
  public boolean hasNext() {
    return numItems > 0 && this.iter.hasNext();
  }

  //get the nezt item
  //Effect: num items is decreased by 1
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more items");
    }
    else {
      numItems--;
      return this.iter.next();
    }
  }
  
}

class ExamplesArrayList {
  ArrayList<Integer> ints;
  ArrayList<String> strings;
  ArrayList<Posn> points;
  Posn p1;
  Posn p2;
  Posn p3;
  
  Evens evenIter = new Evens();
  TakeN<Integer> takeNIter = new TakeN<Integer>(evenIter, 5);
  ArrayList<String> suits = new ArrayList<String>(Arrays.asList("hearts",
      "diamonds", "spades", "clubs"));
  TakeN<String> takeNStrings = new TakeN<String>(this.suits.iterator(), 2);
  
  void testIter(Tester t) {
    t.checkExpect(this.takeNIter.hasNext(), true);
    t.checkExpect(this.takeNIter.next(), 0);
    t.checkExpect(this.takeNIter.hasNext(), true);
    t.checkExpect(this.takeNIter.next(), 2);
    t.checkExpect(this.takeNIter.hasNext(), true);
    t.checkExpect(this.takeNIter.next(), 4);
    t.checkExpect(this.takeNIter.hasNext(), true);
    t.checkExpect(this.takeNIter.next(), 6);
    t.checkExpect(this.takeNIter.hasNext(), true);
    t.checkExpect(this.takeNIter.next(), 8);
    t.checkExpect(this.takeNIter.hasNext(), false);
  }
  
  void initData() {
    this.ints = new ArrayList<Integer>();
    this.ints.add(10);
    this.ints.add(20);
    this.ints.add(30);

    this.strings = new ArrayList<String>(Arrays.asList("a", "bb", "ccc"));

    this.p1 = new Posn(3,4);
    this.p2 = new Posn(6,8);
    this.p3 = new Posn(0,0);

    this.points = new ArrayList<Posn>(Arrays.asList(this.p1, this.p2,
        this.p3));
  }

  void testPosnShift(Tester t) {
    this.initData();
    for (Posn p : this.points) {
      p.shift(0, 1);
    }

    t.checkExpect(p1,  new Posn(3, 5));
    t.checkExpect(p2,  new Posn(6, 9));

  }


  void testArrayList(Tester t) {
    this.initData();
    t.checkExpect(this.ints.get(0), 10);
    t.checkExpect(this.ints.get(2), 30);
    //t.checkExpect(this.ints.get(3), 10);
    this.strings.add(1, "b");
    t.checkExpect(this.strings.get(1), "b");
  }
  void testSwap(Tester t) {
    this.initData();
    t.checkExpect(this.ints.get(0), 10);
    t.checkExpect(this.ints.get(2), 30);
    new ArrayUtils().swap(this.ints, 0, 2);
    t.checkExpect(this.ints.get(0), 30);
    t.checkExpect(this.ints.get(2), 10);
  }
  void testFind(Tester t) {
    this.initData();
    
    
  }
}


class Card {
  String suit;
  String value;
  Card(String s, String v) {
    this.suit = s;
    this.value = v;
  }
}
class Posn {
  int x;
  int y;
  Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }
  //EFFCT: shifts this posn's x and y by the given increments
  void shift(int dx, int dy) {
    this.x = this.x + dx;
    this.y = this.y + dy;
  }
  public String toString() {
    return "[" + this.x + ", " + this.y + "]";
  }
}
