import tester.Tester;
import java.util.function.Predicate;


//abstract class that represents an ANode
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  //convenience constructor
  ANode() {
    this.next = this;
    this.prev = this;
  }

  //constructor that initializes two fields
  ANode(ANode<T> next, ANode<T> prev) {
    this.next = next;
    this.prev = prev;
  }

  //determines the number of the nodes in the Deque
  abstract int size();

  //removes this node from the list
  abstract T removeHelp();

  //determines if this ANode returns true for the given preducate
  abstract ANode<T> findHelp(Predicate<T> pred);

  //changes the next anode to the given anode
  void changeNext(ANode<T> change) {
    this.next = change;
  }

  //changes the previous anode to the given anode
  void changePrev(ANode<T> change) {
    this.prev = change;
  }

}


//class that represents a Sentinel
class Sentinel<T> extends ANode<T> {

  Sentinel() {
    super();
  }

  //counts the number of nodes in this Sentinel
  int size() {
    return 0;
  }

  //helps counts the number of nodes in this Sentinel
  int sizeHelp() {
    return this.next.size();
  }

  //EFFECT: adds the given value to the front of the list
  void addAtHead(T value) {
    new Node<T>(value, this.next, this);
  }

  //EFFECT: adds the given value to the back of the list
  void addAtTail(T value) {
    new Node<T>(value, this, this.prev);
  }

  //removes an item from an empty list so it throws an exception
  T removeHelp() {
    throw new RuntimeException("Cannot remove an item from an empty list");
  }

  //produces the first node in this Deque for which the given predicate returns true
  ANode<T> findHelp(Predicate<T> pred) {
    return this;
  }
}


//class that represents a Node
class Node<T> extends ANode<T> {
  T data;

  //constructor that initializes next and prev to null
  Node(T data) {
    super(null, null);
    this.data = data;
  }

  //constructor that initializes three fields
  Node(T data, ANode<T> next, ANode<T> prev) {
    super(next, prev);
    if (this.next == null || this.prev == null) {
      throw new IllegalArgumentException("Must set the next and previous nodes to a value");
    }

    this.data = data;
    this.next.changePrev(this);
    this.prev.changeNext(this);
  }

  //counts the number of nodes in this Deque
  int size() {
    return 1 + this.next.size();
  }

  //returns the data
  //EFFECT: removes this node from the list 
  T removeHelp() {
    this.prev.changeNext(this.next);
    this.next.changePrev(this.prev);
    return this.data;
  }

  //returns the first node for which the given predicate returns true
  ANode<T> findHelp(Predicate<T> pred) {
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return this.next.findHelp(pred);
    }
  }

}


//represents a Deque
class Deque<T> {
  Sentinel<T> header;

  //empty constructor to initialize header to emtpy sentinel
  Deque() {
    this.header = new Sentinel<T>();
  }

  //constructor that initializes header
  Deque(Sentinel<T> header) {
    this.header = header;
  }

  //counts the number of nodes in this Deque
  int size() {
    return this.header.sizeHelp();
  }

  //EFFECT: adds the given value to the front of the list
  void addAtHead(T value) {
    this.header.addAtHead(value);
  }

  //EFFECT: adds the given value to the back of the list
  void addAtTail(T value) {
    this.header.addAtTail(value);
  }

  //returns the removed item
  //EFFECT: removes the first item
  T removeFromHead() {
    return this.header.next.removeHelp();
  }

  //returns the removed item
  //EFFECT: removes the last item
  T removeFromTail() {
    return this.header.prev.removeHelp();
  }

  //produces the first node in this Deque for which the given predicate returns true
  ANode<T> find(Predicate<T> pred) {
    return this.header.next.findHelp(pred);
  }
}

//Predicate to determine if the integer is even
class Even implements Predicate<Integer> {

  //determines if the given integer is even
  public boolean test(Integer t) {
    return t % 2 == 0;
  }
}

//Predicate to determine if the length of the string is of the given value
class LengthOfString implements Predicate<String> {
  int length;

  LengthOfString(int length) {
    this.length = length;
  }

  //determines if this string is of the given length
  public boolean test(String s) {
    return s.length() == length;
  }
}

//Examples Class
class ExamplesDeque {
  
  Sentinel<String> sentinelEmpty;
  Deque<String> deque1;

