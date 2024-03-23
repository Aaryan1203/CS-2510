import java.util.HashMap;
import tester.Tester;

class Book {
  String name;
  double price;
  int year;

  Book(String name, double price, int year) {
    this.name = name;
    this.price = price;
    this.year = year;
  }

}

class Author {
  String name;
  int yob;

  Author(String name, int yob) {
    this.name = name;
    this.yob = yob;
  }

  //compares this author and a given object
  public boolean equals(Object o) {
    if (o instanceof Author) {
      Author that = (Author)o;
      return this.name.equals(that.name) && 
          this.yob == that.yob;
    }
    else {
      return false;
    }
  }

  //compute a hashCode for this Author
  public int hashCode() {
    return this.name.hashCode() * this.yob * 37;
  }

}

class Posn {
  int x;
  int y;

  Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  //compares this author and a given object
  public boolean equals(Object o) {
    if (o instanceof Posn) {
      Posn that = (Posn)o;
      return that.canEqual(this) && this.x == that.x && 
          this.y == that.y;
    }
    else {
      return false;
    }
  }
  
  //compute a hashCode for this Author
  public int hashCode() {
    return this.x * this.y * 37;
  }
  
  //should the given be an instance of this?
  boolean canEqual(Object o) {
    return o instanceof Posn;
  }

}

class ColorPosn extends Posn {
  String color;

  ColorPosn(int x, int y, String color) {
    super(x,y);
    this.color = color;
  }
  
  //compares this ColorPosn to the given ColorPosn
  //compares this author and a given object
  public boolean equals(Object o) {
    if (o instanceof ColorPosn) {
      ColorPosn that = (ColorPosn)o;
      return that.canEqual(this) && 
          this.x == that.x && 
          this.y == that.y
          && this.color.equals(that.color);
    }
    else {
      return false;
    }
  }
  
  //compute a hashCode for this Author
  public int hashCode() {
    return this.x * this.y * this.color.hashCode() * 37;
  }
  
  //should the given be an instance of this?
  boolean canEqual(Object o) {
    return o instanceof ColorPosn;
  }
  
}

class GamePiece {
  int score;
  String description;

  GamePiece(int score, String des) {
    this.score = score;
    this.description = des;
  }
  
  
}

class ExamplesPosns {
  Posn p1a = new Posn(3,4);
  Posn p1b = new Posn(3,4);
  Posn p1c = new Posn(3,5);
  Posn p2 = new Posn(6,8);
  ColorPosn cp = new ColorPosn(3,4,"red");
  GamePiece spaceship = new GamePiece(100, "spaceship");
  GamePiece invader = new GamePiece(50, "invader");
  HashMap<Posn, GamePiece> gameDetails = new HashMap<Posn, GamePiece>();

  void init() {
    this.gameDetails.put(this.p1a, this.spaceship);
    this.gameDetails.put(this.p2, this.invader);
  }

  void testHash(Tester t) {
    System.out.print(p2.hashCode());
    this.init();
    t.checkExpect(this.gameDetails.get(this.p1a), this.spaceship);
    t.checkExpect(this.gameDetails.get(this.p1b), this.spaceship);
    t.checkExpect(this.p1b.equals(p1c), false);
    t.checkExpect(this.p1b.hashCode() == p1c.hashCode(), false);
    t.checkExpect(this.p1b.equals(p1a), true);
    t.checkExpect(this.p1b.hashCode() == p1a.hashCode(), true);

    t.checkExpect(p1a.equals(cp), false);
    t.checkExpect(cp.equals(p1a), false);
  }
}
