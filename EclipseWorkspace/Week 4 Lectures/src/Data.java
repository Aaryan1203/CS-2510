import tester.Tester;
class Date {
  int month;
  int day;
  int year;
  Date(int m, int d, int y) {

    Utils utils = new Utils();
    this.month = utils.checkRange(m, 1, 12, "Not a valid month");
    this.day = utils.checkRange(d, 1, 31, "Not a valid day");
    this.year = utils.checkRange(y, 2000, 2100, "Not a valid year");
  }


  /* fields:
   *  this.month ... int
   *  this.day   ... int
   *  this.year  ... int
   * methods:
   * 
   */

  //convenience constructor
  Date(int m, int d) {
    this(m, d, 2023);
  }

}

class Utils {

  //checks that a given number is within a given range
  int checkRange(int n, int low, int high, String message) {
    if (n >= low && n <= high) {
      return n;
    }
    else {
      throw new IllegalArgumentException(message);
    }
  }

}

class ExamplesDates {
  Date today = new Date(2, 1, 2023);
  //Date badDate = new Date(-2, 55, -300);

  //testing the Date constructor
  boolean testConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Not a valid month"), 
        "Date",
        -2, 55, -300);
  }

  boolean testUtils(Tester t) {
    return t.checkExpect(new Utils().checkRange(4, 0, 10, "no exception"), 4)
        && t.checkException(new IllegalArgumentException("nope"), new Utils(), "checkRange", 5, 10, 50, "nope");
  }

}