  Sentinel<String> sentinelString;
  Node<String> abc;
  Node<String> bcd;
  Node<String> cde;
  Node<String> def;
  Deque<String> deque2;

  Sentinel<Integer> sentinelInteger;
  Deque<Integer> deaqu4;
  Node<Integer> oneTwoThree;
  Node<Integer> threeFourFive;
  Node<Integer> twoThreeFour;
  Node<Integer> fourFiveSix;
  Node<Integer> fiveSixSeven;

  Sentinel<String> sentinelString2;
  Node<String> abcd;
  Node<String> bcdef;
  Node<String> cdefg;
  Node<String> defh;
  Deque<String> deque3;

  void initData() {
    //deque1
    this.sentinelEmpty = new Sentinel<String>();
    this.deque1 = new Deque<String>(this.sentinelEmpty);

    //deque2
    this.sentinelString = new Sentinel<String>();
    this.abc = new Node<String>("abc", this.sentinelString, this.sentinelString);
    this.bcd = new Node<String>("bcd", this.sentinelString, this.abc);
    this.cde = new Node<String>("cde", this.sentinelString, this.bcd);
    this.def = new Node<String>("def", this.sentinelString, this.cde);
    this.deque2 = new Deque<String>(this.sentinelString);
    
    //deque3
    this.sentinelString2 = new Sentinel<String>();
    this.abcd = new Node<String>("abcd", this.sentinelString2, this.sentinelString2);
    this.bcdef = new Node<String>("bcdef", this.sentinelString2, this.abcd);
    this.cdefg = new Node<String>("cdefg", this.sentinelString2, this.bcdef);
    this.defh = new Node<String>("defh", this.sentinelString2, this.cdefg);
    this.deque3 = new Deque<String>(this.sentinelString2);

    //deque4
    this.sentinelInteger = new Sentinel<Integer>();
    this.oneTwoThree = new Node<Integer>(123, this.sentinelInteger, this.sentinelInteger);
    this.threeFourFive = new Node<Integer>(345, this.sentinelInteger, this.oneTwoThree);
    this.twoThreeFour = new Node<Integer>(234, this.sentinelInteger, this.threeFourFive);
    this.fourFiveSix = new Node<Integer>(456, this.sentinelInteger, this.twoThreeFour);
    this.fiveSixSeven = new Node<Integer>(567, this.sentinelInteger, this.fourFiveSix);
    this.deaqu4 = new Deque<Integer>(this.sentinelInteger);
  }

  //tests the changeNext method
  void testChangeNext(Tester t) {
    this.initData();
    this.sentinelString.changeNext(this.bcd);
    t.checkExpect(this.sentinelString.next, this.bcd);
    this.sentinelString.changeNext(this.def);
    t.checkExpect(this.sentinelString.next, this.def);
    this.sentinelEmpty.changeNext(this.def);
    t.checkExpect(this.sentinelEmpty.next, this.def);
    this.sentinelInteger.changeNext(this.fiveSixSeven);
    t.checkExpect(this.sentinelInteger.next, this.fiveSixSeven);
    this.sentinelInteger.changeNext(this.oneTwoThree);
    t.checkExpect(this.sentinelInteger.next, this.oneTwoThree);
  }

  //tests the changePrev method
  void testChangePrev(Tester t) {
    this.initData();
    this.sentinelString.changePrev(this.bcd);
    t.checkExpect(this.sentinelString.prev, this.bcd);
    this.sentinelString.changePrev(this.def);
    t.checkExpect(this.sentinelString.prev, this.def);
    this.sentinelEmpty.changePrev(this.def);
    t.checkExpect(this.sentinelEmpty.prev, this.def);
    this.sentinelInteger.changePrev(this.fiveSixSeven);
    t.checkExpect(this.sentinelInteger.prev, this.fiveSixSeven);
    this.sentinelInteger.changePrev(this.oneTwoThree);
    t.checkExpect(this.sentinelInteger.prev, this.oneTwoThree);
  }

  //tests the constructor exception
  void testConstructorException(Tester t) {
    this.initData();
    t.checkConstructorException(new IllegalArgumentException(
        "Must set the next and previous nodes to a value"), 
        "Node", "abc", null, null);
    t.checkConstructorException(new IllegalArgumentException(
        "Must set the next and previous nodes to a value"), 
        "Node", "bcd", null, new Sentinel<String>());
    t.checkConstructorException(new IllegalArgumentException(
        "Must set the next and previous nodes to a value"), 
        "Node", "def", new Sentinel<String>(), null);
    t.checkConstructorException(new IllegalArgumentException(
        "Must set the next and previous nodes to a value"), 
        "Node", 123, new Sentinel<Integer>(), null);
  }

