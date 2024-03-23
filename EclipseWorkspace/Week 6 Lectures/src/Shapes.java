
import tester.Tester;
interface IShape { 
  <R> R accept(IShapeVisitor<R> vis);
}


class Circle implements IShape {
  CartPt location;
  int radius;
  String color;

  Circle(CartPt loc, int r, String color) {
    this.location = loc;
    this.radius = r;
    this.color = color;
  }

  //apply a function to this circle
  public <R> R accept(IShapeVisitor<R> vis) {
    return vis.visitCircle(this);
  }
}

class Rect implements IShape {
  CartPt location; 
  int w; 
  int h;
  String color;

  Rect(CartPt location, int w, int h, String color) {
    this.location = location;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  //apply's a function to this rectangle
  public <R> R accept(IShapeVisitor<R> vis) {
    return vis.visitRect(this);
  }
}

//to represent a Cartesian point
class CartPt {
  int x;
  int y;
  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /* TEMPLATE
  FIELDS
  ... this.x ...          -- int
  ... this.y ...          -- int
  METHODS
  ... this.distTo0() ...        -- double
  ... this.distTo(CartPt) ...   -- double
  ... this.sameLocation(Location) ... boolean
   */
  // to compute the distance form this point to the origin
  public double distTo0(){
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
  // to compute the distance form this point to the given point
  public double distTo(CartPt pt){
    /* Everything in the class template plus:
     FIELDS of pt
     ... pt.x ...          -- int
     ... pt.y ...          -- int
     METHODS of pt
     ... pt.distTo0() ...        -- double
     ... pt.distTo(CartPt) ...   -- double
     ... pt.sameLocation(Location) ... boolean
     */
    return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + 
        (this.y - pt.y) * (this.y - pt.y));
  }
  //is this location the same as the given one?
  boolean sameLocation(CartPt that) {
    /* Everything in the class template plus:
     FIELDS of pt
     ... pt.x ...          -- int
     ... pt.y ...          -- int
     METHODS of pt
     ... pt.distTo0() ...        -- double
     ... pt.distTo(CartPt) ...   -- double
     ... pt.sameLocation(Location) ... boolean
     */
    return this.x == that.x && this.y == that.y;
  }
}
class ExamplesShapes {
  ExamplesShapes() {}
  CartPt pt1 = new CartPt(0, 0);
  CartPt pt2 = new CartPt(3, 4);
  CartPt pt3 = new CartPt(7, 1);
  IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
  IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");
  IShape r1 = new Rect(new CartPt(50, 50), 30, 30, "red");
  IShape r2 = new Rect(new CartPt(50, 50), 50, 40, "red");
  IShape r3 = new Rect(new CartPt(20, 40), 10, 20, "green");
  
  IList<IShape> mtShapes = new MtList<IShape>();
  IList<IShape> shapes = new ConsList<IShape>(this.c1, 
      new ConsList<IShape>(this.r1, this.mtShapes));
  
  boolean testShapeToArea(Tester t) {
    return t.checkExpect(this.shapes.map(new ShapeToArea()), 
        new ConsList<Double>(100 * Math.PI, 
            new ConsList<Double>(30 * 30 * 
                1.0, new MtList<Double>())));
  }
  
}
