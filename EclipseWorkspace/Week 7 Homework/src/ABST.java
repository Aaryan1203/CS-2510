import tester.Tester;
import java.util.Comparator;

//represents an interface of a list with type T
interface IList<T> {

}

//an empty list of type T
class MtList<T> implements IList<T> {

  /*
   * Fields: None
   * 
   * Methods: None
   * 
   * Methods of Fields: None
   */
}

// a non-empty list of type T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields: 
   *  this.first ... T
   *  this.rest ... IList<T>
   * Methods: 
   *  
   * Methods of Fields: 
   *  
   */

}

//represents an abstract class of a binary search tree
abstract class ABST<T> {
  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

  //insert an item into a binary search tree in the correct place
  abstract ABST<T> insert(T other);

  //determines if there is an item in the tree with the same attribute 
  //as the given item based on the comparator
  abstract boolean present(T other);

  //returns the leftmost item of a tree
  abstract T getLeftmost();

  //helps return the leftmost item of a tree
  abstract T helperLeft(T data);

  //returns all but the leftmost item of a tree
  abstract ABST<T> getRight();

  //helps return the rightmost item of a tree
  //Acc: keeps track of the left most item in the tree
  abstract ABST<T> rightAcc(T left);

  //determines if this tree is the same as the given ABST
  abstract boolean sameTree(ABST<T> that);

  //determines if this tree is the same as the given leaf
  abstract boolean sameLeaf(Leaf<T> that);

  //determines if this tree is the same as the given node
  abstract boolean sameNode(Node<T> that);

  //determines if this tree has the same data as the given tree
  abstract boolean sameData(ABST<T> that);

  //helps determines if this leaf as the same data as the given tree
  abstract boolean sameDataHelper(ABST<T> that);

  //builds a list of items in the tree in sorted order
  abstract IList<T> buildList();


}

//represents a leaf in a binary search tree
class Leaf<T> extends ABST<T> {
  Leaf(Comparator<T> order) {
    super(order);
  }

  /*
   * Fields:
   *  this.order ... Comparator<T>
   * Methods: none
   *  this.insert(T) ... ABST<T>
   *  this.present(T) ... Boolean
   *  this.getLeftmost() ... T
   *  this.helperLeft(T) ... T
   *  this.getRight() ... ABST<T>
   *  this.rightAcc(T) ... ABST<T>
   *  this.sameTree(ABST<T>) ... boolean
   *  this.sameLeaf(Leaf<T>) ... boolean
   *  this.sameNode(Node<T>) ... boolean
   *  this.sameData(ABST<T>) ... boolean
   *  this.sameDataHelper(ABST<T>) ... boolean
   *  this.inTree(T) ... boolean
   *  this.buildList() ... IList<T>
   * Methods of Fields:
   *  this.order.compare(T, T) ... int
   */

