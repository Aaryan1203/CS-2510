import tester.Tester;

// to represent a list of Strings
interface ILoString {

  // combine all Strings in this list into one
  String combine();

  //Produces a new list, sorted in alphabetical order
  ILoString sort();

  //inserts a string into the list of strings
  ILoString insert(String first);

  //determines if the list of strings is sorted or not
  boolean isSorted();

  //determines if the list of strings is sorted or not
  //Accumulator: keeps track of previous booleans
  boolean isSortedAcc(String first);

  //alternates this list with the given list
  ILoString interleave(ILoString other);

  //merges the two lists in alphabetical order
  ILoString merge(ILoString other);

  //merges the two lists together in alphabetical order
  boolean mergeHelp(String first);

  //reverses this list of strings
  ILoString reverse();

  //inserts the strings in reverse order
  ILoString reverseHelp(String first);

  //determines if the list contains pairs of identical strings
  boolean isDoubledList();

  //determines if the list contains pairs of identical strings
  boolean isDoubledHelp(String next);

  //determines whether this list contains the same words both ways
  boolean isPalindromeList();

}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString(){}

  /*
  FIELDS: none

  METHODS
  this.combine() ... String
  this.sort() ... ILoString
  this.insert(String) ... ILoString
  this.isSorted() ... boolean
  this.isSortedAcc(String) ... boolean
  this.interleave(ILoString) ... ILoString
  this.merge(ILoString) ... ILoString
  this.mergeHelp(String) ... boolean
  this.reverse() ... ILoString
  this.reverseHelp(String) ... ILoString
  this.isDoubledList() ... boolean
  this.isDoubledHelp(String) ... boolean
  this.isPalindromeList() ... boolean

  METHODS FOR FIELDS: none
   */

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  //Sorts the empty list of strings
  public ILoString sort() {
    return this;
  }

  //inserts the new string into the empty list of strings
  public ILoString insert(String first) {
    return new ConsLoString(first, this);
  }

  //determines if the empty string is sorted or not
  public boolean isSorted() {
    return true;
  }

  //determines if the empty string is sorted
  public boolean isSortedAcc(String first) {
    return true;
  }

  //alternates this empty list with the given list
  public ILoString interleave(ILoString other) {

    /*
     * Fields: none
     * Methods:
     *  other.combine() ... String
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.isSortedAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merged(ILoString) ... ILoString
     *  other.mergeHelp(String) ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelp(String) ... ILoString
     *  other.isDoubledList() ... boolean
     *  other.isDoubledHelp(String) ... boolean
     *  other.isPalindromeList() ... boolean
     */

    return other;
  }

  //merges the empty list with the other list in alphabetical order
  public ILoString merge(ILoString other) {

    /*
     * Fields: none
     * Methods:
     *  other.combine() ... String
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.isSortedAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeHelp(String) ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelp(String) ... ILoString
     *  other.isDoubledList() ... boolean
     *  other.isDoubledHelp(String) ... boolean
     *  other.isPalindromeList() ... boolean
     */

    return other;
  }

  //merges the two lists of strings together
  public boolean mergeHelp(String other) {

    /*
     * Fields: none
     * Methods:
     *  original.combine() ... String
     *  original.sort() ... ILoString
     *  original.insert(String) ... ILoString
     *  original.isSorted() ... boolean
     *  original.isSortedAcc(String) ... boolean
     *  original.interleave(ILoString) ... ILoString
     *  original.merge(ILoString) ... ILoString
     *  original.mergeHelp(String) ... boolean
     *  original.reverse() ... ILoString
     *  original.reverseHelp(String) ... ILoString
     *  original.isDoubledList() ... boolean
     *  original.isDoubledHelp(String) ... boolean
     *  original.isPalindromeList() ... boolean
     */

    return true;
  }  

  //reverses the empty list of strings
  public ILoString reverse() {
    return this;
  }

  //inserts the strings in reverse order
  public ILoString reverseHelp(String first) {
    return new ConsLoString(first, this);
  }

  //determines if the empty list has doubled strings
  public boolean isDoubledList() {
    return true;
  }

  //determines if the list contains pairs of identical strings
  public boolean isDoubledHelp(String next) {
    return false;
  }

  //determines whether this empty list contains the same words both ways
  public boolean isPalindromeList() {
    return true;
  }

}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;  
  }

  /*
     FIELDS:
     this.first ... String
     this.rest ... ILoString

     METHODS
     this.combine() ... String
     this.sort() ... ILoString
     this.insert(String) ... ILoString
     this.isSorted() ... boolean
     this.isSortedAcc(String) ... boolean
     this.interleave(ILoString) ... ILoString
     this.merge(ILoString) ... ILoString
     this.mergeHelp(String) ... boolean
     this.reverse() ... ILoString
     this.reverseHelp(String) ... ILoString
     this.isDoubledList() ... boolean
     this.isDoubledHelp(String) ... boolean
     this.isPalindromeList() ... boolean


     METHODS FOR FIELDS
     this.first.concat(String) ... String
     this.first.compareTo(String) ... int
     this.rest.combine() ... String
     this.rest.sort() ... ILoString
     this.rest.insert(String) ... ILoString
     this.rest.isSorted() ... boolean
     this.rest.isSortedAcc(String) ... boolean
     this.rest.interleave(ILoString) ... ILoString
     this.rest.merge(ILoString) ... ILoString
     this.rest.mergeHelp(String) ... boolean
     this.rest.reverse() ... ILoString
     this.rest.reverseHelp(String) ... ILoString
     this.rest.isDoubledList() ... boolean
     this.rest.isDoubledHelp(String) ... boolean
     this.rest.isPalindromeList() ... boolean

   */

  // combine all Strings in this list into one
  public String combine(){
    return this.first.concat(this.rest.combine());
  }

  //Sorts the non-empty list of strings
  public ILoString sort() {
    return this.rest.sort().insert(this.first);
  }

  //insert the string into the already sorted rest
  public ILoString insert(String first) {
    if (this.first.toLowerCase().compareTo(first.toLowerCase()) <= 0) {
      return new ConsLoString(this.first, this.rest.insert(first));
    }

    else {
      return new ConsLoString(first, this);
    }
  }

  //determines if the non-empty list of strings is sorted
  public boolean isSorted() {
    return this.rest.isSortedAcc(this.first);
  }

  //determines if the non-empty list of strings is sorted
  public boolean isSortedAcc(String first) {
    return (first.toLowerCase().compareTo(this.first.toLowerCase()) <= 0) 
        && (this.rest.isSortedAcc(this.first)); 
  }

  //returns this non-empty list with another list
  public ILoString interleave(ILoString other) {

    /*
     * Fields: none
     * Methods:
     *  other.combine() ... String
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.isSortedAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeHelp(String) ... boolean
     *  other.isDoubledList() ... boolean
     *  other.isDoubledHelp(String) ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelp(String) ... ILoString
     *  other.isPalindromeList() ... boolean
     */

    return new ConsLoString(this.first, other.interleave(this.rest));
  }

  //merges the non-empty list with another list in alphabetical order
  public ILoString merge(ILoString other) {

    /*
     * Fields: none
     * Methods:
     *  other.combine() ... String
     *  other.sort() ... ILoString
     *  other.insert(String) ... ILoString
     *  other.isSorted() ... boolean
     *  other.isSortedAcc(String) ... boolean
     *  other.interleave(ILoString) ... ILoString
     *  other.merge(ILoString) ... ILoString
     *  other.mergeHelp(String) ... boolean
     *  other.isDoubledList() ... boolean
     *  other.isDoubledHelp(String) ... boolean
     *  other.reverse() ... ILoString
     *  other.reverseHelp(String) ... ILoString
     *  other.isPalindromeList() ... boolean
     */

    if (other.mergeHelp(this.first)) {
      return new ConsLoString(this.first, this.rest.merge(other));
    }

    else {
      return other.merge(this);
    }
  }

  //merges the two lists together in alphabetical order
  public boolean mergeHelp(String first) {
    return this.first.toLowerCase().compareTo(first.toLowerCase()) > 0; 
  }

  //reverses the non-empty list of strings
  public ILoString reverse() {
    return this.rest.reverse().reverseHelp(this.first);
  }

  //inserts the strings in reverse order
  public ILoString reverseHelp(String first) {
    return new ConsLoString(this.first, this.rest.reverseHelp(first));
  }

  //determines if the non-empty list is doubled
  public boolean isDoubledList() {
    return this.rest.isDoubledHelp(this.first);
  }

  //determines if the list contains pairs of identical strings
  public boolean isDoubledHelp(String next) {
    return this.first.equals(next) && this.rest.isDoubledList();
  }

  //determines whether this list contains the same words both ways
  public boolean isPalindromeList() {
    return this.interleave(this.reverse()).isDoubledList();
  }

}

