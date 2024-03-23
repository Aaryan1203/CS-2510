import java.awt.Color;
import tester.Tester;

class Ball {
  CartPt center;
  int radius;
  Color color;

  Ball(CartPt center, int radius, Color color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }



  /* fields:
   *  this.center ... CartPt
   *  this.radius ... int
   *  this.color ... Color
   * methods:
   *  this.area() ... Double
   *  this.circumference() ... Double
   *  this.distanceTo() ... Double
   *  this.overlaps() ... Boolean
   * methods for fields:
   *  this.center.distance() ... Double
   */

  // Returns the area of this ball
  double area() {
    return Math.PI * Math.pow(this.radius, 2);
  }
  
  double circumference() {
    return Math.PI * this.radius * 2;
  }

  double distanceTo(Ball b1) {
    return this.center.distance(b1.center);
  }

  boolean overlaps(Ball b1) {
    double distance = this.distanceTo(b1);
    double totalRadius = this.radius + b1.radius;
    return distance < totalRadius;
  }

}

class CartPt {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /* fields: 
   *  this.x ... int
   *  this.y ... int
   * methods: 
   *  this.distance() ... double
   * methods for fields:
   * 
   */

  double distance(CartPt other) {
    double distanceX = Math.abs(other.x - this.x);
    double distanceY = Math.abs(other.y - this.y);
    return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
  }
}

class ExamplesBall {
  Ball b = new Ball(new CartPt(0, 0), 5, Color.BLUE);
  Ball b2 = new Ball(new CartPt(3, 5), 4, Color.GREEN);


  boolean testBall(Tester t) {
    return t.checkInexact(b.area(), 78.5, 0.001) 
        && t.checkInexact(b.circumference(), 31.4, .001) 
        && t.checkInexact(b.distanceTo(b2), 5.831, 0.001) 
        && t.checkExpect(b.overlaps(b2), true);
  }
}