  //insert a leaf into a binary search tree in the correct place
  ABST<T> insert(T other) {
    return new Node<T>(this.order, other, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }

  //determines if the item is present in the leaf based on the given comparator
  boolean present(T other) {
    return false;
  }

  //returns the leftmost item of the leaf
  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  //helps return the leftmost item of the leaf
  T helperLeft(T data) {
    return data;
  }

  //returns the all but the leftmost item of a tree
  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  //purpose statement
  ABST<T> rightAcc(T left) {
    return this;
  }

  //determines if this leaf is the same as the given ABST
  boolean sameTree(ABST<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return that.sameLeaf(this);
  }

  //determines if this leaf is the same as the given leaf
  boolean sameLeaf(Leaf<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return this.order.equals(that.order);
  }

  //determines if this leaf is the same as the given node
  boolean sameNode(Node<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return false;
  }

  //determines if this leaf as the same data as the given tree
  boolean sameData(ABST<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return that.sameLeaf(this);
  }

  //helps determines if this leaf as the same data as the given tree
  boolean sameDataHelper(ABST<T> that) {
    return true;
  }

  //builds a list of items in the tree in sorted order
  IList<T> buildList() {
    return new MtList<T>();
  }


}

//represents a node of a binary search tree
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /*
   * Fields: 
   *  this.data ... T
   *  this.left ... ABST<T>
   *  this.right ... ABST<T>
   *  this.order ... Comparator<T>
   * Methods:
   *  this.insert(T) ... ABST<T>
   *  this.present(T) ... boolean
   *  this.getLeftmost() ... T
   *  this.helperLeft(T) ... T
   *  this.getRight() ... ABST<T>
   *  this.rightAcc(T) ... ABST<T>
   *  this.sameTree(ABST<T>) ... boolean
   *  this.sameLeaf(Leaf<T>) ... boolean
   *  this.sameNode(Node<T>) ... boolean
   *  this.sameData(ABST<T>) ... boolean
   *  this.sameDataHelper(ABST<T>) ... boolean
   *  this.inTree(T) ... boolean
   *  this.buildList() ... IList<T>
   * Methods of Fields:
   *  this.order.compare(T, T) ... int
   *  this.left.insert(T) ... ABST<T>
   *  this.right.insert(T) ... ABST<T>
   *  this.left.present(T) ... boolean
   *  this.right.present(T) ... boolean
   *  this.left.getLeftmost() ... T
   *  this.right.getLeftmost() ... T
   *  this.left.helperLeft(T) ... T
   *  this.right.helperLeft(T) ... T
   *  this.left.getRight() ... T
   *  this.right.getRight() ... T
   *  this.left.rightAcc(T) ... ABST<T>
   *  this.right.rightAcc(T) ... ABST<T>
   *  this.left.sameTree(ABST<T>) ... boolean
   *  this.right.sameTree(ABST<T>) ... boolean
   *  this.left.sameLeaf(Leaf<T>) ... boolean
   *  this.right.sameLeaf(Leaf<T>) ... boolean
   *  this.left.sameNode(Node<T>) ... boolean
   *  this.right.sameNode(Node<T>) ... boolean
   *  this.left.sameData(ABST<T>) ... boolean
   *  this.right.sameData(ABST<T>) ... boolean
   *  this.left.sameDataHelper(ABST<T>) ... boolean
   *  this.right.sameDataHelper(ABST<T>) ... boolean
   *  this.left.inTree(T) ... boolean
   *  this.right.inTree(T) ... boolean
   *  this.left.buildList() ... IList<T>
   *  this.right.buildList() ... IList<T>
   */

  //insert a node into a binary search tree in the correct place
  ABST<T> insert(T other) {
    if (this.order.compare(this.data, other) > 0) {
      return new Node<T>(this.order, this.data, this.left.insert(other), this.right);
    }

    else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(other));
    }
  }

  //determines if the item is present in the tree based on the given comparator
  boolean present(T other) {
    return this.order.compare(this.data, other) == 0
        || this.left.present(other)
        || this.right.present(other);
  }


  //returns the leftmost item of the tree
  T getLeftmost() {
    return this.left.helperLeft(this.data);
  }

  //helps return the leftmost item of the tree
  T helperLeft(T data) {
    return this.left.helperLeft(this.data);
  }

  //returns all but the leftmost item of a tree
  ABST<T> getRight() {
    return this.rightAcc(this.getLeftmost());
  }

  //builds the right side of a tree
  //Acc: keeps track of the left most item in the tree
  ABST<T> rightAcc(T left) {
    if (this.order.compare(this.data, left) == 0) {
      return this.right;
    }

    else {
      return new Node<T>(this.order, this.data, this.left.rightAcc(left), this.right);
    }
  }

  //determines if this tree is the same as the given tree
  boolean sameTree(ABST<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return that.sameNode(this);
  }

  //determines if this tree is the same as the given leaf
  boolean sameLeaf(Leaf<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return false;
  }

  //determines if this node is the same as the given node
  boolean sameNode(Node<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return this.order.compare(this.data, that.data) == 0
        && this.left.sameTree(that.left)
        && this.right.sameTree(that.right);
  }

  //determines if this node is the same data as the given tree
  boolean sameData(ABST<T> that) {

    /*
     * Fields: none
     * Methods:
     *  that.insert(T) ... ABST<T>
     *  that.present(T) ... boolean
     *  that.getLeftmost() ... T
     *  that.helperLet(T) ... T
     *  that.getRight() ... ABST<T>
     *  that.rightAcc(T) ... ABST<T>
     *  that.sameTree(ABST<T>) ... boolean
     *  that.sameNode(Node<T>) ... boolean
     *  that.sameData(ABST<T>) ... boolean
     *  that.sameDataHelper(ABST<T>) ... boolean
     *  that.inTree(T) ... boolean
     *  that.buildList() ... IList<T>
     */

    return that.sameDataHelper(this)
        && this.sameDataHelper(that);
  }

  //helps determine if this node is the same data as the given tree
  boolean sameDataHelper(ABST<T> that) {
    return that.present(this.data)
        && this.left.sameDataHelper(that)
        && this.right.sameDataHelper(that);
  }


  //builds a list of items in the tree in sorted order
  IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }

}

