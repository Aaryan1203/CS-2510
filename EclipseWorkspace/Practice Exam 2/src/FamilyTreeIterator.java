
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

// to represent a person with a name 
// and their list of children (for a family tree)
class Person implements Iterable<Person>{
  String name;
  ArrayList<Person> listOfChildren;

  // standard constructor
  Person(String name, ArrayList<Person> children) {
    this.name = name;
    this.listOfChildren = children;
  }

  // convenience constructor
  Person(String name) {
    this.name = name;
    this.listOfChildren = new ArrayList<Person>();
  }

  @Override
  public Iterator<Person> iterator() {
    return new FamilyTreeIterator(this);
  }
}

// represents an iterator for a family tree
class FamilyTreeIterator implements Iterator<Person> {
  // Person curPerson;
  ArrayList<Person> worklist;
  int curIndex;

  FamilyTreeIterator(Person parent) {
    this.worklist = new ArrayList<Person>();
    this.worklist.add(parent);
    this.curIndex = 0;
  }

  // determines if the family tree still has people to return
  public boolean hasNext() {
    return this.curIndex < this.worklist.size();
  }

  // Returns the next item
  // Effect: updates the index by 1
  public Person next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more items");
    }

    Person result = this.worklist.get(curIndex);
    this.curIndex++;
    for (Person p : result.listOfChildren) {
      if (!this.worklist.contains(p)) {
        this.worklist.add(p);
      }
    }
    return result;
  }

}

//to represent examples of Person
class ExamplesPerson {

  ExamplesPerson() {
  }

  // simple example
  Person c;
  Person b;
  Person a;

  // complex example
  Person len;
  Person kim;
  Person jan;
  Person hank;
  Person gabi;
  Person fay;
  Person ed;
  Person dan;
  Person cole;
  Person bob;
  Person ann;

  // initializes the data and the family trees
  void initData() {
    // simple example

    /*
     *      A
     *      |
     *     (B C)
     *      
     */

    this.c = new Person("C");
    this.b = new Person("B");
    this.a = new Person("A", new ArrayList<Person>(Arrays.asList(this.b, this.c)));


    // complex example

    /*
     *            Ann
     *            |
     *           (Bob         Cole         Dan)
     *            |           |            |
     *   (Ed Fay Gabi Hank)  (Jan Kim)     ()
     *       |
     *      (Len)
     * 
     * 
     * Ann's children: Bob, Cole, and Dan
     * Bob's children: Ed, Fay, Gabi, and Hank
     * Cole's children: Jan and Kim
     * Fay's children: Len
     * 
     */

    this.len = new Person("Len");
    this.kim = new Person("Kim");
    this.jan = new Person("Jan");
    this.hank = new Person("Hank");
    this.gabi = new Person("Gabi");
    ArrayList<Person> fayChildren = new ArrayList<Person>(
        Arrays.asList(this.len));
    this.fay = new Person("Fay", fayChildren);
    this.ed = new Person("Ed");
    this.dan = new Person("Dan");
    ArrayList<Person> coleChildren = new ArrayList<Person>(
        Arrays.asList(this.jan, this.kim));
    this.cole = new Person("Cole", coleChildren);
    ArrayList<Person> bobChildren = new ArrayList<Person>(
        Arrays.asList(this.ed, this.fay, this.gabi, this.hank));
    this.bob = new Person("Bob", bobChildren);
    ArrayList<Person> annChildren = new ArrayList<Person>(
        Arrays.asList(this.bob, this.cole, this.dan));
    this.ann = new Person("Ann", annChildren);
  }

  void testForEachLoopForSimpleExample(Tester t) {
    this.initData();

    ArrayList<Person> aPeople = new ArrayList<Person>(
        Arrays.asList(this.a, this.b, this.c));
    ArrayList<Person> aTest = new ArrayList<Person>();

    for (Person p : this.a) {
      aTest.add(p);
      // System.out.println(p.name);
    }

    // order should be A -> B -> C
    t.checkExpect(aTest, aPeople);
  }

  void testForEachLoopForComplexExample(Tester t) {
    this.initData();

    ArrayList<Person> annPeople = new ArrayList<Person>(
        Arrays.asList(this.ann, this.bob, this.cole,
            this.dan, this.ed, this.fay, this.gabi, this.hank,
            this.jan, this.kim, this.len));
    ArrayList<Person> annTest = new ArrayList<Person>();

    for (Person p : this.ann) {
      annTest.add(p);
      // System.out.println(p.name);
    }

    // order should be Ann -> Bob -> Cole -> Dan -> Ed -> Fay
    //              -> Gabi -> Hank -> Jan -> Kim -> Len
    t.checkExpect(annTest, annPeople);
  }

  void testHasNextAndNextForComplexExample(Tester t) {
    this.initData();

    Iterator<Person> famIter = this.ann.iterator();

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.ann);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.bob);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.cole);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.dan);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.ed);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.fay);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.gabi);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.hank);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.jan);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.kim);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.len);

    t.checkExpect(famIter.hasNext(), false);
    t.checkException(new NoSuchElementException("No more items"), 
        famIter, "next");
  }
}