  //tests the size method in the Deque class
  void testSizeDeque(Tester t) {
    this.initData();
    t.checkExpect(this.deque2.size(), 4);
    t.checkExpect(this.deaqu4.size(), 5);
    t.checkExpect(this.deque1.size(), 0);
    t.checkExpect(this.deque3.size(), 4);
  }

  //tests the size method in the Sentinel class
  void testSizeSentinal(Tester t) {
    this.initData();
    t.checkExpect(this.sentinelEmpty.size(), 0);
    t.checkExpect(this.sentinelInteger.size(), 0);
    t.checkExpect(this.sentinelString.size(), 0);
    t.checkExpect(this.sentinelString2.size(), 0);

  }

  //tests the size method in the Node class
  void testSizeNode(Tester t) {
    this.initData();
    t.checkExpect(this.abc.size(), 4);
    t.checkExpect(this.bcd.size(), 3);
    t.checkExpect(this.cde.size(), 2);
    t.checkExpect(this.def.size(), 1);
    t.checkExpect(this.oneTwoThree.size(), 5);
    t.checkExpect(this.threeFourFive.size(), 4);
    t.checkExpect(this.twoThreeFour.size(), 3);
    t.checkExpect(this.fourFiveSix.size(), 2);
    t.checkExpect(this.fiveSixSeven.size(), 1);
  }

  //tests the sizeHelp method
  void testSizeHelp(Tester t) {
    this.initData();
    t.checkExpect(this.sentinelEmpty.sizeHelp(), 0);
    t.checkExpect(this.sentinelInteger.sizeHelp(), 5);
    t.checkExpect(this.sentinelString.sizeHelp(), 4);
  }

  //test the addAtHead method in the Deque class
  void testAddAtHeadDeque(Tester t) {
    this.initData();
    t.checkExpect(this.deque1, new Deque<String>(new Sentinel<String>()));
    Sentinel<String> sentinel1 = new Sentinel<String>();

    this.deque1.addAtHead("abc");
    Node<String> abc = new Node<String>("abc", sentinel1, sentinel1);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deque1.addAtHead("bcd");
    Node<String> bcd = new Node<String>("bcd", abc, sentinel1);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deque1.addAtHead("cde");
    Node<String> cde = new Node<String>("cde", bcd, sentinel1);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deaqu4.addAtHead(999);
    Node<Integer> nineNineNine = new Node<Integer>(999, this.oneTwoThree, sentinelInteger);
    t.checkExpect(this.deaqu4.header.next, nineNineNine);

    this.deque2.addAtHead("zzz");
    Node<String> zzz = new Node<String>("zzz", this.abc, sentinelString);
    t.checkExpect(this.deque2.header.next, zzz);
  }

  //test the addAtHead method in the Sentinel class
  void testAddAtHeadSentinel(Tester t) {
    this.initData();
    t.checkExpect(this.sentinelEmpty, new Sentinel<String>());
    Sentinel<String> sentinel1 = new Sentinel<String>();

    this.sentinelEmpty.addAtHead("abc");
    Node<String> abc = new Node<String>("abc", sentinel1, sentinel1);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelEmpty.addAtHead("bcd");
    Node<String> bcd = new Node<String>("bcd", abc, sentinel1);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelEmpty.addAtHead("cde");
    Node<String> cde = new Node<String>("cde", bcd, sentinel1);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelInteger.addAtHead(999);
    Node<Integer> nineNineNine = new Node<Integer>(999, this.oneTwoThree, this.sentinelInteger);
    t.checkExpect(this.sentinelInteger.next, nineNineNine);

    this.sentinelString.addAtHead("zzz");
    Node<String> zzz = new Node<String>("zzz", this.abc, this.sentinelString);
    t.checkExpect(this.sentinelString.next, zzz);
  }

