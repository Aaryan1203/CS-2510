
// Represents a Bank with list of accounts
class Bank {

  String name;
  ILoAccount accounts;

  Bank(String name) {
    this.name = name;

    // Each bank starts with no accounts
    this.accounts = new MtLoAccount();
  }

  /*
   * Fields:
   *  this.name ... String
   *  this.accounts ... ILoAccount
   * Methods:
   *  this.add(Account) ... void
   *  this.deposit(int, int) ... void
   *  this.withdraw(int, int) ... void
   *  this.removeAccount(int) ... void
   * Methods of Fields:
   *  this.accounts.findAccount(int) ... Account
   *  this.accounts.remove(Account) ... void
   */


  //EFFECT: Add a new account to this Bank
  void add(Account acct) { 
    this.accounts = new ConsLoAccount(acct, this.accounts);
  }

  //EFFECT: Deposits the given amount to the account with the given account number
  void deposit(int amount, int accNum) {
    Account account = this.accounts.findAccount(accNum);
    account.deposit(amount);
  }

  //EFFECT: Deposits the given amount to the account with the given account number
  void withdraw(int amount, int accNum) {
    Account account = this.accounts.findAccount(accNum);
    account.withdraw(amount);
  }

  //EFFECT: Remove the given account from this Bank
  void removeAccount(int accNum) {  
    Account account = this.accounts.findAccount(accNum);
    this.accounts = this.accounts.remove(account);
  }

}
