package SavingsAccounts;

import Accounts.Account;

public class SavingsAccount extends Account{
   public double dailyDeposit = 0;
    public double dailyTransfer = 0;

    public SavingsAccount(String userName, double balance) {

        super(userName, balance);
    }



    @Override
    public void deposit(double amount) {
        if(amount <= 0){
            System.out.println("Cannot deposit negative amount");
        }
        else if (dailyDeposit + amount > 5000){
            System.out.println("Cannot deposit, over daily limit");
        }
        else{
            this.balance += amount;
            dailyDeposit += amount;
            System.out.println("Succesfuly deposity " + amount + "into Savings Accounts.Account\n " +
                    "New Balance: "+ this.balance);
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if(amount<=0){
          System.out.println("Cannot transfer negative amount");
        }
        else if(amount + dailyTransfer > 100){
            System.out.println("Cannot transfer, over daily limit");
        }
        else if(this.balance - amount >= 0){
            this.balance -= amount;
            toAccount.deposit(amount);
            dailyTransfer += amount;
            System.out.println("Succesfully transfered: " + amount);
        }
        else{
            this.balance -= amount-this.balance;
            toAccount.deposit(amount-this.balance); //transfers max amount I THINK
            dailyTransfer += amount - this.balance;
        }
    }
    public void newDayBruv(){
        dailyDeposit = 0;
        dailyTransfer = 0;
    }

}
