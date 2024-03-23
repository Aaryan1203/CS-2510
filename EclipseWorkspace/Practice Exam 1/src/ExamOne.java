////
//
////uses this non-empty list of strings and another non-empty lit of strings to create a list of partners
//  ILoPartners makePairs(ILoString that) {
//    return that.makePairsHelper(this.first, this.rest)
//}
//  //helps create a list of partners by switching the lists
//  ILoPartners makePairsHelper(String first, ILoString that) {
//    return new ConsLoPartner(new Partner(first, this.first), that.makePairs(this.rest);
//  }
//  
//  
//boolean hasNames(String name1, String name2) {
//  (this.parent1.hasNamesHelper(name1)
//  || this.parent2.hasNamesHelper(name1))
//  && 
//  (this.parent1.hasNamesHelper(name2)
//  || this.parent1.hasNameHelper(name2));
//}

//
//boolean hasNamesHelper(String name) {
//  this.name.equals(name)
//  || this.parent1.hasNamesHelper(name)
//  || this.parent2.hasNamesHelper(name);
//}
//
//(circle) circ ...
//
//
//this.circle.radius
//circleHuh
//"isCircle"