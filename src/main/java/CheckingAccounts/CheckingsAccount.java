package CheckingAccounts;

import Accounts.Account;

public class CheckingsAccount extends Account {
    public double dailyDeposit = 0;
    public double dailyWithdraw = 0;
   public CheckingsAccount(String userName, double balance) {
        super(userName, balance);
    }

    @Override
    public void deposit(double amount) {
       if(amount <= 0){
           System.out.println("Cannot deposit negative balance");
       }
       else if (dailyDeposit + amount > 5000){
            System.out.println("Cannot deposit, over daily limit");
        }
        else{
            this.balance += amount;
            dailyDeposit += amount;
            System.out.println("Succesfuly deposity " + amount + "into Checkings Accounts.Account\n " +
                    "New Balance: "+ this.balance);
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
       if(amount <= 0){
           System.out.println("Cannot deposit negative amount");
       }
       else {
           this.balance -= amount;
           toAccount.deposit(amount);
           System.out.println("Succesfully transfered: " + amount);
       }
    }

    public void withdraw(double amount){
       if(dailyWithdraw + amount > 500){
           System.out.println("Cannot withdraw, daily limit reached");
       }

       else if(this.balance - amount > 0){
           this.balance -= amount;
           dailyWithdraw += amount;

       }
       else{
           System.out.println("Withdraw amount more than balance");
       }
    }

    public void newDayBruv(){
        dailyDeposit = 0;
        dailyWithdraw = 0;
    }
}
