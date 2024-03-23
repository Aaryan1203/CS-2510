import java.util.function.Function;
import java.util.function.Predicate;

interface IShapeVisitor<R> {
  //apply's a function to the given circle
  R visitCircle(Circle c);
  //apply's a function to the given rectangle
  R visitRect(Rect r);

}

class ShapeToArea implements IShapeVisitor<Double>, Function<IShape, Double> {

  //compute area of a given circle
  public Double visitCircle(Circle c) {
    return Math.PI * c.radius * c.radius;
  }

  //compute area of a given rectangle
  public Double visitRect(Rect r) {
    return r.w * r.h * 1.0;
  }

  //applies this function to the given shape
  public Double apply(IShape t) {
    return t.accept(this);
  }
}

class ShapeDistTo0 implements IShapeVisitor<Double> {

  //compute the distance to 0 of the given circle
  public Double visitCircle(Circle c) {
    return c.location.distTo0() - c.radius;
  }

  //computes the distance to 0 of the given rectangle
  public Double visitRect(Rect r) {
    return r.location.distTo0();
  }
}

class ShapeGrow implements IShapeVisitor<IShape> {
  int increase;
  
  ShapeGrow(int inc) {
    this.increase = inc;
  }
  
  //grow the circle by the given increment
  public IShape visitCircle(Circle c) {
    return new Circle(c.location, c.radius + this.increase, c.color);
  }

  //grow the rectangle by the given increment
  public IShape visitRect(Rect r) {
    return new Rect(r.location, r.w + this.increase, r.h + this.increase, r.color);
  }
  
}

interface IListVisitor<X, Y> {
  //apply an operation to the given empty list
  Y visitMt(MtList<X> mt);
  
  //apply an operation to the given cons list
  Y visitCons(ConsList<X> cons);
}


class FilterVisitor<X> implements IListVisitor<X, IList<X>> {
  Predicate<X> pred;
  
  FilterVisitor(Predicate<X> pred) {
    this.pred = pred;
  }
  
  //filter the given empty list
  public IList<X> visitMt(MtList<X> mt) {
    return mt;
  }

  //filters the given cons list
  public IList<X> visitCons(ConsList<X> cons) {
    if (pred.test(cons.first)) {
      return new ConsList<X>(cons.first, cons.rest.accept(this));
    }
    
    else {
      return cons.rest.accept(this);
    }
  }
  
}

class ContainsA implements Predicate<String> {

  //does the given string contain an A
  public boolean test(String t) {
    return t.contains("a") || t.contains("A");
  }
  
}






