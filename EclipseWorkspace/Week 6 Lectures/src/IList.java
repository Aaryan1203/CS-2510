
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;
//represents a generic list
interface IList<T> extends Iterable<T>{
  //filter this list by the given predicate
  IList<T> filter(Predicate<T> pred);
  //map a function onto every member of this list, producing a list of the results
  <U> IList<U> map(Function<T, U> fun);
  //combine the items in this list using the given function from right to left
  <U> U foldr(BiFunction<T, U, U> fun, U base);
  //combine the items in this list using the given function and base case from left to right
  <U> U foldl(BiFunction<T, U, U> fun, U base);
  //acceps a function for thie IList
  <R> R accept(IListVisitor<T, R> vis);
  
  //is this list a cons list?
  boolean isCons();
}
//represents an empty generic list
class MtList<T> implements IList<T> {
  //filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    return this;
  }
  //map a function onto every member of this list, producing a list of the results
  public <U> IList<U> map(Function<T, U> fun) {
    return new MtList<U>();
  }
  //combine the items in this list using the given function
  public <U> U foldr(BiFunction<T, U, U> fun, U base) {
    return base;
  }
  //combine the items in this list using the given function and base case from left to right
  public <U> U foldl(BiFunction<T, U, U> fun, U base) {
    return base;
  }
  //acceps a function for this IList
  public <R> R accept(IListVisitor<T, R> vis) {
    return vis.visitMt(this);
  }
  
  //constuct's an iterator for this empty list
  public Iterator<T> iterator() {
    return new IListIter<T> (this);
  }
  
  //is this list a cons list?
  public boolean isCons() {
    return false;
  }
  
}
//represents a non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
  //filter this list by the given predicate
  public IList<T> filter(Predicate<T> pred) {
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(null));
    }
    else {
      return this.rest.filter(null);
    }
  }
  //map a function onto every member of this list, producing a list of the results
  public <U> IList<U> map(Function<T, U> fun) {
    return new ConsList<U>(fun.apply(this.first), this.rest.map(fun));
  }
  //combine the items in this list using the given function
  public <U> U foldr(BiFunction<T, U, U> fun, U base) {
    return fun.apply(this.first, this.rest.foldr(fun, base));
  }
  //combine the items in this list using the given function and base case from left to right
  public <U> U foldl(BiFunction<T, U, U> fun, U base) {
    return this.rest.foldl(fun, fun.apply(this.first, base));
  }
  //accepts a function for this IList
  public <R> R accept(IListVisitor<T, R> vis) {
    return vis.visitCons(this);
  }
  
  //constuct's an iterator for this empty list
  public Iterator<T> iterator() {
    return new IListIter<T> (this);
  }
  
  //is this list a cons list?
  public boolean isCons() {
    return true;
  }
}


//easing the restrictions on type-checking and casting
class IListIter<T> implements Iterator<T>{
  IList<T> items;
  
  IListIter(IList<T> items) {
    this.items = items;
  }
  
  //are there any more items to produce
  public boolean hasNext() {
    return this.items.isCons();
  }

  //produce the next item
  //EFFECT: item is set to be the rest of items
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no more items!");
    }
    ConsList<T> cons = (ConsList<T>)items;
    T temp = cons.first;
    this.items = cons.rest;
    return temp;
  }
  
}

/*
 * attempting to make a function object for IShape
class ShapeToArea implements Function<IShape, Double> {
//produce the area of the given shape
public Double apply(IShape t) {
 //fields of t:
 //  none
 // methods of t: 
 //  none
 // methods for fields of t:
 //  none 

}
}
 */
class ExamplesLists {
  IList<String> mtStrings = new MtList<String>();
  IList<String> list1Strings = new ConsList<String>("a", 
      new ConsList<String>("bb", new MtList<String>()));
  IList<Integer> ints = new ConsList<Integer>(1, 
      new ConsList<Integer>(2, new MtList<Integer>()));
  boolean testMap(Tester t) {
    return t.checkExpect(this.list1Strings.map(s -> s.length()), this.ints)
        &&
        t.checkExpect(this.mtStrings.map(s -> s + "suffix"), 
            this.mtStrings); 
  }
  
  boolean testVisit(Tester t) {
    return t.checkExpect(this.list1Strings.accept(new FilterVisitor<String>(new ContainsA())), 
        new ConsList<String>("a", new MtList<String>()));
  }
  
  void testListInter(Tester t) {
    int sum = 0;
    for (Integer i : ints) {
      sum += i;
    }
    t.checkExpect(sum, 3);
  }
}