//represents a book
class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  /*
   * Fields:
   *  this.title ... String
   *  this.author ... String
   *  this.price ... int
   * Methods: None
   * 
   * Methods of Fields: None
   */
}

//represents a person
class Person {
  String name;
  int age;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  /*
   * Fields:
   *  this.name ... String
   *  this.age ... int
   * Methods: none
   * 
   * Methods of Fields: none
   * 
   */
}


//sorts the books alphabetically by their title
class BooksByTitle implements Comparator<Book> {

  /*
   * Fields: none
   * Methods:
   *  this.compare(Book, Book) ... int
   * Methods of Fields: none
   */

  //compares the books by title
  public int compare(Book o1, Book o2) {
    return o1.title.compareTo(o2.title);
  }
}

//sorts the books alphabetically by their author name
class BooksByAuthor implements Comparator<Book> {

  /*
   * Fields: none
   * Methods:
   *  this.compare(Book, Book) ... int
   * Methods of Fields: none
   */

  //compares the books by author
  public int compare(Book o1, Book o2) {
    return o1.author.compareTo(o2.author);
  }
}

//sorts the books by their price
class BooksByPrice implements Comparator<Book> {

  /*
   * Fields: none
   * Methods:
   *  this.compare(Book, Book) ... int
   * Methods of Fields: none
   */

  //compares the books by price
  public int compare(Book o1, Book o2) {
    return o1.price - o2.price;
  }
}

//sorts the person by their age
class PersonByAge implements Comparator<Person> {

  /*
   * Fields: none
   * Methods: 
   *  this.compare(Person, Person) ... int
   * Methods of Fields:
   */

  //compares the people by age
  public int compare(Person o1, Person o2) {
    return o1.age - o2.age;
  }
}


//examples of binary search trees
class ExmaplesABST {
  Book b1 = new Book("Harry Potter", "JK Rowling", 30);
  Book b2 = new Book("Green Eggs and ham", "Dr. Suess", 15);
  Book b3 = new Book("Wonder", "R J Palacio", 9);
  Book b4 = new Book("Percy Jackson", "Rick Riordan", 25);
  Book b5 = new Book("Smile", "Raina Telgemeler", 5);
  Book b6 = new Book("If you gave a mouse a cookie", "Laura Numeroff", 14);
  Book b7 = new Book("It ends with us", "Colleen Hoover", 28);
  Book b8 = new Book("It starts with us", "Colleen Hoover", 40);
  Book b9 = new Book("It starts with us", "Colleen Hoover", 40);
  Person p1 = new Person("Anne", 19);
  Person p2 = new Person("Bob", 18);
  Person p3 = new Person("Carl", 20);
  Person p4 = new Person("Denis", 12);
  Person p5 = new Person("Edna", 10);
  Person p6 = new Person("Fanks", 10);

  IList<Book> emptyBooks = new MtList<Book>();
  IList<Person> emptyPerson = new MtList<Person>();

  IList<Book> book1 = new ConsList<Book>(this.b1, this.emptyBooks);
  IList<Book> book2 = new ConsList<Book>(this.b1, new ConsList<Book>(b2, this.emptyBooks));
  IList<Book> book3 = new ConsList<Book>(this.b1, new ConsList<Book>(b2, 
      new ConsList<Book>(b3, this.emptyBooks)));

  IList<Book> prices = new ConsList<Book>(this.b5, new ConsList<Book>(this.b3, 
      new ConsList<Book>(this.b6, new ConsList<Book>(this.b2, new ConsList<Book>(this.b7, 
          new ConsList<Book>(this.b1, new ConsList<Book>(this.b8,  this.emptyBooks)))))));

