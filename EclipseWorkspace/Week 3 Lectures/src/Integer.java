//represents a list of integers
interface ILoInt {
  //does this list satisfy the 3 requrements
  boolean satisfies();
  //accumulators: booleans to keep track of which requirements have been satisfied
  boolean satisfyAcc(boolean even, boolean posAndOdd, boolean bet5and10);

}

//represents an empty list of integers
class MtLoInt implements ILoInt {

  @Override
  public boolean satisfies() {
    return false;
  }

  public boolean satisfyAcc(boolean even, boolean posAndOdd, boolean bet5and10) {
    return even && posAndOdd && bet5and10;
  }


}

//represents a non-empty list of integers
class ConsLoInt implements ILoInt {
  int first;
  ILoInt rest;

  ConsLoInt(int first, ILoInt rest) {
    this.first = first;
    this.rest = rest;
  }


  public boolean satisfies() {
    return this.satisfyAcc(false, false, false);
  }


  public boolean satisfyAcc(boolean even, boolean posAndOdd, boolean bet5and10) {
    return (even && posAndOdd & bet5and10) || 
        this.rest.satisfyAcc(even || this.first % 2 == 0, 
                                posAndOdd || this.first != 0 && this.first > 0, 
                                bet5and10 || this.first >= 5 && this.first <= 10);
  }

}

class ExamplesLoInt {
  ILoInt mt = new MtLoInt();
  ILoInt list1 = new ConsLoInt(3, new ConsLoInt(6, this.mt));
  ILoInt list2 = new ConsLoInt(3, new ConsLoInt(4, this.mt));
  ILoInt list3 = new ConsLoInt(3, new ConsLoInt(6, new ConsLoInt(-1, this.mt)));

}