// to represent examples for lists of strings
class ExamplesStrings {

  ILoString empty = new MtLoString();
  ILoString hello = new ConsLoString("Hello", new MtLoString());
  ILoString how = new ConsLoString("How", new MtLoString());
  ILoString are = new ConsLoString("Are", new MtLoString());
  ILoString you = new ConsLoString("You", new MtLoString());


  //Unsorted lists
  ILoString whatsUp = new ConsLoString("Whats ", 
      new ConsLoString ("Up", this.empty));

  ILoString howAreYou = new ConsLoString("How ", 
      new ConsLoString ("Are ", 
          new ConsLoString("You", this.empty)));

  ILoString mary = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", this.empty)))));

  ILoString hiHowAreYouDoingToday = new ConsLoString("Hi, ", 
      new ConsLoString("How ",
          new ConsLoString("Are ",
              new ConsLoString("You ",
                  new ConsLoString("Doing ", 
                      new ConsLoString("Today ", this.empty))))));


  //Sorted lists
  ILoString whatsUpSorted = new ConsLoString("Up", 
      new ConsLoString("Whats ", this.empty));

  ILoString howAreYouSorted = new ConsLoString("Are ", 
      new ConsLoString("How ", 
          new ConsLoString("You", this.empty)));

  ILoString marySorted = new ConsLoString("a ",
      new ConsLoString("had ",
          new ConsLoString("lamb.",
              new ConsLoString("little ",
                  new ConsLoString("Mary ", this.empty)))));