  IList<Book> titles = new ConsList<Book>(this.b2, new ConsList<Book>(b1, new ConsList<Book>(b4, 
      new ConsList<Book>(b5, new ConsList<Book>(b3, this.emptyBooks)))));

  IList<Book> authors = new ConsList<Book>(b7, new ConsList<Book>(b2, new ConsList<Book>(b1, 
      new ConsList<Book>(b5, new ConsList<Book>(b4, this.emptyBooks)))));

  IList<Person> ages = new ConsList<Person>(p5, new ConsList<Person>(p4, new ConsList<Person>(p2, 
      new ConsList<Person>(p1, new ConsList<Person>(p3, this.emptyPerson))))); 



  //leaves for different comparators
  ABST<Book> leafTitle = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leafAuthor = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> leafPrice = new Leaf<Book>(new BooksByPrice());
  ABST<Person> leafAge = new Leaf<Person>(new PersonByAge());


  //sorting ABST by price

  //                               15
  //                        /               \
  //                      9                  30
  //                    /    \             /      \
  //                 5        14         28         40
  //               /    \    /   \      /   \      /   \
  //             leaf leaf leaf  leaf leaf leaf leaf  leaf

  ABST<Book> five = new Node<Book>(new BooksByPrice(), this.b5, 
      this.leafPrice, this.leafPrice);

  ABST<Book> fourteen = new Node<Book>(new BooksByPrice(), this.b6, 
      this.leafPrice, this.leafPrice);

  ABST<Book> twentyEight = new Node<Book>(new BooksByPrice(), this.b7, 
      this.leafPrice, this.leafPrice);

  ABST<Book> fourty = new Node<Book>(new BooksByPrice(), this.b8, 
      this.leafPrice, this.leafPrice);

  ABST<Book> priceLeft = new Node<Book>(new BooksByPrice(), this.b3, 
      this.five, this.fourteen);

  ABST<Book> priceRight = new Node<Book>(new BooksByPrice(), this.b1, 
      this.twentyEight, this.fourty);

  ABST<Book> sortByPrice = new Node<Book>(new BooksByPrice(), this.b2, 
      this.priceLeft, this.priceRight);


  //sorting ABST by title


  //                   Harry Potter
  //                /               \
  //     Green eggs and ham          Smile
  //             /   \             /        \
  //          leaf   leaf    Percy Jackson  Wonder
  //                             /    \      /   \
  //                           leaf  leaf  leaf leaf

  ABST<Book> percyJackson = new Node<Book>(new BooksByTitle(), this.b4, 
      this.leafTitle, this.leafTitle);

  ABST<Book> wonder = new Node<Book>(new BooksByTitle(), this.b3, 
      this.leafTitle, this.leafTitle);

  ABST<Book> greenEggs = new Node<Book>(new BooksByTitle(), this.b2, 
      this.leafTitle, this.leafTitle);

  ABST<Book> smile = new Node<Book>(new BooksByTitle(), this.b5, 
      this.leafTitle, this.wonder);

  ABST<Book> titleLeft = new Node<Book>(new BooksByTitle(), this.b2, 
      this.leafTitle, this.leafTitle);

  ABST<Book> titleRight = new Node<Book>(new BooksByTitle(), this.b5, 
      this.percyJackson, this.wonder);

  ABST<Book> sortByTitle = new Node<Book>(new BooksByTitle(), this.b1, 
      this.titleLeft, this.titleRight);



  //sorting ABST by title in different order


  //                   Percy Jackson
  //                /               \
  //          Harry Potter          Smile
  //             /   \             /      \
  //      Green Eggs  leaf       leaf     Wonder
  //                                        /   \
  //                                      leaf leaf

  ABST<Book> titleDataLeft = new Node<Book>(new BooksByTitle(), this.b5, 
      this.leafTitle, this.wonder);

  ABST<Book> titleDataRight = new Node<Book>(new BooksByTitle(), this.b1, 
      this.greenEggs, this.leafTitle);

