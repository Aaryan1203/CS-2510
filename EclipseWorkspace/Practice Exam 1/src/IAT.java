//// 1. new switcheroo(200)
//
//import tester.Tester;
//
////represents an IAT
//interface IAT { 
//
//  //checks if any name in the tree contains the given name
//  boolean containsName(String name);
//
//  //keeps track of the given name
//  boolean containsNameHelp(String name);
//
//  //checks if anyone in this ancestry tree has the same name as one of their ancestors
//  boolean duplicateNames();
//
//  //keeps track of all the names
//  boolean duplicateNamesAcc(ILoString seen);
//}
//
////represents an unkonwn person
//class Unknown implements IAT { 
//
//  //checks if any name in the unknown case the given name
//  public boolean containsName(String name) {
//    return false;
//  }
//
//  //helps check if the name is in the unknown case
//  public boolean containsNameHelp(String name) {
//    return false;
//  }
//
//  //checks if anyone in this ancestry tree has the same name as one of their ancestors
//  public boolean duplicateNames() {
//    return false;
//  }
//
//  //keeps track of all the names
//  public boolean duplicateNamesAcc(ILoString seen) {
//    return false;
//  }
//}
//
////represents a person
//class Person implements IAT { 
//  String name;
//  IAT dad, mom;
//  Person(String name, IAT dad, IAT mom) {
//    this.name = name; 
//    this.dad = dad; 
//    this.mom = mom;
//  }
//
//  //checks if the given name is the same as any person in this tree
//  public boolean containsName(String name) {
//    return this.mom.containsNameHelp(name)
//        || this.dad.containsNameHelp(name)
//        || this.mom.containsName(name)
//        || this.dad.containsName(name);
//  }
//
//  //checks if the given name is the same as any person in this tree
//  public boolean containsNameHelp(String name) {
//    return this.name.equals(name);
//
//  }
//
//  //checks if anyone in the tree has the same name
//  public boolean duplicateNames() {
//    return this.duplicateNamesAcc(new MtLoString());
//  }
//
//  //keeps track of all the names
//  public boolean duplicateNamesAcc(ILoString seen) {
//    return seen.contains(this.name)
//        || this.dad.duplicateNamesAcc(new ConsLoString(this.name, seen))
//        || this.mom.duplicateNamesAcc(new ConsLoString(this.name, seen));
//  } 
//}
//
//
////represents a list of strings
//interface ILoString {
//  boolean contains(String s);
//}
//
////represents and empty list of strings
//class MtLoString implements ILoString {
//
//  public boolean contains(String s) {
//    return false;
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
//  public boolean contains(String s) {
//    return this.first.equals(s)
//        || this.rest.contains(s);
//  }
//
//}
//
//
////examples of IAT's
//class ExmaplesIAT {
//  IAT davisSr = new Person("Davis", new Unknown(), new Unknown()); 
//  IAT edna = new Person("Edna", new Unknown(), new Unknown());
//  IAT davisJr = new Person("Davis", davisSr, edna);
//  IAT carl = new Person("Carl", new Unknown(), new Unknown());
//  IAT candace = new Person("Candace", davisJr, new Unknown());
//  IAT claire = new Person("Claire", new Unknown(), new Unknown());
//  IAT bill = new Person("Bill", carl, candace);
//  IAT bree = new Person("Bree", new Unknown(), claire);
//  IAT anthony = new Person("Anthony", bill, bree);
//
//  //tests the containsName method
//  boolean testContainsName(Tester t) {
//    return t.checkExpect(this.davisJr.containsName("Edna"), true)
//        && t.checkExpect(this.davisJr.containsName("Bob"), false)
//        && t.checkExpect(this.candace.containsName("Davis"), true)
//        && t.checkExpect(this.davisJr.containsName("bill"), false);
//  }
//
//  //tests the duplicateNames method
//  boolean testDuplicateNames(Tester t) {
//    return t.checkExpect(this.davisJr.duplicateNames(), true)
//        && t.checkExpect(this.candace.duplicateNames(), true)
//        && t.checkExpect(this.davisSr.duplicateNames(), false);
//  }
//}