  //insert list
  ILoString maryInsert = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", 
                      new ConsLoString("Wow", this.empty))))));

  //interleaved lists
  ILoString howAreYouMaryInterleave = new ConsLoString("How ", 
      new ConsLoString("Mary ",
          new ConsLoString("Are ",
              new ConsLoString("had ",
                  new ConsLoString("You", 
                      new ConsLoString("a ", 
                          new ConsLoString("little ",
                              new ConsLoString("lamb.", this.empty))))))));

  ILoString maryYouInterleave = new ConsLoString("Mary ",
      new ConsLoString("You",
          new ConsLoString("had ",
              new ConsLoString("a ",
                  new ConsLoString("little ",
                      new ConsLoString("lamb.", this.empty))))));

  ILoString helloHowInterleave = new ConsLoString("Hello", 
      new ConsLoString("How", this.empty));

  //doubled lists
  ILoString colorDoubled = new ConsLoString("Red", 
      new ConsLoString("Red",
          new ConsLoString("Blue",
              new ConsLoString("Blue",
                  new ConsLoString("Green", 
                      new ConsLoString("Green", this.empty))))));

  ILoString colorNotDoubled = new ConsLoString("Red", 
      new ConsLoString("Red",
          new ConsLoString("Blue",
              new ConsLoString("Blue",
                  new ConsLoString("Green", this.empty)))));


  //reversed lists
  ILoString whatsUpReverse = new ConsLoString("Up", 
      new ConsLoString ("Whats ", this.empty));

  ILoString maryReverse = new ConsLoString("lamb.",
      new ConsLoString("little ",
          new ConsLoString("a ",
              new ConsLoString("had ",
                  new ConsLoString("Mary ", this.empty)))));

  ILoString hiHowAreYouDoingTodayReverse = new ConsLoString("Today ", 
      new ConsLoString("Doing ",
          new ConsLoString("You ",
              new ConsLoString("Are ",
                  new ConsLoString("How ", 
                      new ConsLoString("Hi, ", this.empty))))));

  //reversed helper lists
  ILoString helloHowReverse = new ConsLoString("Hello", 
      new ConsLoString("How", this.empty));

  //lists for merged
  ILoString redBlueViolet = new ConsLoString("Blue", 
      new ConsLoString("Purple",
          new ConsLoString("Violet", this.empty)));

  ILoString greenRedYellow = new ConsLoString("Green", 
      new ConsLoString("Red",
          new ConsLoString("Yellow", this.empty)));

  ILoString mergedInterleaveColors = new ConsLoString("Blue", 
      new ConsLoString("Green",
          new ConsLoString("Purple",
              new ConsLoString("Red",
                  new ConsLoString("Violet", 
                      new ConsLoString("Yellow", this.empty))))));

  ILoString howAreYouMaryMerged = new ConsLoString("a ", 
      new ConsLoString("Are ",
          new ConsLoString("had ",
              new ConsLoString("How ",
                  new ConsLoString("lamb.", 
                      new ConsLoString("little ", 
                          new ConsLoString("Mary ",
                              new ConsLoString("You ", this.empty))))))));

  ILoString helloAreMerged = new ConsLoString("Are",
      new ConsLoString("Hello", this.empty));

  //lists for Palindrome
  ILoString colorsEvenPalindrome = new ConsLoString("Blue", 
      new ConsLoString("Red",
          new ConsLoString("Purple",
              new ConsLoString("Purple",
                  new ConsLoString("Red", 
                      new ConsLoString("Blue", this.empty))))));

  ILoString colorsOddPalindrome = new ConsLoString("Blue", 
      new ConsLoString("Red",
          new ConsLoString("Purple",
              new ConsLoString("Red", 
                  new ConsLoString("Blue", this.empty)))));

  ILoString aPalindrome = new ConsLoString("aa",
      new ConsLoString("a", this.empty));



  //test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return t.checkExpect(this.mary.combine(), "Mary had a little lamb.")
        && t.checkExpect(this.whatsUp.combine(), "Whats Up")
        && t.checkExpect(this.howAreYou.combine(), "How Are You");
  }

  //tests the sort method
  boolean testSort(Tester t) {
    return t.checkExpect(this.empty.sort(), this.empty)
        && t.checkExpect(this.hello.sort(), this.hello)
        && t.checkExpect(this.whatsUp.sort(), this.whatsUpSorted)
        && t.checkExpect(this.howAreYou.sort(), this.howAreYouSorted)
        && t.checkExpect(this.mary.sort(), this.marySorted);
  }

  //tests the insert method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.empty.insert("Hello"), this.hello)
        && t.checkExpect(this.hello.insert("Are"), this.helloAreMerged)
        && t.checkExpect(this.mary.insert("Wow"), this.maryInsert);
  }

  //tests the isSorted method
  boolean testIsSorted(Tester t) {
    return t.checkExpect(this.empty.isSorted(), true)
        && t.checkExpect(this.hello.isSorted(), true)
        && t.checkExpect(this.whatsUp.isSorted(), false)
        && t.checkExpect(this.whatsUpSorted.isSorted(), true)
        && t.checkExpect(this.mary.isSorted(), false)
        && t.checkExpect(this.howAreYouSorted.isSorted(), true);
  }

  //tests the isSortedAcc method
  boolean testIsSortedAcc(Tester t) {
    return t.checkExpect(this.empty.isSortedAcc("hello"), true)
        && t.checkExpect(this.mary.isSortedAcc("hello"), false)
        && t.checkExpect(this.hiHowAreYouDoingToday.isSortedAcc("A"), false);
  }

  //tests the interleave method
  boolean testInterleave(Tester t) {
    return t.checkExpect(this.howAreYou.interleave(this.mary), this.howAreYouMaryInterleave)
        && t.checkExpect(this.mary.interleave(this.you), this.maryYouInterleave)
        && t.checkExpect(this.hello.interleave(this.how), this.helloHowInterleave)
        && t.checkExpect(this.empty.interleave(this.hiHowAreYouDoingToday), this.hiHowAreYouDoingToday)
        && t.checkExpect(this.hiHowAreYouDoingToday.interleave(this.empty), this.hiHowAreYouDoingToday)
        && t.checkExpect(this.redBlueViolet.interleave(this.greenRedYellow), this.mergedInterleaveColors);
  }

  
  //tests the merge method
  boolean testMerge(Tester t) {
    return t.checkExpect(this.empty.merge(this.are), this.are)
        && t.checkExpect(this.hello.merge(this.empty), this.hello)
        && t.checkExpect(this.hello.merge(this.are), this.helloAreMerged)
        && t.checkExpect(this.redBlueViolet.merge(this.greenRedYellow), this.mergedInterleaveColors);
  }

  //tests the mergeHelp method
  boolean testMergeHelp(Tester t) {
    return t.checkExpect(this.empty.mergeHelp("Hello"), true)
        && t.checkExpect(this.how.mergeHelp("Hello"), true)
        && t.checkExpect(this.mary.mergeHelp("Zebra"), false)
        && t.checkExpect(this.mary.mergeHelp("Wow"), false);
  }
   
  //tests the reverse method
  boolean testReverse(Tester t) {
    return t.checkExpect(this.hello.reverse(), this.hello)
        && t.checkExpect(this.empty.reverse(), this.empty)
        && t.checkExpect(this.whatsUp.reverse(), this.whatsUpReverse)
        && t.checkExpect(this.mary.reverse(), this.maryReverse)
        && t.checkExpect(this.hiHowAreYouDoingToday.reverse(), this.hiHowAreYouDoingTodayReverse);
  }

  //tests the reverseHelp method
  boolean testReverseHelp(Tester t) {
    return t.checkExpect(this.empty.reverseHelp("How"), this.how)
        && t.checkExpect(this.hello.reverseHelp("How"), this.helloHowReverse)
        && t.checkExpect(this.mary.reverseHelp("Wow"), this.maryInsert);
  }

  //tests the isDoubledList method
  boolean testDoubled(Tester t) {
    return t.checkExpect(this.empty.isDoubledList(), true)
        && t.checkExpect(this.mary.isDoubledList(), false)
        && t.checkExpect(this.colorDoubled.isDoubledList(), true)
        && t.checkExpect(this.colorNotDoubled.isDoubledList(), false);
  }

  //tests the isDoubledHelp method
  boolean testDoubledHelp(Tester t) {
    return t.checkExpect(this.empty.isDoubledHelp("Hello"), false)
        && t.checkExpect(this.hello.isDoubledHelp("Hello"), true)
        && t.checkExpect(this.colorDoubled.isDoubledHelp("Red"), false)
        && t.checkExpect(this.mary.isDoubledHelp("Mary"), false);
  }

  //tests the isPalindromeList method
  boolean testPalindrome(Tester t) {
    return t.checkExpect(this.empty.isPalindromeList(), true)
        && t.checkExpect(this.hello.isPalindromeList(), true)
        && t.checkExpect(this.mary.isPalindromeList(), false)
        && t.checkExpect(this.colorsEvenPalindrome.isPalindromeList(), true)
        && t.checkExpect(this.colorsOddPalindrome.isPalindromeList(), true)
        && t.checkExpect(this.howAreYou.isPalindromeList(), false)
        && t.checkExpect(this.aPalindrome.isPalindromeList(), false);
  }


}