  ABST<Book> titleDataFinal = new Node<Book>(new BooksByTitle(), this.b4, 
      this.titleDataLeft, this.titleDataRight);


  //sorting ABST by author

  //                  JK Rowling
  //              /               \
  //          Dr. Suess         Raina Telgemler
  //          /       \             /        \
  //  Colleen Hoover   leaf       leaf     Rick Riordan
  //     /      \                            /       \ 
  //   leaf    leaf                        leaf     leaf

  ABST<Book> colleenHoover = new Node<Book>(new BooksByAuthor(), this.b7, 
      this.leafAuthor, this.leafAuthor);

  ABST<Book> rickRiordan = new Node<Book>(new BooksByAuthor(), this.b4, 
      this.leafAuthor, this.leafAuthor);

  ABST<Book> jkRowling = new Node<Book>(new BooksByAuthor(), this.b1, 
      this.leafAuthor, this.leafAuthor);

  ABST<Book> drSuess = new Node<Book>(new BooksByAuthor(), this.b2, 
      this.leafAuthor, this.leafAuthor);

  ABST<Book> raina = new Node<Book>(new BooksByAuthor(), this.b5, 
      this.leafAuthor, this.leafAuthor);

  ABST<Book> authorLeft = new Node<Book>(new BooksByAuthor(), this.b2, 
      this.colleenHoover, this.leafAuthor);

  ABST<Book> authorRight = new Node<Book>(new BooksByAuthor(), this.b5, 
      this.leafAuthor, this.rickRiordan);

  ABST<Book> sortByAuthor = new Node<Book>(new BooksByAuthor(), this.b1, 
      this.authorLeft, this.authorRight);

  //sorting ABST by age

  //                               18
  //                        /               \
  //                      12                  19
  //                    /    \             /      \
  //                 10      leaf        leaf       20
  //               /    \                          /   \
  //             leaf leaf                       leaf  leaf


  ABST<Person> ten = new Node<Person>(new PersonByAge(), this.p5, 
      this.leafAge, this.leafAge);

  ABST<Person> twelve = new Node<Person>(new PersonByAge(), this.p4, 
      this.leafAge, this.leafAge);

  ABST<Person> twenty = new Node<Person>(new PersonByAge(), this.p3, 
      this.leafAge, this.leafAge);

  ABST<Person> eighteen = new Node<Person>(new PersonByAge(), this.p2, 
      this.leafAge, this.leafAge);

  ABST<Person> ageLeft = new Node<Person>(new PersonByAge(), this.p4, 
      this.ten, this.leafAge);

  ABST<Person> ageRight = new Node<Person>(new PersonByAge(), this.p1, 
      this.leafAge, this.twenty);

  ABST<Person> sortByAge = new Node<Person>(new PersonByAge(), this.p2, 
      this.ageLeft, this.ageRight);

  //examples for tests
  ABST<Book> title1 = new Node<Book>(new BooksByTitle(), this.b4, 
      this.greenEggs, this.leafTitle);

  ABST<Book> price1 = new Node<Book>(new BooksByPrice(), this.b6, 
      this.leafPrice, this.twentyEight);

  //tests the compare method
  void testCompare(Tester t) {
    t.checkExpect(new PersonByAge().compare(p2, p1), -1);
    t.checkExpect(new PersonByAge().compare(p4, p5), 2);
    t.checkExpect(new BooksByTitle().compare(b2, b1), -1);
    t.checkExpect(new BooksByTitle().compare(b3, b4), 7);
    t.checkExpect(new BooksByAuthor().compare(b3, b7), 15);
    t.checkExpect(new BooksByAuthor().compare(b6, b4), -6);
    t.checkExpect(new BooksByPrice().compare(b4, b5), 20);
    t.checkExpect(new BooksByPrice().compare(b3, b4), -16);
    t.checkExpect(new BooksByTitle().compare(b8, b9), 0);
    t.checkExpect(new BooksByAuthor().compare(b9, b8), 0);
    t.checkExpect(new BooksByPrice().compare(b8, b9), 0);
    t.checkExpect(new PersonByAge().compare(p6, p5), 0);
  }