  //test the addAtTail method
  void testAddAtTailDeque(Tester t) {
    this.initData();
    t.checkExpect(this.deque1, new Deque<String>(new Sentinel<String>()));
    Sentinel<String> sentinel1 = new Sentinel<String>();

    this.deque1.addAtTail("abc");
    Node<String> abc = new Node<String>("abc", sentinel1, sentinel1);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deque1.addAtTail("bcd");
    Node<String> bcd = new Node<String>("bcd", sentinel1, abc);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deque1.addAtTail("cde");
    Node<String> cde = new Node<String>("cde", sentinel1, bcd);
    t.checkExpect(this.deque1, new Deque<String>(sentinel1));

    this.deque1.addAtTail("def");
    t.checkExpect(this.deque1, this.deque2);

    this.deaqu4.addAtTail(999);
    Node<Integer> nineNineNine = new Node<Integer>(999, sentinelInteger, this.fiveSixSeven);
    t.checkExpect(this.deaqu4.header.prev, nineNineNine);

    this.deque2.addAtTail("zzz");
    Node<String> zzz = new Node<String>("zzz", sentinelString, this.def);
    t.checkExpect(this.deque2.header.prev, zzz);
  }

  //test the addAtTail method in the Sentinel class
  void testAddAtTailSentinel(Tester t) {
    this.initData();
    t.checkExpect(this.sentinelEmpty, new Sentinel<String>());
    Sentinel<String> sentinel1 = new Sentinel<String>();

    this.sentinelEmpty.addAtTail("abc");
    Node<String> abc = new Node<String>("abc", sentinel1, sentinel1);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelEmpty.addAtTail("bcd");
    Node<String> bcd = new Node<String>("bcd", sentinel1, abc);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelEmpty.addAtTail("cde");
    Node<String> cde = new Node<String>("cde", sentinel1, bcd);
    t.checkExpect(this.sentinelEmpty, sentinel1);

    this.sentinelInteger.addAtTail(999);
    Node<Integer> nineNineNine = new Node<Integer>(999, this.sentinelInteger, this.fiveSixSeven);
    t.checkExpect(this.sentinelInteger.prev, nineNineNine);

    this.sentinelString.addAtTail("zzz");
    Node<String> zzz = new Node<String>("zzz", this.sentinelString, this.def);
    t.checkExpect(this.sentinelString.prev, zzz);
  }

  //tests the removeFromHead method in the Deque class
  void testRemoveFromHeadDeque(Tester t) {
    this.initData();
    t.checkExpect(this.deque2.removeFromHead(), "abc");
    t.checkExpect(this.deque2.header.next, this.bcd);
    t.checkExpect(this.deque2.removeFromHead(), "bcd");
    t.checkExpect(this.deque2.header.next, this.cde);
    t.checkExpect(this.deque2.removeFromHead(), "cde");
    t.checkExpect(this.deque2.header.next, this.def);
    t.checkExpect(this.deque2.removeFromHead(), "def");
    t.checkExpect(this.deque2.header.next, new Sentinel<String>());

    t.checkExpect(this.deaqu4.removeFromHead(), 123);
    t.checkExpect(this.deaqu4.header.next, this.threeFourFive);
    t.checkExpect(this.deaqu4.removeFromHead(), 345);
    t.checkExpect(this.deaqu4.header.next, this.twoThreeFour);
    t.checkExpect(this.deaqu4.removeFromHead(), 234);
    t.checkExpect(this.deaqu4.header.next, this.fourFiveSix);
    t.checkExpect(this.deaqu4.removeFromHead(), 456);
    t.checkExpect(this.deaqu4.header.next, this.fiveSixSeven);
  }

  //tests the removeFromTail method in the Deque class
  void testRemoveFromTailDeque(Tester t) {
    this.initData();
    t.checkExpect(this.deque2.removeFromTail(), "def");
    t.checkExpect(this.deque2.header.prev, this.cde);
    t.checkExpect(this.deque2.removeFromTail(), "cde");
    t.checkExpect(this.deque2.header.prev, this.bcd);
    t.checkExpect(this.deque2.removeFromTail(), "bcd");
    t.checkExpect(this.deque2.header.prev, this.abc);
    t.checkExpect(this.deque2.removeFromTail(), "abc");
    t.checkExpect(this.deque2.header.prev, new Sentinel<String>());

    t.checkExpect(this.deaqu4.removeFromTail(), 567);
    t.checkExpect(this.deaqu4.header.prev, this.fourFiveSix);
    t.checkExpect(this.deaqu4.removeFromTail(), 456);
    t.checkExpect(this.deaqu4.header.prev, this.twoThreeFour);
    t.checkExpect(this.deaqu4.removeFromTail(), 234);
    t.checkExpect(this.deaqu4.header.prev, this.threeFourFive);
    t.checkExpect(this.deaqu4.removeFromTail(), 345);
    t.checkExpect(this.deaqu4.header.prev, this.oneTwoThree);
  }

