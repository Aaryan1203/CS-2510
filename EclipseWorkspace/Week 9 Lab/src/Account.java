import tester.Tester;

// Represents a List of Accounts
interface ILoAccount {

  //returns the account with the given account number otherwise throws an exception
  Account findAccount(int accNum);

  //removes the given account from the list of accounts
  ILoAccount remove(Account acct);
}


//Represents the empty List of Accounts
class MtLoAccount implements ILoAccount {

  MtLoAccount() {}

  /*
   * Fields:
   *  None
   * Methods:
   *  this.findAccount(accNum) ... Account
   * Methods of Fields:
   *  None
   */

  //returns the account with the given account number otherwise throws an exception
  public Account findAccount(int accNum) {
    throw new RuntimeException("Account cannot be found");
  }

  //EFFECT: removes the given account from the list of accounts
  public ILoAccount remove(Account acct) {
    throw new RuntimeException("Account cannot be found");
  }

}


// Represents a non-empty List of Accounts...
class ConsLoAccount implements ILoAccount {

  Account first;
  ILoAccount rest;

  ConsLoAccount(Account first, ILoAccount rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields:
   *  this.first ... Account
   *  this.rest ... ILoAccount
   * Methods:
   *  this.findAccount(int) ... Account
   * Methods of Fields:
   *  None
   */


  //returns the account with the given account number otherwise throws an exception
  public Account findAccount(int accNum) {
    if (this.first.sameAccount(accNum)) {
      return this.first;
    }
    else {
      return this.rest.findAccount(accNum);
    }
  }

  //removes the given account from the list of accounts
  public ILoAccount remove(Account acct) {
    if (this.first.sameAccount(acct.accountNum)) {
      return this.rest;
    }
    else {
      return new ConsLoAccount(this.first, this.rest.remove(acct));
    }
  }

}

// Represents a bank account
abstract class Account {

  int accountNum;  // Must be unique
  int balance;     // Must remain above zero (others Accts have more restrictions)
  String name;     // Name on the account

  Account(int accountNum, int balance, String name) {
    this.accountNum = accountNum;
    this.balance = balance;
    this.name = name;
  }

  /*
   * Fields:
   *  this.accountNum ... int
   *  this.balance ... int
   *  this.name ... String
   * Methods:
   *  this.sameAccount(int) ... boolean
   *  this.withdraw(int) ... int
   *  this.deposit(int) ... int
   * Methods of Fields:
   *  None
   */

  //determines is this account is the same as the given account number
  boolean sameAccount(int accNum) {
    return this.accountNum == accNum;
  }

  //EFFECT: Withdraw the given amount
  //Return the new balance
  abstract int withdraw(int amount);


  //EFFECT: Deposit the given funds into this account
  //Return the new balance
  abstract int deposit(int funds);
}

//Bank Account Examples and Tests
class Examples {
  Account check1;
  Account savings1;
  Account credit1;
  Account check2;
  Account savings2;
  Account credit2;
  Bank bank;

  Examples() { 
    reset(); 
  }

  // Initializes accounts to use for testing with effects.
  // We place inside reset() so we can "reuse" the accounts
  void reset() {

    // Initialize the account examples
    check1 = new Checking(1, 100, "First Checking Account", 20);
    savings1 = new Savings(2, 200, "First Savings Account", 2.5);
    credit1 = new Credit(3, 200, "First Credit Account", 500, 3);
    check2 = new Checking(4, 2000, "Second Checking Account", 500);
    savings2 = new Savings(5, 10000, "Second Savings Account", 1.5);
    credit2 = new Credit(6, 500, "Second Credit Account", 3000, 2);
    bank = new Bank("Bank 1");

  }

  // Tests the exceptions we expect to be thrown when
  // performing an "illegal" action.
  void testExceptions(Tester t) {
    reset();
    t.checkException("Test for invalid Checking withdraw",
        new RuntimeException("1000 is not available"),
        this.check1,
        "withdraw",
        1000);
    t.checkException("Test for invalid Saving withdraw",
        new RuntimeException("201 is not available"),
        this.savings1,
        "withdraw",
        201);
    t.checkException("Test for invalid Credit withdraw",
        new RuntimeException("301 is not available"),
        this.credit1,
        "withdraw",
        301);
    t.checkException("Test for invalid Account number withdraw",
        new RuntimeException("Account cannot be found"),
        this.bank.accounts,
        "findAccount",
        7);
    t.checkException("Test for invalid Account number withdraw",
        new RuntimeException("Account cannot be found"),
        this.bank.accounts,
        "findAccount",
        8);
    t.checkException("Test for invalid Credit deposit",
        new RuntimeException("Cannot deposit more than the balance"),
        this.credit2,
        "deposit",
        600);
    t.checkConstructorException(new IllegalArgumentException(
        "The balance cannot be less than the minimum"), 
        "Checking", 2, 200, "First Checking Account", 300);
    t.checkConstructorException(new IllegalArgumentException(
        "The balance cannot be more than the credit line"), 
        "Credit", 2, 400, "First Checking Account", 300, 2.5);

    Bank bank = new Bank("Bank");
    bank.add(check1);
    t.checkException("Test for depositing to a non-existing account",
        new RuntimeException("Account cannot be found"),
        bank,
        "deposit",
        3, 100);

    t.checkException("Test for withdrawing from a non-existing account",
        new RuntimeException("Account cannot be found"),
        bank,
        "withdraw",
        3, 100);
  }

