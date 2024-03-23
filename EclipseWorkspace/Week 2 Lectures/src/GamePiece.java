
import tester.Tester;

interface IGamePiece {
  IGamePiece move(int dx, int dy);
}
//a class to represent a spaceship
class Spaceship implements IGamePiece {
  Location loc;
  String color;
  double speed; //in mph

  Spaceship(Location location, String color, double speed) {
    this.loc = location;
    this.color = color;
    this.speed = speed;
  }
  /* fields:
   *  this.loc ... Location
   *  this.color ... String
   *  this.speed ... double
   * methods:
   *  this.move(int, int) ... IGamePiece
   * methods for fields:
   *  this.loc.shiftXY(int, int)
   *  this.loc.sameLocation(int, int)  
   */

  public IGamePiece move(int dx, int dy) {
    return new Spaceship(this.loc.shiftXY(dx, dy), this.color, this.speed);

  }
}
//class to represent an invader
class Invader implements IGamePiece {
  Location loc;
  String color;
  int bullets;

  Invader(Location loc, String color, int bullets) {
    this.loc = loc;
    this.color = color;
    this.bullets = bullets;
  }
  /* fields:
   *  this.loc ... Location
   *  this.color ... String
   *  this.bullets ... int
   * methods:
   *  
   * methods for fields:
   *  this.loc.shiftXY(int, int)
   *  this.loc.sameLocation(int, int)  
   */

  //moves this Invader by the given dx and dy
  public IGamePiece move(int dx, int dy) {
    return new Invader(this.loc.shiftXY(dx, dy), this.color, this.bullets);
  }
}

//a class to represent a point on the Cartesian plane
class Location {
  int x;
  int y;

  Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  Location shiftXY(int dx, int dy) {
    return new Location(this.x + dx, this.y + dy);
  }
  //is this location the same as the given one?
  boolean sameLocation(Location that) {
    return this.x == that.x && this.y == that.y;
  }
  /* fields: 
   *  this.x ... int
   *  this.y ... int
   * methods:
   *  this.shiftXY(int, int) ... Location 
   *  this.sameLocation(Location) .. boolean
   */
}

class ExamplesGame {
  Location loc30_40 = new Location(30, 40);
  Location loc80_120 = new Location(80, 120);
  IGamePiece ship1 = new Spaceship(this.loc30_40, "red", 67.0);
  IGamePiece ship2 = new Spaceship(this.loc80_120, "green", 100.75);
  IGamePiece invader1 = new Invader(new Location(50, 800), "black", 60);
  IGamePiece invader2 = new Invader(new Location(50, 800), "yellow", 60);

  ExamplesGame() {}

  // this.ship1.move(1, 2) --> new Spaceship(new Location(31, 42), red, 67.0)
  // this.invader1.move(0, 3) --> new Invader(new Location(50, 803), black, 60)

}

