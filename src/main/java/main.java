import CheckingAccounts.CheckingsAccount;
import SavingsAccounts.SavingsAccount;
import UtilitiesAccounts.Utilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

class Main {
   static CheckingsAccount checkingsAccount;
   static SavingsAccount savingsAccount;
    static Utilities util;
    static int accountNumber = 0;

    public static void main(String[] args) {
        init();
        mainloop();

    }


    public static void init() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Please choose an option:");
        System.out.println("1. Log in");
        System.out.println("2. Create Account");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                createAccount();
                break;
            default:
                System.out.println("Invalid option. Please choose 1 or 2.");
                init();
                break;
        }

    }

    public static void login(){
        //prompt for user and password
        //search file
        //keep track of username line
        //ask whether to access checking or saving account
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to login to utilities with a username, or 2 to login with an account number:");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                System.out.println("enter username");
                String username = sc.next();
                System.out.println("enter password");
                String password = sc.next();
                authenticateUser(username,password, accountNumber);
            case 2:
                System.out.println("enter account number:");
                int acctNum = sc.nextInt();
                System.out.println("enter account password:");
                String password2 = sc.next();
                authenticateUser(util.getUserName(),password2,acctNum);
                break;

            default:
                System.out.println("invalid response. Please try again.");
                break;
        }


    }

    private static boolean authenticateUser(String username, String password, int accountNumber) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("path/to/your/file.txt"));
            for (int i = 0; i < lines.size(); i += 4) {
                if (lines.get(i).equals(username) && lines.get(i + 1).equals(password)) {
                    // You can also access account numbers here if needed
                    double checkingAccountBalance = Double.parseDouble(lines.get(i + 3));
                    double savingsAccountBalance = Double.parseDouble(lines.get(i + 4));
                    checkingsAccount = new CheckingsAccount(accountNumber, username,checkingAccountBalance);
                    savingsAccount = new SavingsAccount(accountNumber, username, savingsAccountBalance);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read or process the file.");
            e.printStackTrace();
        }
        return false;
    }
    public static void createAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username");
        String username = sc.nextLine();
        System.out.println("enter password");
        String password = sc.nextLine();
         util = new Utilities(username, password);
        accountNumber = util.getaccountNumber();

    }

    public static void mainloop(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("ATM Menu:");
            System.out.println("Access Checking Account  (1):");
            System.out.println("Access Savings Account   (2):");
            System.out.println("Access Utilities Account (3):");
            System.out.println("New day bruv             (4):");
            int input = sc.nextInt();
            switch (input){
                case 1:
                    checkingMenu();
                    break;
                case 2:
                    savingMenu();
                    break;
                case 3:
                    utilitiesMenu();
            }

        }
    }

    public static void checkingMenu(){

        Scanner sc = new Scanner(System.in);
        System.out.println("******Checking Menu bruv******:");
        System.out.println("Checking Balance lad: " + checkingsAccount.getBalance());
        System.out.println("Deposit bread  (1):");
        System.out.println("Withdraw bread (2):");
        System.out.println("Transfer       (3):");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                System.out.flush();
                System.out.println("How much money do you want to deposit bruv?");
                int amt = sc.nextInt();
                checkingsAccount.deposit(amt);
                break;
            case 2:

                break;
            case 3:

                break;
        }

    }

    public static void savingMenu(){
        System.out.flush();
        System.out.println("******Savings Menu bruv******:");
        System.out.println("Savings Balance bruv: " + savingsAccount.getBalance());


    }
    public static void utilitiesMenu(){
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        System.out.println("******Utilities Menu bruv******:");
        System.out.println("Press 1 to see last 3 bills bruv:");
        System.out.println("Press 2 to see your next bill bruv:");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                utilSubMenu1();
                break;
            case 2:
                utilSubMenu2();
                break;
            default:
                System.out.println("Shite choice mandem");
                break;
        }
    }



    public static void utilSubMenu1(){
        System.out.flush();




    }
    public static void utilSubMenu2(){
        System.out.flush();

    }

}
