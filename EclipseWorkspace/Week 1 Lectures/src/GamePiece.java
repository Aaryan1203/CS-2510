/*
interface IGamePiece{

}

class Spaceship implements IGamePiece{
  Location loc;
  String color;
  double speed;

  Spaceship (Location location, String color, double speed){
    this.loc = location;
    this.color = color;
    this.speed = speed;
  }
}

class Invader implements IGamePiece{
  Location loc;
  String color;
  int bullets;

  Invader(Location location, String color, int bullets){
    this.loc = location;
    this.color = color;
    this.bullets = bullets;
  }
}
class Location {
  int x;
  int y;

  Location (int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class ExamplesGame {
  Location loc30_40 = new Location (30, 40);
  Location loc80_120 = new Location (80, 120);
  IGamePiece ship1 = new Spaceship(this.loc30_40, "red", 67.0);
  IGamePiece ship2 = new Spaceship(this.loc80_120, "green", 100.75);
  IGamePiece invader1 = new Invader(new Location(500, 360), "black", 55);
  IGamePiece invader2 = new Invader(new Location(500, 360), "yellow", 35);

  ExamplesGame() {}
}

*/