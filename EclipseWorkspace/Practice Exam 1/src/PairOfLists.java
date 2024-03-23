//import tester.Tester;
////represents a list of strings
//interface ILoString {
//  PairOfLists unzipFirst();
//
//  PairOfLists unzipSecond();
//
//  PairOfLists unzip();
//}
//
////represents and empty list of strings
//class MtLoString implements ILoString {
//
//  public PairOfLists unzipFirst() {
//    return new PairOfLists(new MtLoString(), new MtLoString());
//  }
//
//  public PairOfLists unzipSecond() {
//    return new PairOfLists(new MtLoString(), new MtLoString());
//  }
//
//  public PairOfLists unzip() {
//    return new PairOfLists(new MtLoString(), new MtLoString());
//  }
//
//}
//
////represents a non-empty list of strings
//class ConsLoString implements ILoString {
//  String first;
//  ILoString rest;
//
//  ConsLoString(String first, ILoString rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  public PairOfLists unzipFirst() {
//    return this.rest.unzipSecond().addToFirst(this.first);
//  }
//
//  public PairOfLists unzipSecond() {
//    return this.rest.unzipFirst().addToSecond(this.first);
//  }
//
//  public PairOfLists unzip() {
//    return this.unzipFirst(); 
//  }
//
//}
//
//// Represents a pair of lists of strings
//class PairOfLists {
//  ILoString first, second;
//
//  PairOfLists(ILoString first, ILoString second) {
//    this.first = first;
//    this.second = second; 
//
//  }
//
//  // Produces a new pair of lists, with the given String added to 
//  // the front of the first list of this pair
//  PairOfLists addToFirst(String first) {
//    return new PairOfLists(new ConsLoString(first, this.first), this.second); //this.second.first, this.second.rest
//
//  }
//
//  // Produces a new pair of lists, with the given String added to 
//  // the front of the second list of this pair
//  PairOfLists addToSecond(String second) {
//    return new PairOfLists(this.first, new ConsLoString(second, this.second)); //this.first.first, this.first.rest
//
//  } 
//
//}
//
//class ExamplesPairs {
//  ILoString list1 = new ConsLoString("A", new ConsLoString("B", new ConsLoString("C", new MtLoString())));
//  ILoString list2 = new ConsLoString("D", new ConsLoString("E", new ConsLoString("F", new MtLoString())));
//  ILoString list3 = new ConsLoString("G", new ConsLoString("H", new ConsLoString("I", new MtLoString())));
//  ILoString list4 = new ConsLoString("J", new ConsLoString("K", new ConsLoString("L", new MtLoString())));
//  ILoString listAddFirst = new ConsLoString("Alphabets: ", (new ConsLoString("A", new ConsLoString("B", new ConsLoString("C", new MtLoString())))));
//  ILoString listAddSecond = new ConsLoString("Alphabets: ", (new ConsLoString("D", new ConsLoString("E", new ConsLoString("F", new MtLoString())))));
//  PairOfLists unzip = new PairOfLists(new ConsLoString("A", (new ConsLoString("C", (new MtLoString())))), new ConsLoString("B", new MtLoString()));
//  PairOfLists unzipAdd = new PairOfLists(new ConsLoString("Alphabets: ", new ConsLoString("A", (new ConsLoString("C", (new MtLoString()))))), new ConsLoString("B", new MtLoString()));
//  PairOfLists pair1 = new PairOfLists(list1, list2);
//  PairOfLists pair2 = new PairOfLists(list3, list4);
//
//  boolean testAddTo(Tester t) {
//    return t.checkExpect(this.unzip.addToFirst("Alphabets: "), this.unzipAdd);
//  }
//
//  boolean testUnzip(Tester t) {
//    return t.checkExpect(this.list1.unzip(), this.unzip);
//  }
//
//}
//