  //tests the insert method
  void testInsert(Tester t) {
    t.checkExpect(this.leafAge.insert(p1), new Node<Person>(new PersonByAge(), this.p1, 
        this.leafAge, this.leafAge));

    t.checkExpect(this.leafAuthor.insert(b1), new Node<Book>(new BooksByAuthor(), this.b1, 
        this.leafAuthor, this.leafAuthor));

    t.checkExpect(this.leafPrice.insert(b2), new Node<Book>(new BooksByPrice(), this.b2, 
        this.leafPrice, this.leafPrice));

    t.checkExpect(this.leafTitle.insert(b3), new Node<Book>(new BooksByTitle(), this.b3, 
        this.leafTitle, this.leafTitle));

    t.checkExpect(this.colleenHoover.insert(b1), new Node<Book>(new BooksByAuthor(), this.b7, 
        this.leafAuthor, this.jkRowling));

    t.checkExpect(this.titleRight.insert(b2), new Node<Book>(new BooksByTitle(),this.b5, 
        title1, wonder));

    t.checkExpect(this.priceLeft.insert(b7), new Node<Book>(new BooksByPrice(), this.b3, 
        this.five, this.price1));

    t.checkExpect(this.ageRight.insert(p2), new Node<Person>(new PersonByAge(), this.p1, 
        this.eighteen, this.twenty));
  }


  //tests the present method
  void testPresent(Tester t) {
    t.checkExpect(this.leafAuthor.present(b1), false);
    t.checkExpect(this.leafPrice.present(b4), false);
    t.checkExpect(this.leafTitle.present(b3), false);
    t.checkExpect(this.leafAge.present(p2), false);
    t.checkExpect(this.authorLeft.present(b1), false);
    t.checkExpect(this.sortByTitle.present(b3), true);
    t.checkExpect(this.sortByAuthor.present(b2), true);
    t.checkExpect(this.priceLeft.present(b5), true);
    t.checkExpect(this.fourteen.present(b6), true);
    t.checkExpect(this.fourteen.present(b1), false);
    t.checkExpect(this.twenty.present(p1), false);
    t.checkExpect(this.ageRight.present(p3), true);
    t.checkExpect(this.sortByAge.present(p3), true);
    t.checkExpect(this.ageLeft.present(p1), false);
  }


  //tests the getLeftmost method
  void testGetLeftmost(Tester t) {
    t.checkException(new RuntimeException("No leftmost item of an empty tree"),
        new Leaf<Book>(new BooksByTitle()), "getLeftmost");
    t.checkException(new RuntimeException("No leftmost item of an empty tree"),
        new Leaf<Book>(new BooksByAuthor()), "getLeftmost");
    t.checkException(new RuntimeException("No leftmost item of an empty tree"),
        new Leaf<Book>(new BooksByPrice()), "getLeftmost");
    t.checkException(new RuntimeException("No leftmost item of an empty tree"),
        new Leaf<Person>(new PersonByAge()), "getLeftmost");
    t.checkExpect(this.five.getLeftmost(), this.b5);
    t.checkExpect(this.twenty.getLeftmost(), this.p3);
    t.checkExpect(this.percyJackson.getLeftmost(), this.b4);
    t.checkExpect(this.colleenHoover.getLeftmost(), this.b7);
    t.checkExpect(this.five.getLeftmost(), this.b5);
    t.checkExpect(this.sortByAge.getLeftmost(), this.p5);
    t.checkExpect(this.titleRight.getLeftmost(), this.b4);
  }

  //tests the helperLeft method
  void testHelperLeft(Tester t) {
    t.checkExpect(this.ageLeft.helperLeft(p4), p5);
    t.checkExpect(this.authorLeft.helperLeft(b1), b7);
    t.checkExpect(this.sortByAge.helperLeft(p1), p5);
    t.checkExpect(this.sortByPrice.helperLeft(b3), b5);
    t.checkExpect(this.sortByTitle.helperLeft(b2), b2);
    t.checkExpect(this.sortByAuthor.helperLeft(b4), b7);
    t.checkExpect(this.leafAge.helperLeft(p2), p2);
    t.checkExpect(this.leafAuthor.helperLeft(b2), b2);
    t.checkExpect(this.leafPrice.helperLeft(b1), b1);
    t.checkExpect(this.leafTitle.helperLeft(b3), b3);
  }


