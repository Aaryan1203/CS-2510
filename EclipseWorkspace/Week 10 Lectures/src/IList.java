import tester.Tester;
interface ILoInt {
  //sort this list in ascending order
  ILoInt sort();
  //insert the given number into this sorted list
  ILoInt insert(int n);
  //compute the length of this list
  int length();
}

class MtLoInt implements ILoInt {
  //sort this list in ascending order
  public ILoInt sort() { return this; }
  //insert the given number into this sorted list
  public ILoInt insert(int n) { return new ConsLoInt(n, this); }
  //compute the length of this empty list
  public int length() {
    return 0;
  }
}

class ConsLoInt implements ILoInt {
  int first;
  ILoInt rest;
  ConsLoInt(int first, ILoInt rest) {
    this.first = first;
    this.rest = rest;
  }
  //sort this list in ascending order
  public ILoInt sort() {
    return this.rest.sort().insert(this.first);
  }
  //insert the given number into this sorted list
  public ILoInt insert(int n) {
    if (this.first < n) {
      return new ConsLoInt(this.first, this.rest.insert(n));
    }
    else {
      return new ConsLoInt(n, this);
    }
  }
  //compute the length of this non-empty list
  public int length() {
    return 1 + this.rest.length();
  }
}
class ExamplesInts {
  ILoInt mt = new MtLoInt();
  ILoInt one = new ConsLoInt(2, new ConsLoInt(3,
      new ConsLoInt(1, new ConsLoInt(4,
          this.mt))));
}
