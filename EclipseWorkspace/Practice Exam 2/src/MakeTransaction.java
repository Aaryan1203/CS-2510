import tester.Tester;

interface ICard {

  int checkCard(int value, int lower, int upper, String message);
  boolean isExpired();
  boolean sameCard(ICard other);
  boolean sameCredit(CreditCard other);
  boolean sameDebit(DebitCard other);
  boolean sameGift(GiftCard other);
  void makeTransaction(Double subtract);
  boolean sameFields(int cnum, int year, int cvv);

}

abstract class ACard implements ICard {
  int cnum;
  int year;
  int cvv;

  ACard(int cnum, int year, int cvv) {
    this.cnum = this.checkCard(cnum, 9999, 100000, "cnum must have five digits and not start with 0");
    this.year = this.checkCard(year, 10, 99, "year must be a two digit number");
    this.cvv = this.checkCard(cvv, 99, 900, "cvv must be a three digit number that cannot start with a 0 or 9");
  }

  public int checkCard(int value, int lower, int upper, String message) {
    if (value > lower && value < upper) {
      return value;
    }
    else {
      throw new IllegalArgumentException(message);
    }
  }

  public boolean isExpired() {
    return year < 23;
  }

  public abstract boolean sameCard(ICard other);

  public boolean sameCredit(CreditCard other) {
    return false;
  }

  public boolean sameDebit(DebitCard other) {
    return false;
  }

  public boolean sameGift(GiftCard other) {
    return false;
  }

  public void makeTransaction(Double subtract) {
    return ;
  }

  public boolean sameFields(int cnum, int year, int cvv) {
    return this.cnum == cnum
        && this.year == year
        && this.cvv == cvv;
  }

}

class CreditCard extends ACard {
  String name;
  CreditCard(int cnum, int year, int cvv, String name) {
    super(cnum, year, cvv);
    this.name = name;
  }

  public boolean sameCard(ICard other) {
    return other.sameCredit(this);
  }

  public boolean sameCredit(CreditCard other) {
    return this.sameFields(other.cnum, other.year, other.cvv)
        && this.name.equals(other.name);
  }

}

class GiftCard extends ACard {
  Double balance;
  GiftCard(int cnum, int year, int cvv, Double balance) {
    super(cnum, year, cvv);
    this.balance = balance;
    if (this.balance < 0) {
      throw new IllegalArgumentException("Balance must be greater than 0");
    }
  }

  public void makeTransaction(Double subtract) {
    if (subtract > this.balance) {
      this.balance = 0.0;
    }
    else {
      this.balance = this.balance - subtract;
    }
  }

  public boolean sameCard(ICard other) {
    return other.sameGift(this);
  }

  public boolean sameGift(GiftCard other) {
    return Math.abs(this.balance - other.balance) < 0.001
        && this.sameFields(other.cnum, other.year, other.cvv);

  }

}

class DebitCard extends ACard {
  String name;
  Double balance;
  DebitCard(int cnum, int year, int cvv, String name, Double balance) {
    super(cnum, year, cvv);
    this.name = name;
    this.balance = balance;
    if (this.balance < 0) {
      throw new IllegalArgumentException("Balance must be greater than 0");
    }
  }

  public void makeTransaction(Double subtract) {
    if (subtract > this.balance) {
      this.balance = 0.0;
    }
    else {
      this.balance = balance - subtract;
    }
  }

  public boolean sameCard(ICard other) {
    return other.sameDebit(this);
  }

  public boolean sameDebit(DebitCard other) {
    return this.name.equals(other.name)
        && this.sameFields(other.cnum, other.year, other.cvv)
        && Math.abs(this.balance - other.balance) < 0.001;
  }

}

class ExamplesCard {
  CreditCard creditOne = new CreditCard(90000, 22, 191, "AMEX");
  CreditCard creditTwo = new CreditCard(90000, 24, 191, "Capital One");
  CreditCard creditThree = new CreditCard(90000, 22, 191, "AMEX");
  GiftCard giftOne = new GiftCard(19020, 21, 292, 100.0);
  GiftCard giftTwo = new GiftCard(19020, 21, 292, 100.0);
  GiftCard giftThree = new GiftCard(19020, 21, 292, 100.0);
  DebitCard debitOne = new DebitCard(19020, 21, 292, "AMEX", 100.0);
  DebitCard debitTwo = new DebitCard(19020, 26, 292, "Capital One", 100.0);
  DebitCard debitThree = new DebitCard(19020, 21, 292, "AMEX", 100.0);


  boolean testExpired(Tester t) {
    return t.checkExpect(this.creditOne.isExpired(), true)
        && t.checkExpect(this.creditTwo.isExpired(), false)
        && t.checkExpect(this.giftOne.isExpired(), true)
        && t.checkExpect(this.giftTwo.isExpired(), true)
        && t.checkExpect(this.debitOne.isExpired(), true)
        && t.checkExpect(this.debitTwo.isExpired(), false);
  }
//
//  boolean testSame(Tester t) {
//    return t.checkExpect(this.giftOne.sameCard(creditOne), false)
//        && t.checkExpect(this.creditThree.sameCard(creditOne), true)
//        & t.checkExpect(this.giftOne.sameCard(debitOne), false)
//        & t.checkExpect(this.debitOne.sameCard(giftOne), false)
//        & t.checkExpect(this.debitOne.sameCard(debitThree), true);
//  }

  void testTransaction(Tester t) {
    this.giftOne.makeTransaction(20.0);
    t.checkExpect(this.giftOne.balance, 80.0);
    this.giftTwo.makeTransaction(120.0);
    t.checkExpect(this.giftTwo.balance, 0.0);
    this.creditOne.makeTransaction(20.0);
    this.debitOne.makeTransaction(40.0);
    t.checkExpect(this.debitOne.balance, 60.0);
  }


}




