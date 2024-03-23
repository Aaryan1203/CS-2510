import tester.*;


// runs tests for the buddies problem
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

  //initializes all of the people
  void initPerson() {
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
  }

  //initializes all of the buddies
  void initBuddies() {
    this.ann.addBuddy(bob);
    this.ann.addBuddy(cole);
    this.bob.addBuddy(ann);
    this.bob.addBuddy(ed);
    this.bob.addBuddy(hank);
    this.cole.addBuddy(dan);
    this.dan.addBuddy(cole);
    this.ed.addBuddy(fay);
    this.fay.addBuddy(ed);
    this.fay.addBuddy(gabi);
    this.gabi.addBuddy(ed);
    this.gabi.addBuddy(fay);
    this.jan.addBuddy(kim);
    this.jan.addBuddy(len);
    this.kim.addBuddy(jan);
    this.kim.addBuddy(len);
    this.len.addBuddy(jan);
    this.len.addBuddy(kim);
  }

  //tests the add buddy method
  void testAddBuddy(Tester t) {
    initPerson();
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

  //tests the has direct buddy method
  void testHasDirectBuddy(Tester t) {
    initPerson();
    initBuddies();
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
    initPerson();
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

  //tests the hasExtendedBuddy method
  void testHasExtendedBuddy(Tester t) {
    initPerson();
    initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(this.bob), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.dan), true);
    t.checkExpect(this.cole.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.cole.hasExtendedBuddy(this.dan), true);
    t.checkExpect(this.jan.hasExtendedBuddy(this.len), true);
    t.checkExpect(this.hank.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.ed.hasExtendedBuddy(this.fay), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(this.bob), false);
    t.checkExpect(this.cole.hasExtendedBuddy(this.bob), false);
  }

  //tests the partCout method
  void testPartyCount(Tester t) {
    initPerson();
    initBuddies();
    t.checkExpect(this.ann.partyCount(), 7);
    t.checkExpect(this.bob.partyCount(), 7);
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
}