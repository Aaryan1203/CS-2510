import tester.Tester;
//represents an ancestor tree

interface IAT {
  //is this IAT well formed
  boolean wellFormed();
  //helps check if this IAT is well formed
  //accumulator: keeps track of the child's yob
  boolean wellFormedHelp(int childYob);
  //list all of the names of the persons in thie IAT
  ILoString names();
  //helps list the names of this person in this IAT
  //accumulator: keeps track of all the names
  ILoString namesAcc(ILoString acc);

  //return the IAT that is younger between this one and a given one
  IAT youngerIAT(IAT that);

  //youngerIAT help by passing along the year of birth of the original IAT
  IAT youngerHelp(int origionalYob, IAT origional);
  //produce the younger of this IAT's parents
  
  IAT youngerParent();
  //produce the younger of this IAT's grandparents
  IAT youngerGrandParent();
  //finds the youngest IAT in the given generation
  IAT youngestInGeneration(int gen);
  //is there a person in this IAT that has the same name as an ancestor?
  boolean repeatAncestorName();
  //is there a person in this IAT that has the same name as an ancestor?
  //accumulator: keeps track of the name sseen so far
  boolean repeatAncestorName(ILoString acc);

}

class Unknown implements IAT {

  /* fields: none
   * methods:
   *   this.names() ... ILoString
   *   this.namesAcc(ILoString) ... ILoString
   *   this.wellFormed() ... boolean
   * methods for fields: none
   */

  //is this IAT well-formed?
  public boolean wellFormed() {
    return true;
  }

  //helps check if this IAT is well formed
  //accumulator: keeps track of the child's yob
  public boolean wellFormedHelp(int childYob) {
    return true;
  }

  @Override
  public ILoString names() {
    return new MtLoString();
  }

  @Override
  public ILoString namesAcc(ILoString acc) {
    return acc;
  }

  @Override
  public IAT youngerIAT(IAT that) {
    return that;
  }

  @Override
  public IAT youngerHelp(int origionalYob, IAT origional) {
    return origional;
  }

  @Override
  public IAT youngerParent() {
    return this;
  }

  @Override
  public IAT youngerGrandParent() {
    return this;
  }

  @Override
  public IAT youngestInGeneration(int gen) {
    return this;
  }

  @Override
  public boolean repeatAncestorName() {
    return false;
  }

  @Override
  public boolean repeatAncestorName(ILoString acc) {
    return false;
  }
}

//represents a person in an ancestor tree
class Person implements IAT {
  String name;
  int yob;
  IAT parent1;
  IAT parent2;

  Person(String name, int yob, IAT p1, IAT p2) {
    this.name = name;
    this.yob = yob;
    this.parent1 = p1;
    this.parent2 = p2;
  }

  /* fields: 
   *   this.name ... String
   *   this.yob ... int
   *   this.parent1 ... IAT
   *   this.parent2 ... IAT
   * methods:
   *   this.names() ... ILoString
   *   this.namesAcc(ILoString) ... ILoString
   *   this.wellFormed() ... boolean
   * methods for fields:
   *   this.parent1.names() ... ILoString
   *   this.parent2.names() ... ILoString
   *   this.parent1.namesAcc(ILoString) ... ILoString
   *   this.parent2.namesAcc(ILoString) ... ILoString
   *   this.parent1.wellFormed() ... boolean
   *   this.parent2.wellFormed() ... boolean
   */

  //determines if this IAT is wellFormed
  public boolean wellFormed() {
    return this.parent1.wellFormedHelp(this.yob)
        && this.parent2.wellFormedHelp(this.yob)
        && this.parent1.wellFormed()
        && this.parent2.wellFormed();
  }

  //helps check if this Person is well formed
  //accumulator: keeps track of the child's yob
  public boolean wellFormedHelp(int childYob) {
    return this.yob < childYob;
  }

  @Override
  public ILoString names() {
    return this.namesAcc(new MtLoString());
  }

  @Override
  public ILoString namesAcc(ILoString acc) {
    return new ConsLoString(this.name,
        this.parent1.namesAcc(this.parent2.namesAcc(acc)));
  }

  @Override
  public IAT youngerIAT(IAT that) {
    /* fields of that : none
     * methods of that: all the methods listed in the template above
     * methods for feidlds : none
     */

    return that.youngerHelp(this.yob, this);
  }

  @Override
  public IAT youngerHelp(int origionalYob, IAT origional) {
    if(origionalYob > this.yob) {
      return origional;
    }
    else {
      return this;
    }
  }

  @Override
  public IAT youngerParent() {
    return this.parent1.youngerIAT(this.parent2);
  }

  @Override
  public IAT youngerGrandParent() {
    return this.parent1.youngerParent().youngerIAT(this.parent2.youngerParent());
  }

  @Override
  public IAT youngestInGeneration(int gen) {
    if (gen == 0) {
      return this;
    }
    else {
      return this.parent1.youngestInGeneration(gen - 1).youngerIAT(this.parent2.youngestInGeneration(gen - 1));
    }
  }

  @Override
  public boolean repeatAncestorName() {
    return this.repeatAncestorName(new MtLoString());
  }

  @Override
  public boolean repeatAncestorName(ILoString acc) {
    return false;
    //is this persons name in acc? 
    //if so return true
    //else, recur on parents 1 and parent 2, cons this person's name to acc in both parents
  }
}

//represents a list of strings
interface ILoString {
  //reverse the order of the strings in this list
  ILoString reverse();
  //add the string to the end of this list
  ILoString addAsLast(String add);

