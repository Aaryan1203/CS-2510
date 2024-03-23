
// Represents a checking account
class Checking extends Account {

  int minimum; // The minimum account balance allowed

  Checking(int accountNum, int balance, String name, int minimum) {
    super(accountNum, balance, name);
    this.minimum = minimum;
    if (this.balance < this.minimum) {
      throw new IllegalArgumentException("The balance cannot be less than the minimum");
    }
  }

  //EFFECT: Withdraw the given amount
  //Return the new balance
  int withdraw(int amount) {
    if (this.balance - amount < this.minimum) {
      throw new RuntimeException(amount + " is not available");
    }
    else {
      this.balance = this.balance - amount;
      return this.balance;
    }
  }

  //EFFECT: Deposit the given funds into this account
  //Return the new balance
  int deposit(int funds) {
    this.balance = this.balance + funds;
    return this.balance;
  }
}

//Represents a credit line account
class Credit extends Account {

  int creditLine;  // Maximum amount accessible
  double interest; // The interest rate charged

  Credit(int accountNum, int balance, String name, int creditLine, double interest) {
    super(accountNum, balance, name);
    this.creditLine = creditLine;
    this.interest = interest;
    if (this.balance > this.creditLine) {
      throw new IllegalArgumentException("The balance cannot be more than the credit line");
    }
  }

  //EFFECT: Withdraw the given amount
  //Return the new balance
  int withdraw(int amount) {
    if (this.balance + amount > this.creditLine) {
      throw new RuntimeException(amount + " is not available");
    }
    else {
      this.balance = this.balance + amount;
      return this.balance;
    }
  }

  //EFFECT: Deposit the given funds into this account
  //Return the new balance
  int deposit(int funds) {
    if (this.balance - funds < 0) {
      throw new RuntimeException("Cannot deposit more than the balance");
    }
    else {
      this.balance = this.balance - funds;
      return this.balance;
    }
  }
}

//Represents a savings account
class Savings extends Account {

  double interest; // The interest rate

  Savings(int accountNum, int balance, String name, double interest) {
    super(accountNum, balance, name);
    this.interest = interest;
  }

  //EFFECT: Withdraw the given amount
  //Return the new balance
  int withdraw(int amount) {
    if (this.balance - amount < 0) {
      throw new RuntimeException(amount + " is not available");
    }
    else {
      this.balance = this.balance - amount;
      return this.balance;
    }
  }

  //EFFECT: Deposit the given funds into this account
  //Return the new balance
  int deposit(int funds) {
    this.balance = this.balance + funds;
    return this.balance;
  }
}
