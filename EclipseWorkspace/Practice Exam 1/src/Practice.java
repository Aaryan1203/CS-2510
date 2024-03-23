//
////Practice Problem 1.
//// Represents a single building in Boston
//class Building {
//  String name;
//  int height;
//  Building(String name, int height) {
//    this.name = name;
//    this.height = height;
//  }
//  //is this building the same as the given one?
//  boolean sameBuilding(Building b) {
//    return b.name.equals(this.name);
//  } 
//  //is this building's height > the given height?
//  boolean tallerThan(int height) {
//    return this.height > height;
//  }
//  //find the height of the taller building
//  int tallerHeight(int height) {
//    return Math.max(this.height, height);
//  }
//  //is this building's height > the given height?
//  Building tallerBuilding(Building b) {
//    if (this.tallerThan(b.height)) {
//      return this;
//    }
//    else {
//      return b;
//    }
//  }
//}
//// Represents a list of buildings
//interface ILoBuilding {
//  //is the given building visible in this list from the front of this list
//  boolean buildingVisible(Building b);
//  //is the given building visible in this list from the front of this list (using an accumulator)
//  boolean buildingVisible2(Building b);
//  //accumulator: keeps track of the height of the tallest building so far
//  boolean buildingVisibleAcc(Building b, int maxSoFar);
//}
////represents an empty list of buildings
//class MtLoBuilding implements ILoBuilding {
//  //is the given building visible in this list from the front of this list
//  public boolean buildingVisible(Building b) {
//    return false;
//  }
//  //is the given building visible in this list from the front of this list (using an accumulator)
//  public boolean buildingVisible2(Building b) {
//    return false;
//  }
//  //accumulator: keeps track of the height of the tallest building so far
//  public boolean buildingVisibleAcc(Building b, int maxSoFar) {
//    return false;
//  }
//}
////represents a non empty list of building
//class ConsLoBuilding implements ILoBuilding {
//  Building first;
//  ILoBuilding rest;
//  ConsLoBuilding(Building first, ILoBuilding rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//  
//  @Override
//  public boolean buildingVisible(Building b) {
//    return this.first.sameBuilding(b) ||
//        (b.sameBuilding(b.tallerBuilding(this.first)) && 
//            this.rest.buildingVisible(b));
//  }
//  //is the given building visible in this list from the front of this list  (using an accumulator)
//  public boolean buildingVisible2(Building b) {
//    return this.buildingVisibleAcc(b, 0);
//  }
//  //accumulator: keeps track of the height of the tallest building so far
//  public boolean buildingVisibleAcc(Building b, int maxSoFar) {
//    if (this.first.sameBuilding(b)) {
//      return b.tallerThan(maxSoFar);
//    }
//    else {
//      return this.rest.buildingVisibleAcc(b, 
//          this.first.tallerHeight(maxSoFar));
//    }
//  }
//}
////Practice Problem 2.
////represents a list of integers
//interface ILoInt {
//  //compute the dot product of this list and the given one
//  int dotProduct(ILoInt that);
//  //helps compute the dot product by passing in the first and rest of a cons list if this is a conslist
//  int dpHelp(int f, ILoInt r);
//}
////represents an empty list of integers
//class MtLoInt implements ILoInt {
//  //compute the dot product of this list and the given one
//  public int dotProduct(ILoInt that) {
//    return 0;
//  }
//  //helps compute the dot product. Result is 0 because this empty list has no numbers
//  public int dpHelp(int f, ILoInt r) {
//    return 0;
//  }
//}
////represents a non empty list of integers
//class ConsLoInt implements ILoInt {
//  int first;
//  ILoInt rest;
//  ConsLoInt(int first, ILoInt rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//  //compute the dot product of this list and the given one
//  public int dotProduct(ILoInt that) {
//    return that.dpHelp(this.first, this.rest);
//  }
//  //helps compute the dot product by passing in the first and rest of a cons list if this is a conslist
//  public int dpHelp(int f, ILoInt r) {
//    return this.first * f + this.rest.dotProduct(r);
//  }
//}
////Practice Problem 3.
////represents an ancestor tree
//interface IAT {
//  //are the given names for persons in this IAT that are in the same generation?
//      boolean sameGen(String name1, String name2);
//  //compute the generation of the person with the given name in this IAT
//  //return -1 is there is no person with the given name
//  int generation(String name);
//  //accumulator: keeps track of the number of generations counted so far
//  int genAcc(String name, int genSoFar);
//}
////represents an unknown ancestor tree
//class Unknown implements IAT {
//  //are the given names for persons in this IAT that are in the same generation?
//      public boolean sameGen(String name1, String name2) {
//    return false;
//  }
//  //compute the generation of the person with the given name in this IAT
//  //return -1 is there is no person with the given name
//  public int generation(String name) {
//    return -1;
//  }
//  //accumulator: keeps track of the number of generations counted so far
//  public int genAcc(String name, int genSoFar) {
//    return -1;
//  }
//}
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
//  //are the given names for persons in this IAT that are in the same generation?
//      public boolean sameGen(String name1, String name2) {
//    return this.generation(name1) == this.generation(name2);
//  }
//  //compute the generation of the person with the given name in this IAT
//  //return -1 is there is no person with the given name
//  public int generation(String name) {
//    return this.genAcc(name, 0);
//  }
//  //accumulator: keeps track of the number of generations counted so far
//  public int genAcc(String name, int genSoFar) {
//    if (this.name.equals(name)) {
//      return genSoFar + 1;
//    }
//    else {
//      return Math.max(this.parent1.genAcc(name, genSoFar + 1), 
//          this.parent2.genAcc(name, genSoFar + 1));
//    }
//  }
//}