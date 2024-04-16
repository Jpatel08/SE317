public class SavingsAccount extends Account{
    double dailyDeposit = 0;
    double dailyTransfer = 0;

    public SavingsAccount(int accountId, String userName, double balance) {

        super(accountId, userName, balance);
    }



    @Override
    public void deposit(double amount) {
        if (dailyDeposit + amount > 5000){
            System.out.println("Cannot deposit, over daily limit");
        }
        else{
            this.balance += amount;
            dailyDeposit += amount;
            System.out.println("Succesfuly deposity " + amount + "into Savings Account\n " +
                    "New Balance: "+ this.balance);
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if(amount + dailyTransfer > 100){
            System.out.println("Cannot transfer, over daily limit");
        }
        else if(this.balance - amount >= 0){
            toAccount.deposit(amount);
            dailyTransfer += amount;
            System.out.println("Succesfully transfered: " + amount);
        }
        else{
            toAccount.deposit(amount-this.balance); //transfers max amount I THINK
        }
    }

}
