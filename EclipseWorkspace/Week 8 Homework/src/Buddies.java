import tester.Tester;

// represents a list of Person's buddies
interface ILoBuddy {

  //determines if this list of buddies contains the given person
  boolean contains(Person p);

  //determines the length of the list
  int length();

  //creates a list of buddies and ensures they are all unique
  ILoBuddy partyCountHelp(ILoBuddy acc);

  //checks if this list of buddies has any buddies that is the same as the given person
  boolean hasExtendedBuddy(Person that, ILoBuddy acc);

  //creates a list of buddies with the common buddies of this list and the given list
  int countCommonBuddiesHelp(ILoBuddy other);

}


//represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {

  /*
   * Fields: 
   *  None
   * Methods:
   *  this.contains(Person) ... boolean
   *  this.length() ... int
   *  this.partyCountHelp(ILoBuddy) ... ILoBuddy
   *  this.hasExtendedBuddy(Person, ILoBuddy) ... boolean
   *  this.countCommonBuddiesHelp(ILoBuddy) ... int
   * Methods of Fields:
   *  None
   */

  //determines if this empty list of buddies contains the given person
  public boolean contains(Person p) {
    return false;
  }

  //determines the length of the empty list
  public int length() {
    return 0;
  }

  //counts the number of buddies of all of the empty buddies
  public ILoBuddy partyCountHelp(ILoBuddy acc) {
    return acc;
  }

  //checks if this empty list of buddies has any buddies that is the same as the given person
  public boolean hasExtendedBuddy(Person that, ILoBuddy acc) {
    return false;
  }

  //creates a list of buddies with the common buddies of this empty list and the given list
  public int countCommonBuddiesHelp(ILoBuddy other) {
    return 0;
  }
}

//represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields: none
   *  this.first ... Person
   *  this.rest ... ILoBuddy
   * Methods:
   *  this.contains(Person) ... boolean
   *  this.length() ... int
   *  this.partyCountHelp(ILoBuddy) ... ILoBuddy
   *  this.hasExtendedBuddy(Person, ILoBuddy) ... boolean
   *  this.countCommonBuddiesHelp(ILoBuddy) ... int
   * Methods of Fields: 
   *  this.first.addBuddy(Person) ... void
   *  this.first.hasDirectBuddy(Person) ... boolean
   *  this.first.partCount() ... int
   *  this.first.countcommonBuddies(Person) ... int
   *  this.first.hasExtendedBuddy(Person) ... boolean
   *  this.first.samePerson(Person) ... boolean
   *  this.first.hasExtendedBuddyHelper(Person, ILoBuddy) ... boolean
   *  this.first.unionBuddy(ILoBuddy) ... ILoBuddy
   */

  //determines if this non-empty list of buddies contains the given person
  public boolean contains(Person p) {
    return this.first.samePerson(p)
        || this.rest.contains(p);
  }

  //determines the length of the list
  public int length() {
    return 1 + this.rest.length();
  }

  //counts the number of buddies of all of the non-empty buddies
  public ILoBuddy partyCountHelp(ILoBuddy acc) {
    if (acc.contains(this.first)) {
      return this.rest.partyCountHelp(acc);
    }
    else {
      return this.rest.partyCountHelp(this.first.unionBuddy(new ConsLoBuddy(this.first, acc)));
    }
  }

  //creates a list of buddies with the common buddies of this non-empty list and the given list
  public int countCommonBuddiesHelp(ILoBuddy other) {
    if (other.contains(this.first)) {
      return 1 + this.rest.countCommonBuddiesHelp(other);
    }
    else {
      return this.rest.countCommonBuddiesHelp(other);
    }
  }

  //checks if this non-empty list of buddies has any buddies that is the same as the given person
  public boolean hasExtendedBuddy(Person that, ILoBuddy acc) {
    if (acc.contains(this.first)) {
      return this.rest.hasExtendedBuddy(that, acc);
    }
    else {
      ILoBuddy visited = new ConsLoBuddy(this.first, acc);
      return this.first.hasExtendedBuddyHelper(that, visited)
          || this.rest.hasExtendedBuddy(that, visited);
    }
  }

}

//represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }

  /*
   * Fields: none
   *  this.username ... String
   *  this.buddies ... ILoBuddy
   * Methods:
   *  this.addBuddy(Person) ... void
   *  this.hasDirectBuddy(Person) ... boolean
   *  this.partCount() ... int
   *  this.countcommonBuddies(Person) ... int
   *  this.hasExtendedBuddy(Person) ... boolean
   *  this.samePerson(Person) ... boolean
   *  this.hasExtendedBuddyHelper(Person, ILoBuddy) ... boolean
   *  this.unionBuddy(ILoBuddy) ... ILoBuddy
   * Methods of Fields: 
   *  this.buddies.contains(Person) ... boolean
   *  this.buddies.length() ... int
   *  this.buddies.partyCountHelp(ILoBuddy) ... ILoBuddy
   *  this.buddies.hasExtendedBuddy(Person, ILoBuddy) ... boolean
   *  this.buddies.countCommonBuddiesHelp(ILoBuddy) ... int
   */

  //EFFECT:
  //Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    this.buddies = new ConsLoBuddy(buddy, this.buddies);
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.contains(that);
  }

  // returns the number of people who will show up at the party 
  // given by this person
  int partyCount() {
    return this.buddies.partyCountHelp(new ConsLoBuddy(this, new MTLoBuddy())).length();
  }

  // returns the number of people that are direct buddies 
  // of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countCommonBuddiesHelp(that.buddies);
  }

  // will the given person be invited to a party 
  // organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that)
        || this.buddies.hasExtendedBuddy(that, new ConsLoBuddy(this, new MTLoBuddy()));
  }

  //determines if the given person is the same as this person
  boolean samePerson(Person that) {
    return this.username.equals(that.username);
  }

  //determines if this person is direct buddies with that person or its buddies 
  //contain the given person
  boolean hasExtendedBuddyHelper(Person that, ILoBuddy visited) {
    return this.hasDirectBuddy(that)
        || this.buddies.contains(that)
        || this.buddies.hasExtendedBuddy(that, visited);
  }

  //creates a list of unique buddies given a list of buddies
  ILoBuddy unionBuddy(ILoBuddy acc) {
    return this.buddies.partyCountHelp(acc);
  }
}


//runs tests for the buddies problem
class ExamplesBuddies {
  Person ann; 
  Person bob; 
  Person cole; 
  Person dan; 
  Person ed; 
  Person fay; 
  Person gabi; 
  Person hank; 
  Person jan; 
  Person kim; 
  Person len;
  Person ann2;


  //initializes all of the buddies
  void initBuddies() {
    this.ann = new Person("Ann");
    this.bob = new Person("Bob");
    this.cole = new Person("Cole");
    this.dan = new Person("Dan");
    this.ed = new Person("Ed");
    this.fay = new Person("Fay");
    this.gabi = new Person("Gabi");
    this.hank = new Person("Hank");
    this.jan = new Person("Jan");
    this.kim = new Person("Kim");
    this.len = new Person("Len");
    this.ann2 = new Person("Ann");

    this.ann.addBuddy(this.bob);
    this.ann.addBuddy(this.cole);
    this.bob.addBuddy(this.ann);
    this.bob.addBuddy(this.ed);
    this.bob.addBuddy(this.hank);
    this.cole.addBuddy(this.dan);
    this.dan.addBuddy(this.cole);
    this.ed.addBuddy(this.fay);
    this.fay.addBuddy(this.ed);
    this.fay.addBuddy(this.gabi);
    this.gabi.addBuddy(this.ed);
    this.gabi.addBuddy(this.fay);
    this.jan.addBuddy(this.kim);
    this.jan.addBuddy(this.len);
    this.kim.addBuddy(this.jan);
    this.kim.addBuddy(this.len);
    this.len.addBuddy(this.jan);
    this.len.addBuddy(this.kim);
    this.ann2.addBuddy(this.bob);
    this.ann2.addBuddy(this.cole);
  }