  //reverse the order of the strings in this list using an accumulator
  ILoString reverse2();
  //reverse the order of the strings in this list
  //accumulator: keeps track of the reversed list so far
  ILoString reverseAcc(ILoString acc);
  //appends the given list to the end of this list
  ILoString append(ILoString other);


}

//represents a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  /* fields:
   *  this.first ... String
   *  this.rest ... ILoString
   * methods:
   *  this.reverse() ... ILoString
   *  this.addAsLast(String) ... ILoString
   *  this.reverse2() ... ILoString
   *  this.reverseAcc(ILoString) ... ILoString
   * methods for fields:
   *  this.rest.reverse() ... ILoString
   *  this.rest.addAsLast(String) ... ILoString  
   *  this.rest.reverse2() ... ILoString
   *  this.rest.reverseAcc(ILoString) ... ILoString
   * 
   */

  //reverses this list of strings
  public ILoString reverse() {
    return this.rest.reverse().addAsLast(this.first) ;
  }

  //adds the given string to the end of this list
  public ILoString addAsLast(String add) {
    return new ConsLoString(this.first, this.rest.addAsLast(add));
  }

  @Override
  public ILoString reverse2() {
    return this.reverseAcc(new MtLoString ());
  }

  @Override
  public ILoString reverseAcc(ILoString acc) {
    return this.rest.reverseAcc(new ConsLoString(this.first, acc));
  }

  //put the given list at the end of this list
  public ILoString append(ILoString other) {
    return new ConsLoString(this.first, this.rest.append(other));
  }


}

//represents an empty list of strings
class MtLoString implements ILoString {
  MtLoString() { }
  /* fields:none
   * methods:
   *  this.reverse() ... ILoString
   *  this.addAsLast(String) ... ILoString
   *  this.reverse2() ... ILoString
   *  this.reverseAcc(ILoString) ... ILoString
   * methods for fields:none
   * 
   */
  //returns this list as its reverse
  public ILoString reverse() {
    return this;
  }

  //put the given string at the end of this list
  public ILoString addAsLast(String add) {
    return new ConsLoString(add, this);
  }
  //reverse the order of the strings in this list
  public ILoString reverse2() {
    return this;
  }

  //accumulator: keeps track of the reversed list so far
  public ILoString reverseAcc(ILoString acc) {
    return acc;
  }

  @Override
  public ILoString append(ILoString other) {

    /* fields of other: None
     * methods of other:
     *  other.reverse() ... ILoString
     * 
     * 
     */

    return other;
  }
}

class Examples {
  IAT enid = new Person("Enid", 1904, new Unknown(), new Unknown());
  IAT edward = new Person("Edward", 1902, new Unknown(), new Unknown());
  IAT emma = new Person("Emma", 1906, new Unknown(), new Unknown());
  IAT eustace = new Person("Eustace", 1907, new Unknown(), new Unknown());
  IAT david = new Person("David", 1925, new Unknown(), this.edward);
  IAT daisy = new Person("Daisy", 1927, new Unknown(), new Unknown());
  IAT dana = new Person("Dana", 1933, new Unknown(), new Unknown());
  IAT darcy = new Person("Darcy", 1930, this.emma, this.eustace);
  IAT darren = new Person("Darren", 1935, this.enid, new Unknown());
  IAT dixon = new Person("Dixon", 1936, new Unknown(), new Unknown());
  IAT clyde = new Person("Clyde", 1955, this.daisy, this.david);
  IAT candace = new Person("Candace", 1960, this.dana, this.darren);
  IAT cameron = new Person("Cameron", 1959, new Unknown(), this.dixon);
  IAT claire = new Person("Claire", 1956, this.darcy, new Unknown());
  IAT bill = new Person("Bill", 1980, this.candace, this.clyde);
  IAT bree = new Person("Bree", 1981, this.claire, this.cameron);
  IAT andrew = new Person("Andrew", 2001, this.bree, this.bill);
  ILoString mt = new MtLoString();
  ILoString list1 = new ConsLoString("hello", this.mt);
  ILoString list2 = new ConsLoString("how", this.list1);
  ILoString list3 = new ConsLoString("are", this.list2);
  ILoString list4 = new ConsLoString("you", this.list3);
  ILoString list1rev = new ConsLoString("you", this.mt);
  ILoString list2rev = new ConsLoString("are", this.list1rev);
  ILoString list3rev = new ConsLoString("how", this.list2rev);
  ILoString list4rev = new ConsLoString("hello", this.list3rev);
  boolean testReverse(Tester t) {
    return t.checkExpect(this.list4.reverse(), this.list4rev) &&
        t.checkExpect(this.mt.reverse(), this.mt) &&
        t.checkExpect(this.mt.addAsLast("hello"), this.list1) &&
        t.checkExpect(this.list1.addAsLast("world"), 
            new ConsLoString("hello", new 
                ConsLoString("world", this.mt)));

    //add more tests
  }

  boolean testCountAnc(Tester t) {
    return true;
  }

  boolean testWellFormed(Tester t) {
    return t.checkExpect(this.andrew.wellFormed(), true) 
        && t.checkExpect(new Unknown().wellFormed(), true);
    //add tests for non-well0formed trees
  }


  boolean testAncNames(Tester t) {
    return t.checkExpect(new Unknown().names(), new MtLoString()) &&
        t.checkExpect(this.darcy.names(), new ConsLoString("Darcy",
            new ConsLoString("Emma",
                new ConsLoString("Eustace", new 
                    MtLoString())))) &&
        t.checkExpect(this.andrew.names(), false);
  }

}