  //tests the removeHelp method in the sentinel class
  void testRuntimeExceptions(Tester t) {
    this.initData();
    t.checkException(new RuntimeException("Cannot remove an item from an empty list"), 
        new Sentinel<Integer>(), "removeHelp");
    t.checkException(new RuntimeException("Cannot remove an item from an empty list"), 
        this.sentinelEmpty, "removeHelp");
    t.checkException(new RuntimeException("Cannot remove an item from an empty list"), 
        this.deque1, "removeFromHead");
    t.checkException(new RuntimeException("Cannot remove an item from an empty list"), 
        this.deque1, "removeFromTail");
  }

  //tests the removeHelp method in the Node class
  void testRemoveHelpNode(Tester t) {
    this.initData();
    t.checkExpect(this.def.removeHelp(), "def");
    t.checkExpect(this.cde.removeHelp(), "cde");
    t.checkExpect(this.bcd.removeHelp(), "bcd");
    t.checkExpect(this.abc.removeHelp(), "abc");
    t.checkExpect(this.threeFourFive.removeHelp(), 345);
    t.checkExpect(this.fourFiveSix.removeHelp(), 456);
    t.checkExpect(this.oneTwoThree.removeHelp(), 123);
  }

  //tests the find method
  void testFind(Tester t) {
    this.initData();
    t.checkExpect(this.deaqu4.find(new Even()), this.twoThreeFour);
    t.checkExpect(this.deque3.find(new LengthOfString(2)), this.sentinelString2);
    t.checkExpect(this.deque3.find(new LengthOfString(4)), this.abcd);
    t.checkExpect(this.deque3.find(new LengthOfString(5)), this.bcdef);
    t.checkExpect(this.deque1.find(new LengthOfString(2)), this.sentinelEmpty);
    t.checkExpect(this.deque2.find(new LengthOfString(3)), this.abc);
    t.checkExpect(this.deque2.find(new LengthOfString(4)), this.sentinelString);
  }

  //tests the findHelp method in the Sentinel class
  void testFindHelpSentinel(Tester t) {
    this.initData();
    t.checkExpect(this.sentinelEmpty.findHelp(new LengthOfString(3)), this.sentinelEmpty);
    t.checkExpect(this.sentinelString.findHelp(new LengthOfString(3)), this.sentinelString);
    t.checkExpect(this.sentinelString2.findHelp(new LengthOfString(4)), this.sentinelString2);
    t.checkExpect(this.sentinelInteger.findHelp(new Even()), this.sentinelInteger);
  }

  //tests the findHelp method in the Node class
  void testFindHelpNode(Tester t) {
    this.initData();
    t.checkExpect(this.abc.findHelp(new LengthOfString(3)), this.abc);
    t.checkExpect(this.cde.findHelp(new LengthOfString(3)), this.cde);
    t.checkExpect(this.abc.findHelp(new LengthOfString(4)), this.sentinelString);
    t.checkExpect(this.bcd.findHelp(new LengthOfString(4)), this.sentinelString);
    t.checkExpect(this.abcd.findHelp(new LengthOfString(5)), this.bcdef);
    t.checkExpect(this.bcdef.findHelp(new LengthOfString(5)), this.bcdef);
    t.checkExpect(this.bcdef.findHelp(new LengthOfString(4)), this.defh);
    t.checkExpect(this.bcdef.findHelp(new LengthOfString(6)), this.sentinelString2);
    t.checkExpect(this.oneTwoThree.findHelp(new Even()), this.twoThreeFour);
  }
  
  //tests the Predicates
  void testPredicate(Tester t) {
    LengthOfString string = new LengthOfString(3);
    Even even = new Even();
    t.checkExpect(string.test("hello"), false);
    t.checkExpect(string.test("bob"), true);
    t.checkExpect(even.test(3), false);
    t.checkExpect(even.test(4), true);
  }
}






