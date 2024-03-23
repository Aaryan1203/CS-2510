import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import tester.Tester;


interface ILoString {

}

interface IList<T> {
  //filters a list of T by the given predicate
  IList<T> filter(Predicate<T> pred);

  //applys a function on every item in the list
  <U> IList<U> map(Function<T,U> converter);

  //performs the fold method and returns a U
  <U> U fold(BiFunction<T,U,U> converter,U initial);  
}

//returns a list of filtered strings with the given predicate
class CompareToLetter implements Predicate<String> {
  String letter;

  public CompareToLetter(String letter) {
    this.letter = letter;
  }

  /*
   * Fields:
   *  this.letter ... String
   * Methods:
   *  this.test(String) ... boolean
   * Methods of Fields:
   *  None
   */

  //checks if item in the list starts with the given letter
  public boolean test(String month) {
    return month.startsWith(letter);
  }

}

//counts the number of months with the specific condition
class MonthsEndWith implements BiFunction<String, Integer, Integer> {
  String suffix;

  MonthsEndWith(String suffix) {
    this.suffix = suffix;
  }

  /*
   * Fields:
   *  this.suffix ... String
   * Methods:
   *  this.apply(String, Integer) ... Integer
   * Methods of Fields:
   *  None
   */

  //returns the number of months that have the specific 
  public Integer apply(String t, Integer u) {
    if (t.endsWith(suffix)) {
      return u += 1;
    }
    else {
      return u;
    }
  }

}

//abbreviates all of the strings in the list by the given amount
class Abbreviate implements Function<String, String> {
  int abb;

  Abbreviate(int abb) {
    this.abb = abb;
  }

  /*
   * Fields:
   *  this.abb ... int
   * Methods:
   *  this.apply(String) ... String
   * Methods of Fields:
   *  None
   */

  //returns a new string with the given predicate
  public String apply(String t) {
    return t.substring(0, abb);
  }

}

class MtList<T> implements IList<T> {

  MtList() {}

  /*
   * Fields:
   * 
   * Methods:
   *  this.filter(Predicate<T>) ... IList<T>
   *  this.map(Function<T, U>) ... IList<U>
   *  this.folt(BiFunction<T, U, U>, U) ... U
   * Methods of Fields:
   * 
   */

  //filters a empty list
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    return new MtList<T>();
  }

  //performs a function on every element in the empty list
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new MtList<U>();
  }

  //performs the fold function on every element in the empty list
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return initial;
  }
}


class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first,IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }


  /*
   * Fields:
   *  this.first ... T
   *  this.rest ... IList<T>
   * Methods:
   *  this.filter(Predicate<T>) ... IList<T>
   *  this.map(Function<T, U>) ... IList<U>
   *  this.fold(BiFunction<T, U, U>, U) ... U
   * Methods of Fields:
   *  this.rest.filter(Predicate<T>) ... IList<T>
   *  this.rest.map(Function<T, U>) ... IList<U>
   *  this.rest.fold(BiFunction<T, U, U>, U) ... U
   */

  //filters an non-empty list of gien strings
  public IList<T> filter(Predicate<T> pred) {
    // TODO Auto-generated method stub
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first,this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //performs a function to every item in the list on the non-empty list
  public <U> IList<U> map(Function<T, U> converter) {
    // TODO Auto-generated method stub
    return new ConsList<U>(converter.apply(this.first),this.rest.map(converter));
  }

  //performs the fold function on every element in the non-empty list
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    // TODO Auto-generated method stub
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }
}



class ExamplesLists {
  ExamplesLists() {}

  IList<String> months = new ConsList<String>("Janruary", 
      new ConsList<String>("February", 
          new ConsList<String>("March",
              new ConsList<String>("April", 
                  new ConsList<String>("May",
                      new ConsList<String>("June", 
                          new ConsList<String>("July",
                              new ConsList<String>("August", 
                                  new ConsList<String>("September",
                                      new ConsList<String>("October", 
                                          new ConsList<String>("November",
                                              new ConsList<String>("December", 
                                                  new MtList<String>()))))))))))));

  IList<String> monthsAbb = new ConsList<String>("Jan", 
      new ConsList<String>("Feb", 
          new ConsList<String>("Mar",
              new ConsList<String>("Apr", 
                  new ConsList<String>("May",
                      new ConsList<String>("Jun", 
                          new ConsList<String>("Jul",
                              new ConsList<String>("Aug", 
                                  new ConsList<String>("Sep",
                                      new ConsList<String>("Oct", 
                                          new ConsList<String>("Nov",
                                              new ConsList<String>("Dec", 
                                                  new MtList<String>()))))))))))));

  IList<String> monthsJ = new ConsList<String>("Janruary",
      new ConsList<String>("June",
          new ConsList<String>("July",
              new MtList<String>())));




  boolean testLists(Tester t) {
    return t.checkExpect(this.months.filter(new CompareToLetter("J")), this.monthsJ)
        && t.checkExpect(this.months.fold(new MonthsEndWith("er"), 0), 4)
        && t.checkExpect(this.months.map(new Abbreviate(3)), this.monthsAbb);

  }

}