  //tests the getRight method
  void testGetRight(Tester t) {
    t.checkException(new RuntimeException("No right of an empty tree"),
        new Leaf<Book>(new BooksByTitle()), "getRight");
    t.checkException(new RuntimeException("No right of an empty tree"),
        new Leaf<Book>(new BooksByAuthor()), "getRight");
    t.checkException(new RuntimeException("No right of an empty tree"),
        new Leaf<Book>(new BooksByPrice()), "getRight");
    t.checkException(new RuntimeException("No right of an empty tree"),
        new Leaf<Person>(new PersonByAge()), "getRight");

    t.checkExpect(this.twenty.getRight(), this.leafAge);
    t.checkExpect(this.percyJackson.getRight(), this.leafTitle);
    t.checkExpect(this.colleenHoover.getRight(), this.leafAuthor);
    t.checkExpect(this.five.getRight(), this.leafPrice);

    t.checkExpect(this.sortByAge.getRight(), new Node<Person>(new PersonByAge(), this.p2, 
        this.twelve, this.ageRight));

    t.checkExpect(this.sortByAuthor.getRight(), new Node<Book>(new BooksByAuthor(), this.b1, 
        this.drSuess, this.authorRight));

    t.checkExpect(this.sortByTitle.getRight(), new Node<Book>(new BooksByTitle(), this.b1, 
        this.leafTitle, this.titleRight));

    t.checkExpect(this.priceLeft.getRight(), new Node<Book>(new BooksByPrice(), this.b3, 
        this.leafPrice, this.fourteen));
  }


  //tests the rightAcc method
  void testRightAcc(Tester t) {
    t.checkExpect(this.twenty.rightAcc(this.p3), this.leafAge);
    t.checkExpect(this.percyJackson.rightAcc(this.b4), this.leafTitle);
    t.checkExpect(this.colleenHoover.rightAcc(this.b7), this.leafAuthor);
    t.checkExpect(this.five.rightAcc(this.b5), this.leafPrice);

    t.checkExpect(this.sortByAge.rightAcc(this.p5), new Node<Person>(new PersonByAge(), this.p2, 
        this.twelve, this.ageRight));

    t.checkExpect(this.sortByAuthor.rightAcc(this.b7), new Node<Book>(new BooksByAuthor(), this.b1,
        this.drSuess, this.authorRight));

    t.checkExpect(this.sortByTitle.rightAcc(this.b2), new Node<Book>(new BooksByTitle(), this.b1, 
        this.leafTitle, this.titleRight));

    t.checkExpect(this.priceLeft.rightAcc(this.b5), new Node<Book>(new BooksByPrice(), this.b3, 
        this.leafPrice, this.fourteen));
  }


  //examples of sameTree and sameData
  ABST<Book> sortByAuthor2 = new Node<Book>(new BooksByAuthor(), this.b1, 
      this.authorLeft, this.authorRight);

  ABST<Person> ageLeftSame = new Node<Person>(new PersonByAge(), this.p5, 
      this.leafAge, this.twelve);

  ABST<Book> titleRightSame = new Node<Book>(new BooksByTitle(), this.b4, 
      this.leafTitle, this.smile);

  ABST<Book> authorRightSame = new Node<Book>(new BooksByAuthor(), this.b4,
      this.raina, this.leafAuthor);



  //tests the sameTree method
  void testSameTree(Tester t) {
    t.checkExpect(this.ten.sameTree(this.ten), true);
    t.checkExpect(this.leafTitle.sameTree(this.leafAuthor), false);
    t.checkExpect(this.leafPrice.sameTree(this.leafTitle), false);
    t.checkExpect(this.leafAge.sameTree(this.leafAge), true);
    t.checkExpect(this.ageLeft.sameTree(this.ageRight), false);
    t.checkExpect(this.sortByAuthor.sameTree(this.sortByAuthor2), true);
    t.checkExpect(this.fourteen.sameTree(this.fourteen), true);
    t.checkExpect(this.leafAge.sameTree(ageLeft), false);
    t.checkExpect(this.titleLeft.sameTree(this.leafTitle), false);
  }

