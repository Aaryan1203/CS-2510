//import tester.Tester;
////Practice Problem 2.
////represents a list of integers
//interface ILoInt {
//  //computes the dot product of this vector and a given vector
//  int dotProduct(ILoInt that);
//  
//  int dotProductMultFirst(int origional);
//  
//  int dotProductAddRest(ILoInt rest);
//}
//
////represents an empty list of integers
//class MtLoInt implements ILoInt {
//
//  //computes the dot product of this empty vector and a given vector
//  public int dotProduct(ILoInt that) {
//    return 0;
//  }
//
//  //multiply's the first vector in each list together
//  public int dotProductMultFirst(int origional) {
//    // TODO Auto-generated method stub
//    return 0;
//  }
//
//  @Override
//  public int dotProductAddRest(ILoInt rest) {
//    return 0;
//  }
//  
//}
//
////represents a non empty list of integers
//class ConsLoInt implements ILoInt {
//  int first;
//  ILoInt rest;
//  ConsLoInt(int first, ILoInt rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//  
//  //computes the dot product of the non-empty vector and a given vector
//  public int dotProduct(ILoInt that) {
//    return that.dotProductMultFirst(this.first)
//        + that.dotProductAddRest(this.rest);
//  }
//
//  //multiply's the first item in each vector together
//  public int dotProductMultFirst(int origional) {
//    return this.first * origional;
//  }
//
//  public int dotProductAddRest(ILoInt rest) {
//    return rest.dotProductMultFirst(this.first);
//  }
//  
//}
//
//class ExamplesDotProduct {
//  ILoInt l1 = new MtLoInt();
//  ILoInt l2 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, l1)));
//  ILoInt l3 = new ConsLoInt(2, new ConsLoInt(2, l1));
//  ILoInt l4 = new ConsLoInt(3, new ConsLoInt(2, new ConsLoInt(3, l1)));
//  ILoInt l5 = new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(5, l1)));
//
//  boolean testDot(Tester t) {
//    return t.checkExpect(l2.dotProduct(l3), 6)
//        && t.checkExpect(l4.dotProduct(l3), 10)
//        && t.checkExpect(l3.dotProduct(l5), 8);
//  }
//  
//}
//
//
//
//
//