  //tests the add buddy method
  void testAddBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.hank.buddies, new MTLoBuddy());

    this.hank.addBuddy(this.bob);
    t.checkExpect(this.hank.buddies, new ConsLoBuddy(this.bob, 
        new MTLoBuddy()));

    this.hank.addBuddy(this.ann);
    t.checkExpect(this.hank.buddies, new ConsLoBuddy(this.ann, 
        new ConsLoBuddy(this.bob, new MTLoBuddy())));

    t.checkExpect(this.cole.buddies, new ConsLoBuddy(this.dan, 
        new MTLoBuddy()));

    this.cole.addBuddy(this.kim);
    t.checkExpect(this.cole.buddies, new ConsLoBuddy(this.kim, 
        new ConsLoBuddy(this.dan, new MTLoBuddy())));

    t.checkExpect(this.gabi.buddies, new ConsLoBuddy(this.fay, 
        new ConsLoBuddy(this.ed, new MTLoBuddy())));

    this.gabi.addBuddy(this.len);
    t.checkExpect(this.gabi.buddies, new ConsLoBuddy(this.len, 
        new ConsLoBuddy(this.fay, new ConsLoBuddy(this.ed, new MTLoBuddy()))));

    t.checkExpect(this.len.buddies, new ConsLoBuddy(this.kim, 
        new ConsLoBuddy(this.jan, new MTLoBuddy())));

    this.len.addBuddy(this.dan);
    t.checkExpect(this.len.buddies, new ConsLoBuddy(this.dan, 
        new ConsLoBuddy(this.kim, new ConsLoBuddy(this.jan, new MTLoBuddy()))));
  }

  //tests the contain method
  void testContains(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.buddies.contains(this.cole), true);
    t.checkExpect(this.ann.buddies.contains(this.hank), false);
    t.checkExpect(this.dan.buddies.contains(this.cole), true);
    t.checkExpect(this.dan.buddies.contains(this.bob), false);
    t.checkExpect(this.jan.buddies.contains(this.len), true);
    t.checkExpect(this.len.buddies.contains(this.ed), false);
    t.checkExpect(this.fay.buddies.contains(this.gabi), true);
    t.checkExpect(this.hank.buddies.contains(this.kim), false);
    t.checkExpect(this.jan.buddies.contains(this.gabi), false);
    t.checkExpect(this.jan.buddies.contains(this.kim), true);
  }

  //tests the length method
  void testLength(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.buddies.length(), 2);
    t.checkExpect(this.bob.buddies.length(), 3);
    t.checkExpect(this.cole.buddies.length(), 1);
    t.checkExpect(this.dan.buddies.length(), 1);
    t.checkExpect(this.ed.buddies.length(), 1);
    t.checkExpect(this.fay.buddies.length(), 2);
    t.checkExpect(this.gabi.buddies.length(), 2);
    t.checkExpect(this.hank.buddies.length(), 0);
    t.checkExpect(this.jan.buddies.length(), 2);
    t.checkExpect(this.kim.buddies.length(), 2);
    t.checkExpect(this.len.buddies.length(), 2);
  }

  //tests the samePerson method
  void testSamePerson(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.samePerson(this.ann), true);
    t.checkExpect(this.ann.samePerson(this.hank), false);
    t.checkExpect(this.ed.samePerson(this.ed), true);
    t.checkExpect(this.ann.samePerson(this.ann2), true);
    t.checkExpect(this.fay.samePerson(this.gabi), false);
    t.checkExpect(this.len.samePerson(this.kim), false);
    t.checkExpect(this.cole.samePerson(this.kim), false);
    t.checkExpect(this.dan.samePerson(this.ed), false);
    t.checkExpect(this.dan.samePerson(this.ed), false);
    t.checkExpect(this.jan.samePerson(this.jan), true);
  }

  //tests the has direct buddy method
  void testHasDirectBuddy(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.dan), false);
    t.checkExpect(this.cole.hasDirectBuddy(this.bob), false);
    t.checkExpect(this.cole.hasDirectBuddy(this.dan), true);
    t.checkExpect(this.jan.hasDirectBuddy(this.len), true);
    t.checkExpect(this.hank.hasDirectBuddy(this.bob), false);
    t.checkExpect(this.ed.hasDirectBuddy(this.fay), true);
    t.checkExpect(this.ed.hasDirectBuddy(this.bob), false);
    t.checkExpect(this.cole.hasDirectBuddy(this.bob), false);
  }


  //test the count common buddies method
  void testCountCommonBuddies(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.cole.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.cole.countCommonBuddies(this.dan), 0);
    t.checkExpect(this.jan.countCommonBuddies(this.len), 1);
    t.checkExpect(this.hank.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.ed.countCommonBuddies(this.fay), 0);
    t.checkExpect(this.gabi.countCommonBuddies(this.bob), 1);
    t.checkExpect(this.cole.countCommonBuddies(this.bob), 0);
  }


  //tests the partCout method
  void testPartyCount(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.partyCount(), 8);
    t.checkExpect(this.bob.partyCount(), 8);
    t.checkExpect(this.cole.partyCount(), 2);
    t.checkExpect(this.dan.partyCount(), 2);
    t.checkExpect(this.ed.partyCount(), 3);
    t.checkExpect(this.fay.partyCount(), 3);
    t.checkExpect(this.gabi.partyCount(), 3);
    t.checkExpect(this.hank.partyCount(), 1);
    t.checkExpect(this.jan.partyCount(), 3);
    t.checkExpect(this.kim.partyCount(), 3);
    t.checkExpect(this.len.partyCount(), 3);
  }


  //tests the hasExtendedBuddy for person's method
  void testHasExtendedBuddyPerson(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(this.bob), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.dan), true);
    t.checkExpect(this.cole.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.cole.hasExtendedBuddy(this.dan), true);
    t.checkExpect(this.jan.hasExtendedBuddy(this.len), true);
    t.checkExpect(this.hank.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.ed.hasExtendedBuddy(this.fay), true);
    t.checkExpect(this.ed.hasExtendedBuddy(this.gabi), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.cole.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.ann.hasExtendedBuddy(this.fay), true);
  }

  //tests the hasExtendedBuddy for lists method
  void testHasExtendedBuddyILoBuddy(Tester t) {
    this.initBuddies();
    t.checkExpect(this.hank.buddies.hasExtendedBuddy(ann, 
        new ConsLoBuddy(this.ann, new MTLoBuddy())), false);
    t.checkExpect(this.ann.buddies.hasExtendedBuddy(fay, 
        new ConsLoBuddy(this.bob, new MTLoBuddy())), false);
    t.checkExpect(this.ann.buddies.hasExtendedBuddy(len, 
        new ConsLoBuddy(this.hank, new MTLoBuddy())), false);
    t.checkExpect(this.ed.buddies.hasExtendedBuddy(ann, 
        new ConsLoBuddy(this.fay, new MTLoBuddy())), false);
    t.checkExpect(this.gabi.buddies.hasExtendedBuddy(bob, 
        new ConsLoBuddy(this.len, new MTLoBuddy())), false);
    t.checkExpect(this.cole.buddies.hasExtendedBuddy(bob, 
        new ConsLoBuddy(this.kim, new MTLoBuddy())), false);
    t.checkExpect(this.ann.buddies.hasExtendedBuddy(dan, 
        new ConsLoBuddy(this.jan, new MTLoBuddy())), true);
    t.checkExpect(this.ed.buddies.hasExtendedBuddy(gabi, 
        new ConsLoBuddy(this.ed, new MTLoBuddy())), true);
    t.checkExpect(this.cole.buddies.hasExtendedBuddy(ann, 
        new ConsLoBuddy(this.fay, new MTLoBuddy())), false);
  }

  //tests the hasExtendedBuddyHelper method
  void testHasExtendedBuddyHelper(Tester t) {
    this.initBuddies();
    t.checkExpect(this.hank.hasExtendedBuddyHelper(ann, 
        new ConsLoBuddy(this.ann, new MTLoBuddy())), false);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(fay, 
        new ConsLoBuddy(this.bob, new MTLoBuddy())), false);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(len, 
        new ConsLoBuddy(this.hank, new MTLoBuddy())), false);
    t.checkExpect(this.ed.hasExtendedBuddyHelper(ann, 
        new ConsLoBuddy(this.fay, new MTLoBuddy())), false);
    t.checkExpect(this.gabi.hasExtendedBuddyHelper(bob, 
        new ConsLoBuddy(this.len, new MTLoBuddy())), false);
    t.checkExpect(this.cole.hasExtendedBuddyHelper(bob, 
        new ConsLoBuddy(this.kim, new MTLoBuddy())), false);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(dan, 
        new ConsLoBuddy(this.jan, new MTLoBuddy())), true);
    t.checkExpect(this.ed.hasExtendedBuddyHelper(gabi, 
        new ConsLoBuddy(this.ed, new MTLoBuddy())), true);
    t.checkExpect(this.cole.hasExtendedBuddyHelper(ann, 
        new ConsLoBuddy(this.fay, new MTLoBuddy())), false);
  }

  //tests the countCommonBuddiesHelp method
  void testCountCommonBuddiesHelp(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.buddies.countCommonBuddiesHelp(this.bob.buddies), 0);
    t.checkExpect(this.ann.buddies.countCommonBuddiesHelp(this.dan.buddies), 1);
    t.checkExpect(this.cole.buddies.countCommonBuddiesHelp(this.bob.buddies), 0);
    t.checkExpect(this.cole.buddies.countCommonBuddiesHelp(this.dan.buddies), 0);
    t.checkExpect(this.jan.buddies.countCommonBuddiesHelp(this.len.buddies), 1);
    t.checkExpect(this.hank.buddies.countCommonBuddiesHelp(this.bob.buddies), 0);
    t.checkExpect(this.ed.buddies.countCommonBuddiesHelp(this.fay.buddies), 0);
    t.checkExpect(this.gabi.buddies.countCommonBuddiesHelp(this.bob.buddies), 1);
    t.checkExpect(this.cole.buddies.countCommonBuddiesHelp(this.bob.buddies), 0);
  }

  //tests the unionBuddies method
  void testUnionBuddys(Tester t) {
    this.initBuddies();
    t.checkExpect(this.cole.unionBuddy(new ConsLoBuddy(this.dan, new MTLoBuddy())), 
        this.cole.buddies);
    t.checkExpect(this.dan.unionBuddy(new ConsLoBuddy(this.cole, new MTLoBuddy())), 
        this.dan.buddies);
    t.checkExpect(this.jan.unionBuddy(new ConsLoBuddy(this.kim, new MTLoBuddy())), 
        new ConsLoBuddy(this.jan, new ConsLoBuddy(this.len, new ConsLoBuddy(this.kim, 
            new MTLoBuddy()))));
    t.checkExpect(this.kim.unionBuddy(new ConsLoBuddy(this.jan, new MTLoBuddy())), 
        new ConsLoBuddy(this.kim, new ConsLoBuddy(this.len, new ConsLoBuddy(this.jan, 
            new MTLoBuddy()))));
    t.checkExpect(this.ann.unionBuddy(new ConsLoBuddy(this.bob, new MTLoBuddy())), 
        new ConsLoBuddy(this.dan, new ConsLoBuddy(this.cole, new ConsLoBuddy(this.bob, 
            new MTLoBuddy()))));
    t.checkExpect(this.gabi.unionBuddy(new ConsLoBuddy(this.ed, new MTLoBuddy())), 
        new ConsLoBuddy(this.gabi, new ConsLoBuddy(this.fay, new ConsLoBuddy(this.ed, 
            new MTLoBuddy()))));
    t.checkExpect(this.hank.unionBuddy(new ConsLoBuddy(this.bob, new MTLoBuddy())),
        new ConsLoBuddy(this.bob, new MTLoBuddy()));
  }

  //tests the partyCountHelp method
  void testPartyCountHelp(Tester t) {
    this.initBuddies();
    t.checkExpect(this.cole.buddies.partyCountHelp(new ConsLoBuddy(this.hank, 
        new MTLoBuddy())), new ConsLoBuddy(this.cole, new ConsLoBuddy(this.dan, 
            new ConsLoBuddy(this.hank, new MTLoBuddy()))));
    t.checkExpect(this.dan.buddies.partyCountHelp(new ConsLoBuddy(this.hank, 
        new MTLoBuddy())), new ConsLoBuddy(this.dan, new ConsLoBuddy(this.cole, 
            new ConsLoBuddy(this.hank, new MTLoBuddy()))));
    t.checkExpect(this.fay.buddies.partyCountHelp(new ConsLoBuddy(this.hank, 
        new MTLoBuddy())), new ConsLoBuddy(this.ed, new ConsLoBuddy(this.fay, 
            new ConsLoBuddy(this.gabi, new ConsLoBuddy(this.hank, new MTLoBuddy())))));
    t.checkExpect(this.fay.buddies.partyCountHelp(new ConsLoBuddy(this.hank, 
        new MTLoBuddy())), new ConsLoBuddy(this.ed, new ConsLoBuddy(this.fay, 
            new ConsLoBuddy(this.gabi, new ConsLoBuddy(this.hank, new MTLoBuddy())))));
    t.checkExpect(this.hank.buddies.partyCountHelp(new ConsLoBuddy(this.cole, 
        new MTLoBuddy())), new ConsLoBuddy(this.cole, new MTLoBuddy()));
    t.checkExpect(this.jan.buddies.partyCountHelp(new ConsLoBuddy(this.kim, 
        new MTLoBuddy())), new ConsLoBuddy(this.jan, new ConsLoBuddy(this.len, 
            new ConsLoBuddy(this.kim, new MTLoBuddy()))));
    t.checkExpect(this.kim.buddies.partyCountHelp(new ConsLoBuddy(this.len, 
        new MTLoBuddy())), new ConsLoBuddy(this.kim, new ConsLoBuddy(this.jan, 
            new ConsLoBuddy(this.len, new MTLoBuddy()))));
  }
}