  //tests the sameLeaf method
  void testSameLeaf(Tester t) {
    t.checkExpect(this.leafTitle.sameLeaf((Leaf<Book>)this.leafAuthor), false);
    t.checkExpect(this.leafPrice.sameLeaf((Leaf<Book>)this.leafTitle), false);
    t.checkExpect(this.leafAge.sameLeaf((Leaf<Person>)this.leafAge), true);
    t.checkExpect(this.leafPrice.sameLeaf((Leaf<Book>)this.leafAuthor), false);
    t.checkExpect(this.ageLeft.sameLeaf((Leaf<Person>)this.leafAge), false);
    t.checkExpect(this.titleRight.sameLeaf((Leaf<Book>)this.leafTitle), false);
    t.checkExpect(this.sortByAuthor.sameLeaf((Leaf<Book>)this.leafAuthor), false);
    t.checkExpect(this.sortByPrice.sameLeaf((Leaf<Book>)this.leafPrice), false);
  }


  //tests the sameNode method
  void testSameNode(Tester t) {
    t.checkExpect(this.leafAuthor.sameNode((Node<Book>)this.drSuess), false);
    t.checkExpect(this.leafPrice.sameNode((Node<Book>)this.five), false);
    t.checkExpect(this.leafTitle.sameNode((Node<Book>)this.wonder), false);
    t.checkExpect(this.leafAge.sameNode((Node<Person>)this.twenty), false);
    t.checkExpect(this.greenEggs.sameNode((Node<Book>)this.wonder), false);
    t.checkExpect(this.greenEggs.sameNode((Node<Book>)this.wonder), false);
    t.checkExpect(this.greenEggs.sameNode((Node<Book>)this.greenEggs), true);
    t.checkExpect(this.wonder.sameNode((Node<Book>)this.wonder), true);
    t.checkExpect(this.titleLeft.sameNode((Node<Book>)this.titleLeft), true);
    t.checkExpect(this.sortByAuthor.sameNode((Node<Book>)this.sortByAuthor2), true);
  }

  //tests the sameData method
  void testSameData(Tester t) {
    t.checkExpect(this.leafAge.sameData(this.leafAge), true);
    t.checkExpect(this.leafPrice.sameData(this.leafAuthor), false);
    t.checkExpect(this.leafAuthor.sameData(this.leafTitle), false);
    t.checkExpect(this.ageLeftSame.sameData(this.ageLeft), true);
    t.checkExpect(this.titleRightSame.sameData(this.titleRight), true);
    t.checkExpect(this.authorRight.sameData(this.authorRightSame), true);
    t.checkExpect(this.titleLeft.sameData(this.titleRight), false);
    t.checkExpect(this.sortByTitle.sameData(this.titleDataFinal), true);
  }

  //tests the sameDataHelper method
  void testSameDataHelper(Tester t) {
    t.checkExpect(this.leafAge.sameDataHelper(this.leafAge), true);
    t.checkExpect(this.leafPrice.sameDataHelper(this.leafAuthor), true);
    t.checkExpect(this.sortByPrice.sameDataHelper(this.sortByTitle), false);
    t.checkExpect(this.ageLeftSame.sameDataHelper(this.ageLeft), true);
    t.checkExpect(this.titleRightSame.sameDataHelper(this.authorRight), false);
    t.checkExpect(this.authorRight.sameDataHelper(this.authorRightSame), true);
    t.checkExpect(this.titleLeft.sameDataHelper(this.titleRight), false);
    t.checkExpect(this.sortByTitle.sameDataHelper(this.titleDataFinal), true);
  }

  //tests the buildList method
  void testBuildList(Tester t) {
    t.checkExpect(this.sortByAge.buildList(), this.ages);
    t.checkExpect(this.sortByTitle.buildList(), this.titles);
    t.checkExpect(this.sortByPrice.buildList(), this.prices);
    t.checkExpect(this.sortByAuthor.buildList(), this.authors);
    t.checkExpect(this.leafAge.buildList(), this.emptyPerson);
    t.checkExpect(this.leafPrice.buildList(), this.emptyBooks);
    t.checkExpect(this.leafAuthor.buildList(), this.emptyBooks);
    t.checkExpect(this.leafTitle.buildList(), this.emptyBooks);
  }
}