  //tests the withdraw method for account
  void testWithdrawAccount(Tester t) {
    reset();
    t.checkExpect(this.check1.withdraw(79), 21);
    t.checkExpect(this.check1.withdraw(1), 20);
    t.checkExpect(this.check2.withdraw(1500), 500);
    t.checkExpect(this.savings1.withdraw(150), 50);
    t.checkExpect(this.savings2.withdraw(9000), 1000);
    t.checkExpect(this.credit1.withdraw(200), 400);
    t.checkExpect(this.credit2.withdraw(1000), 1500);
    t.checkExpect(this.credit2.withdraw(500), 2000);
  }

  //tests the deposit method for the account
  void testDepositAccount(Tester t) {
    reset();
    t.checkExpect(this.check1.deposit(80), 180);
    t.checkExpect(this.check1.deposit(20), 200);
    t.checkExpect(this.check2.deposit(1500), 3500);
    t.checkExpect(this.savings1.deposit(150), 350);
    t.checkExpect(this.savings2.deposit(9000), 19000);
    t.checkExpect(this.credit1.deposit(200), 0);
    t.checkExpect(this.credit2.deposit(300), 200);
    t.checkExpect(this.credit2.deposit(200), 0);
  }

  //tests the add method
  void testAdd(Tester t) {
    reset();
    t.checkExpect(this.bank.accounts, new MtLoAccount());
    this.bank.add(check1);
    t.checkExpect(this.bank.accounts, new ConsLoAccount(check1, new MtLoAccount()));
    this.bank.add(savings1);
    t.checkExpect(this.bank.accounts, new ConsLoAccount(savings1, 
        new ConsLoAccount(check1, new MtLoAccount())));
    reset();
    t.checkExpect(this.bank.accounts, new MtLoAccount());
    this.bank.add(credit2);
    t.checkExpect(this.bank.accounts, new ConsLoAccount(credit2, new MtLoAccount()));
  }


  //tests the findAcount method
  void testFindAccount(Tester t) {
    reset();
    this.bank.add(check1);
    this.bank.add(savings1);
    this.bank.add(credit2);
    t.checkExpect(this.bank.accounts.findAccount(2), this.savings1);
    t.checkExpect(this.bank.accounts.findAccount(6), this.credit2);
  }


  //tests the deposit method for banks
  void testDepositBank(Tester t) {
    reset();
    this.bank.add(check1);
    this.bank.add(savings1);
    this.bank.add(credit2);
    this.bank.add(savings2);
    this.bank.deposit(500, 2);
    t.checkExpect(this.savings1.balance, 700);
    this.bank.deposit(200, 1);
    t.checkExpect(this.check1.balance, 300);
  }

  //tests the withdraw method for banks
  void testWithdrawBank(Tester t) {
    reset();
    this.bank.add(check1);
    this.bank.add(savings1);
    this.bank.add(credit2);
    this.bank.add(savings2);
    this.bank.add(check2);
    this.bank.withdraw(200, 2);
    t.checkExpect(this.savings1.balance, 0);
    this.bank.withdraw(500, 4);
    t.checkExpect(this.check2.balance, 1500);
  }

  //tests the remove method
  void testRemove(Tester t) {
    reset();
    this.bank.add(check1);
    this.bank.add(savings1);
    this.bank.add(credit2);
    this.bank.add(savings2);
    this.bank.add(check2);
    t.checkExpect(this.bank.accounts, new ConsLoAccount(this.check2, 
        new ConsLoAccount(this.savings2, new ConsLoAccount(this.credit2, 
            new ConsLoAccount(this.savings1, new ConsLoAccount(this.check1,
                new MtLoAccount()))))));

    this.bank.removeAccount(4);
    t.checkExpect(this.bank.accounts,
        new ConsLoAccount(this.savings2, new ConsLoAccount(this.credit2, 
            new ConsLoAccount(this.savings1, new ConsLoAccount(this.check1,
                new MtLoAccount())))));

    this.bank.removeAccount(6);
    t.checkExpect(this.bank.accounts,
        new ConsLoAccount(this.savings2,  
            new ConsLoAccount(this.savings1, new ConsLoAccount(this.check1,
                new MtLoAccount()))));

    this.bank.removeAccount(1);
    t.checkExpect(this.bank.accounts,
        new ConsLoAccount(this.savings2,  
            new ConsLoAccount(this.savings1, 
                new MtLoAccount())));
  }

}

