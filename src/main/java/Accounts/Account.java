package Accounts;

public abstract class Account
{

    protected int accountNumber;
    protected String userName;
    protected double balance;

    public Account(int accountNumber, String userName, double balance) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.balance = balance;
    }

    // Abstract methods that must be implemented by subclasses
    public abstract void deposit(double amount);
    public abstract void transfer(Account toAccount, double amount);

    // Common utility methods can be added here, used by all account types
    public double checkBalance() {
        return balance;
    }

    // Getters and setters
    public int getAccountId() {
        return accountNumber;
    }

    public void setAccountId(int accountId) {
        this.accountNumber = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Accounts.Account ID: " + accountNumber + ", User Name: " + userName + ", Balance: $" + balance;
    }
}


