
import tester.Tester;
//a class to represent a spaceship
class Spaceship {
  Location loc;
  String color;
  int speed; //in mph
  Spaceship(Location location, String color, int speed) {
    this.loc = location;
    this.color = color;
    this.speed = speed;
  }
  /* fields:
   *  this.loc ... Location
   *  this.color ... String
   *  this.speed ... int
   * methods:
   *  this.reduceSpeed(int) ... int
   *  this.move(int, int) ... Spaceship
   *  this.inSameLocation(Spaceship) ... boolean
   * methods for fields:
   *  this.loc.shiftXY(int, int)
   *  this.loc.sameLocation(int, int)  
   */
  //returns the speed of this spaceship reduced by the given rate
  int reduceSpeed(int rate) {
    return this.speed - this.speed * rate / 100;
  }
  //create a new Spaceship whose location is moved by the given dx and dy
  Spaceship move(int dx, int dy) {
    return new Spaceship(this.loc.shiftXY(dx, dy), this.color, this.speed);
  }
  //is this spaceship in the same location as the given one?
  boolean inSameLocation(Spaceship that) {
    /* fields of that: (can access the fields of that because it is a 
spaceship)
     *  that.loc .. Location
     *  that.color .. String
     *  that.speed .. int
     * methods of that:
     *  that.reduceSpeed(int) .. int
     *  that.move(int, int) ... Spaceship 
     *  that.sameLocation(Spaceship) ... boolean
     * methods of fields of that:
     *  that.loc.shiftXY(int, int) 
     */
    return this.loc.sameLocation(that.loc);
  }
  //Exercise: design a method to determine if this spaceship is touching any
  //border of a 600 x 400 rectangle (use ||)
}
//a class to represent a point on the Cartesian plane
class Location {
  int x;
  int y;
  Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /* fields: 
   *  this.x ... int
   *  this.y ... int
   * methods:
   *  this.shiftXY(int, int) ... Location 
   *  this.sameLocation(Location) .. boolean
   */
  //creates a new Location that is shifted by the given dx and dy
  Location shiftXY(int dx, int dy) {
    return new Location(this.x + dx, this.y + dy);
  }
  //is this location the same as the given one?
  boolean sameLocation(Location that) {
    /* fields of that (allowed to access the fields of that since it is 
also a Location):
     *  that.x ... int
     *  that.y ... int
     * methods of that:
     *  that.shiftXY(int, int) ... Location
     *  that.sameLocation(Location) ... boolean
     */
    return this.x == that.x && this.y == that.y;
  }
}
class ExamplesSpaceship {
  Location loc30_40 = new Location(30, 40);
  Location loc60_80 = new Location(60, 80);
  Spaceship ship1 = new Spaceship(this.loc30_40, "red", 100);
  Spaceship ship2 = new Spaceship(this.loc60_80, "green", 120);
  //tests for reducing speed of a spaceship
  boolean testReduceSpeed(Tester t) {
    return t.checkExpect(this.ship1.reduceSpeed(50), 50) &&
        t.checkExpect(this.ship1.reduceSpeed(20), 80);
  }
  //tests for moving a spaceship
  boolean testMove(Tester t) {
    return t.checkExpect(this.ship2.move(5, 10), 
        new Spaceship(new Location(65, 90), "green", 120)) &&
        t.checkExpect(this.ship1.move(0, 1), 
            new Spaceship(new Location(30, 41), "red", 
                100)) &&
        t.checkExpect(this.loc30_40.shiftXY(1, 2), new Location(31,
            42));
  }
  //tests for same location
  boolean testSameLocation(Tester t) {
    return t.checkExpect(this.ship1.inSameLocation(this.ship2), false) &&
        t.checkExpect(this.ship1.inSameLocation(ship1), true) &&
        t.checkExpect(this.loc30_40.sameLocation(loc30_40), true) 
        &&
        t.checkExpect(this.loc30_40.sameLocation(loc30_40), true) 
        &&
        t.checkExpect(this.loc30_40.sameLocation(this.loc60_80), 
            false) ;
  }
}
