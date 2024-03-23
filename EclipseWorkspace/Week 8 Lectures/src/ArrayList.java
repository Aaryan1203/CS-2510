import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import tester.Tester;

class ArrayUtils {
  //Effect: the items at index1 and index2 are swapped
  <T> void swap(ArrayList<T> alist, int index1, int index2) {
    //    T oldValueIndex2 = alist.get(index2);
    //    
    //    alist.set(index2, alist.get(index1));
    //    alist.set(index1, oldValueIndex2);

    alist.set(index2, alist.set(index1, alist.get(index2)));
  }

  //map a function onto every member of the list and return a list of the results
  <T, U> ArrayList<U> map(ArrayList<T> alist, Function<T, U> fun) {
    return this.mapHelp(alist, fun, 0, new ArrayList<U>());
  }

  //map a function onto every member of the list and return a list of the results
  //keeps track of the current index
  <T, U> ArrayList<U> mapHelp(ArrayList<T> alist, Function<T, U> fun, int curIndex, ArrayList<U> result) {
    if(curIndex >= alist.size()) {
      return result;
    }
    else { 
      result.add(fun.apply(alist.get(curIndex)));
      return this.mapHelp(alist, fun, curIndex + 1, result);
    }
  }

  //map a function onto every member of the list and return a list of the results
  <T, U> ArrayList<U> map2(ArrayList<T> alist, Function<T, U> fun) {
    ArrayList<U> result = new ArrayList<U>();

    for (T item : alist) {
      result.add(fun.apply(item));
    }

    return result;
  }

  //combines the items in the list using the given BiFunction
  <T, U> U foldl(ArrayList<T> alist, BiFunction<T, U, U> fun, U base) {
    U result = base;

    for (T t : alist) {
      result = fun.apply(t, result);
    }

    return result;
  }
}


class ExamplesArrayList {
  ArrayList<Integer> ints;
  ArrayList<String> strings;

  void initData() {
    this.ints = new ArrayList<Integer>();
    this.ints.add(1);
    this.ints.add(2);
    this.strings = new ArrayList<String>(Arrays.asList("a", "bb", "ccc"));
  }

  void testArrayList(Tester t) {
    this.initData();

    t.checkExpect(this.ints.get(0), 1);
    t.checkExpect(this.ints.get(1), 2);
    t.checkExpect(this.ints.get(0), 1);
    this.strings.add(2, "cc");
    t.checkExpect(this.strings.get(2), "cc");
  }

  void testSwap(Tester t) {
    this.initData();
    new ArrayUtils().swap(this.ints, 0, 1);
    t.checkExpect(this.ints.get(0), 2);
    t.checkExpect(this.ints.get(1), 1);
  }

}


