//import tester.Tester;
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
//
//  //compares if the height of this building is less than the height of the other building
//  boolean compareHeight(int max) {
//    return this.height > max;
//  }
//
//  boolean sameBuilding(Building b) {
//    return b.name.equals(this.name);
//  } 
//}
//
//// Represents a list of buildings
//interface ILoBuilding {
//
//  //is the given building visible in this list of building from our viewpoint?
//  boolean buildingVisible(Building b);
//  
//  boolean buildingVisibleAccum(Building b, int max);
//}
//
////represents an empty list of buildings
//class MtLoBuilding implements ILoBuilding {
//
//  //if the building visible from an empty list of buildings
//  public boolean buildingVisible(Building b) {
//    return false;
//  }
//
//  @Override
//  public boolean buildingVisibleAccum(Building b, int max) {
//    return false;
//  }
//
//}
//
////represents a non empty list of building
//class ConsLoBuilding implements ILoBuilding {
//  Building first;
//  ILoBuilding rest;
//  ConsLoBuilding(Building first, ILoBuilding rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  //is the building visible in the non-empty list of buildings
//  public boolean buildingVisible(Building b) {
//    
//    return this.buildingVisibleAccum(b, 0);
//
//  }
//
//  @Override
//  public boolean buildingVisibleAccum(Building b, int max) {
//    if (this.first.sameBuilding(b)) {
//      return b.compareHeight(max);
//    }
//    
//    else {
//      return this.rest.buildingVisibleAccum(b, Math.max(max, this.first.height));
//    }
//  }
//}
//
//
//
//class ExamplesBuilding {
//  Building b1 = new Building("One", 1);
//  Building b2 = new Building("Two", 2);
//  Building b3 = new Building("Three", 3);
//  Building b4 = new Building("Four", 4);
//  Building b5 = new Building("Five", 5);
//
//  ILoBuilding mt = new MtLoBuilding();
//  ILoBuilding list1 = new ConsLoBuilding(b1, new ConsLoBuilding(b2, new ConsLoBuilding(b3, this.mt)));
//  ILoBuilding list2 = new ConsLoBuilding(b1, new ConsLoBuilding(b3, new ConsLoBuilding(b2, this.mt)));
//  ILoBuilding list3 = new ConsLoBuilding(b3, new ConsLoBuilding(b2, new ConsLoBuilding(b1, this.mt)));
//
//  boolean testVisible(Tester t) {
//    return t.checkExpect(this.list1.buildingVisible(b3), true)
//        && t.checkExpect(this.list2.buildingVisible(b1), true)
//        && t.checkExpect(this.list2.buildingVisible(b2), false)
//        && t.checkExpect(this.list3.buildingVisible(b3), true)
//        && t.checkExpect(this.list2.buildingVisible(b3), false)
//        && t.checkExpect(this.list3.buildingVisible(b3), false);
//  }
//}
