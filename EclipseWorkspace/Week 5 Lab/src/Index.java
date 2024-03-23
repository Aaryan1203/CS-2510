import tester.Tester;

interface ILoString {
  // return the 0-based index of the first occurrence of str in this list
  // or -1 if it is not present
  int indexOf(String str);
  
  //keeps track of what index on the list it is currently on
  int indexOfAcc(int index, String str);
}

class MtLoString implements ILoString {

  //checks if the given string is in the empty list of strings
  public int indexOf(String str) {
    return -1;
  }

  //keeps track of what index on the list it is currently on
  public int indexOfAcc(int index, String str) {
    return -1;
  }

  
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  //checks if the given string is in the non-empty list of strings
  public int indexOf(String str) {
    return this.indexOfAcc(0, str);
  }

  //checks if the given string is in the empty list of strings
  public int indexOfAcc(int index, String str) {
    if (this.first.equals(str)) {
      return index;
    }
    else {
      return this.rest.indexOfAcc(index + 1, str);
    }
  }
  
}

class ExamplesStrings {
  ILoString mt = new MtLoString();
  ILoString appleBeeApple = new ConsLoString("apple", 
      new ConsLoString("bee", 
          new ConsLoString("apple", mt)));
  
 

  boolean testIndexOf(Tester t) {
    return t.checkExpect(this.mt.indexOf("apple"), -1)
        && t.checkExpect(this.appleBeeApple.indexOf("apple"), 0)
        && t.checkExpect(this.appleBeeApple.indexOf("bee"), 1)
        && t.checkExpect(this.appleBeeApple.indexOf("hello"), -1);

  }
  /*
  ILoString list1 = makeList("apple", "bee", "cat");
  ILoString list2 = makeList("dog", "egg", "goat");
  
  boolean testInterleaveAndMerge(Tester t) {
    return t.checkExpect(this.list1.interleave(this.list2), makeList("apple", "dog", "bee", "egg", "cat", "goat"))
        && t.checkExpect(this.list1.merge(this.list2), makeList("apple", "bee", "cat", "dog", "egg", "goat"));
  }
  */
}        
