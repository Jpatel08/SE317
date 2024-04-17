package CheckingAccounts;

import Accounts.Account;

public class CheckingsAccount extends Account {
    double dailyDeposit = 0;
    double dailyWithdraw = 0;
   public CheckingsAccount(int accountNumber, String userName, double balance) {
        super(accountNumber, userName, balance);
    }

    @Override
    public void deposit(double amount) {
        if (dailyDeposit + amount > 5000){
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
        toAccount.deposit(amount);
        System.out.println("Succesfully transfered: " + amount);
    }

    public void withdraw(double amount){
       if(dailyWithdraw + amount > 500){
           System.out.println("Cannot withdraw, daily limit reached");
       }
       else if(this.balance - amount >= 0){
           this.balance -= amount;
       }
       else{
           this.balance = 0;
       }
    }
    public void newDayBruv(){
        dailyDeposit = 0;
        dailyWithdraw = 0;
    }
}
