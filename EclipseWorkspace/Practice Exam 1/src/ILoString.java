import tester.Tester;

interface ILoString {
  boolean hasSameContentAs(ILoString other);
  boolean contains(String other);
  boolean isSubsetOf(ILoString other);
  ILoString removeDuplicates();
  
}

class MtLoString implements ILoString {

  public boolean hasSameContentAs(ILoString other) {
    return other.isSubsetOf(this);
  }

  public boolean contains(String other) {
    return false;
  }

  public boolean isSubsetOf(ILoString other) {
    return true;
  }

  public ILoString removeDuplicates() {
    return this;
  }
  
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  public boolean hasSameContentAs(ILoString other) {
    return this.isSubsetOf(other) && other.isSubsetOf(this);
  }

  public boolean contains(String other) {
    return this.first.equals(other)
        || this.rest.contains(other);
  }

  public boolean isSubsetOf(ILoString other) {
    return other.contains(this.first) && this.rest.isSubsetOf(other);
  }

  public ILoString removeDuplicates() {
    
    if (this.rest.contains(this.first)) {
      return this.rest.removeDuplicates();
    }
    else {
      return new ConsLoString(this.first, this.rest.removeDuplicates());
    }

  }
  
}

class ExamplesILo {
  
  ILoString string1 = new ConsLoString("a", new ConsLoString("b", new ConsLoString ("c", new MtLoString())));
  ILoString string2 = new ConsLoString("c", new ConsLoString("b", new ConsLoString ("a", new MtLoString())));
  ILoString string3 = new ConsLoString("a", new ConsLoString("B", new ConsLoString ("c", new MtLoString())));
  ILoString string4 = new ConsLoString("a", new ConsLoString("a", new ConsLoString ("c", new MtLoString())));
  ILoString string5 = new ConsLoString("a", new ConsLoString("b", new ConsLoString ("b", new MtLoString())));


  
  boolean testHasSameContent(Tester t) {
    return t.checkExpect(this.string1.hasSameContentAs(string2), true)
        && t.checkExpect(this.string1.hasSameContentAs(string3), false);
  }
  
  boolean testDuplicate(Tester t) {
    return t.checkExpect(this.string1.removeDuplicates(), this.string1)
        && t.checkExpect(this.string4.removeDuplicates(), new ConsLoString("a", new ConsLoString ("c", new MtLoString())))
        && t.checkExpect(this.string5.removeDuplicates(), new ConsLoString("a", new ConsLoString ("b", new MtLoString())));
  }
}