////Practice Problem 3.
////represents an ancestor tree
//interface IAT {
// boolean sameGen(String name1, String name2);
// 
// boolean helper(String name1, String name2);
//}
////represents an unknown ancestor tree
//class Unknown implements IAT {
//
//  public boolean sameGen(String name1, String name2) {
//    return false;
//  }
//
//  public boolean helper(String name1, String name2) {
//    return false;
//  }
//  
//}
//
////represents a known ancestor tree
//class Person implements IAT {
//  String name;
//  IAT parent1;
//  IAT parent2;
//  Person(String name, IAT parent1, IAT parent2) {
//    this.name = name;
//    this.parent1 = parent1;
//    this.parent2 = parent2;
//  }
//
//  public boolean sameGen(String name1, String name2) {
//    return this.parent1.helper(name1, name2)
//        && this.parent2.helper(name1, name2);
//  }
//
//  public boolean helper(String name1, String name2) {
//    this.name.equals(name1);
//  }
//}
//
//class ExamplesReview {
//  IAT enzo = new Person("Enzo", new Unknown(), new Unknown()); 
//  IAT edna = new Person("Edna", new Unknown(), new Unknown());
//  IAT davis = new Person("Davis", enzo, edna);
//  IAT carl = new Person("Carl", new Unknown(), new Unknown());
//  IAT candace = new Person("Candace", davis, new Unknown());
//  IAT claire = new Person("Claire", new Unknown(), new Unknown());
//  IAT bill = new Person("Bill", carl, candace);
//  IAT bree = new Person("Bree", new Unknown(), claire);
//  IAT anthony = new Person("Anthony", bill, bree);
//}