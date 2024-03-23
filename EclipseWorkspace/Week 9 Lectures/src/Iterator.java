import java.util.Iterator;
import java.util.NoSuchElementException;

class Evens implements Iterator<Integer> {
  int currentEven;
  
  Evens() {
    this.currentEven = 0;
  }
  
  //is there another even number to produce
  public boolean hasNext() {
    return true;
  }

  //produce the next even number 
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException("no more itmes");
    }
    else {
      int result = this.currentEven;
      this.currentEven = this.currentEven + 2;
      return result;
    }
  }
  
}

class sumOfSquares implements Iterator<Integer> {
  int curTotal;

  sumOfSquares() {
    this.curTotal = 1;
  }
  //is there another square to produce
  public boolean hasNext() {
    return true;
  }

  //produce the next sum
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException("no more items");
    }
    else {
      int result = curTotal;
      this.curTotal = this.curTotal + (this.curTotal + 1) * (this.curTotal + 1);
      return result;
    }
  }
}








