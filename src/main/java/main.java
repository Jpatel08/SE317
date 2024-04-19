import Bills.Bills;
import CheckingAccounts.CheckingsAccount;
import SavingsAccounts.SavingsAccount;
import UtilitiesAccounts.Utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Main {
   static CheckingsAccount checkingsAccount;
   static SavingsAccount savingsAccount;
    static Utilities util;
    static int accountNumber = 0;
    static String billsFile = "src/bills.txt";
    static ArrayList<Bills> bills = getBills(billsFile);







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
                login();
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
                break;
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

            List<String> lines = Files.readAllLines(Paths.get("src/userInfo"));
            for (int i = 0; i < lines.size(); i += 4) {
                if (lines.get(i).equals(username) && lines.get(i + 1).equals(password)) {
                    double checkingAccountBalance = Double.parseDouble(lines.get(i + 3));
                    double savingsAccountBalance = Double.parseDouble(lines.get(i + 4));
                    checkingsAccount = new CheckingsAccount(username,checkingAccountBalance);
                    savingsAccount = new SavingsAccount(username, savingsAccountBalance);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read or process the file.");
            e.printStackTrace();
        }
        return false;
    }
    public static void createAccount() {
        Scanner sc = new Scanner(System.in);

        // Collect user information
        System.out.println("Enter Username:");
        String username = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();

        // Assuming Utilities is a class you have that can generate account numbers
        util = new Utilities(username, password);
        accountNumber = util.getaccountNumber();

        // Prepare the data string to write to the file
        String userInfo = username + "\n" + password + "\n" + accountNumber + "\n" + 0.0 + "\n" + 0.0 + "\n";

        try {
            // Write the new user info to the file, appending to the end of the file
            Files.write(Paths.get("src/userInfo"), userInfo.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Account successfully created.");
        } catch (Exception e) {
            System.out.println("Failed to write to the file.");
            e.printStackTrace();
        }
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
        System.out.println("GET OUT        (4):");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                checkingdeposit();

                break;
            case 2:
                checkingsWithdraw();
                break;
            case 3:
                transferFromCheckings();
                break;
            case 4:
                mainloop();
                break;
            default:
                System.out.println("Bad Choice bruv");
                break;
        }

    }
    public static void transferFromCheckings() {
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("How much money do you want to transfer to savings brothanem");
        double amt = sc.nextInt();
        checkingsAccount.transfer(savingsAccount,amt);
        updateAccountBalances();

    }

    public static void checkingsWithdraw(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("How much money do you want to withdraw bruv?");
        double amt = sc.nextInt();
        checkingsAccount.withdraw(amt);
        updateAccountBalances();

    }
    public static void checkingdeposit(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("How much money do you want to deposit bruv?");
        int amt = sc.nextInt();
        checkingsAccount.deposit(amt);
        updateAccountBalances();

    }

    public static void savingMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("******Savings Menu bruv******:");
        System.out.println("Savings Balance bruv: " + savingsAccount.getBalance());
        System.out.println("Deposit bread  (1):");
        System.out.println("Transfer       (2):");
        System.out.println("GET OUT        (3):");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                savingDeposit();

                break;
            case 2:
                transferFromSavings();
                break;
            case 3:
                mainloop();
                break;
            default:
                System.out.println("Bad Choice bruv");
                break;
        }

    }
    public static void savingDeposit(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("How much money do you want to deposit into savings ");
        double amt = sc.nextInt();
        savingsAccount.deposit(amt);
        updateAccountBalances();
    }
    public static void transferFromSavings(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("How much money do you want to transfer (max 100 per day) ");
        double amt = sc.nextInt();
        savingsAccount.transfer(checkingsAccount,amt);
        updateAccountBalances();

    }
    public static void utilitiesMenu(){
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        System.out.println("******Utilities Menu bruv******:");
        System.out.println("Press 1 to see last 3 bills bruv:");
        System.out.println("Press 2 to see your next bill bruv:");
        System.out.println("Press 3 to up and leave this menu fam:");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                utilSubMenu1();
                break;
            case 2:
                utilSubMenu2();
                break;
            case 3:
                mainloop();
            default:
                System.out.println("Bad choice bruv");
                break;
        }
    }



    public static void utilSubMenu1(){
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("Your last three bills fam:");
        lastThreeBills(bills);
        System.out.println("press 1 to go back to the utilities menu bruv.");
        int choice  = sc.nextInt();
        if (choice == 1){
            utilitiesMenu();
        }else{
                System.out.println("Bad Choice bruv");
        }

    }




    public static void utilSubMenu2() {
        System.out.flush();

        System.out.println("Your next bill fam:");
        Bills unpaid  = getNextUnpaidBill(bills);
        System.out.println("Balance due:"+unpaid.getAmount());
        System.out.println("Would you like to pay this off? yes(1) or no (2):");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                if (checkingsAccount.getBalance() >=1 ) {
                    checkingsAccount.withdraw(/*util.getNextBill.amount() */ 1);
                    payBill(bills,checkingsAccount);
                    updateAccountBalances();
                    updateFile(bills,billsFile);
                    System.out.println("Big spenda activities, bill has been paid.");
                    utilitiesMenu();

                } else {
                    System.out.println("Your a bum fam, insufficient bread activities.");
                }
            case 2:
                utilitiesMenu();
                break;
            default:
                System.out.println("Bad Choice bruv");
                break;
        }
    }

    public static boolean updateAccountBalances() {
        try {
            // Path to the file
           // String path = "src/userInfo";

            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("src/userInfo"));
            boolean found = false;

            // Iterate over the lines to find the user
            for (int i = 0; i < lines.size(); i += 5) { // Assuming each user's data spans 5 lines as per your reading logic
                if (lines.get(i).equals(util.getUserName())) {
                    // Update the checking and savings balances
                    lines.set(i + 3, String.valueOf(checkingsAccount.getBalance()));
                    lines.set(i + 4, String.valueOf(savingsAccount.checkBalance()));
                    found = true;
                    break;
                }
            }

            if (found) {
                // Write the updated content back to the file
                Files.write(Paths.get("src/userInfo"), lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
                return true;
            } else {
                System.out.println("User not found.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to read or write to the file.");
            e.printStackTrace();
            return false;
        }
    }


    public static ArrayList<Bills> getBills(String filename) {
        ArrayList<Bills> billsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int dueDate = Integer.parseInt(line);
                double amount = Double.parseDouble(br.readLine());
                boolean paid = Boolean.parseBoolean(br.readLine());
                Bills bill = new Bills((int) amount, dueDate, paid);
                billsList.add(bill);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billsList;
    }


    public static Bills getNextUnpaidBill(ArrayList<Bills> billsList) {
        for (Bills bill : billsList) {
            if (!bill.isPaid()) {
                return bill;
            }
        }
        return null; // If no unpaid bill found
    }


    public static void lastThreeBills(ArrayList<Bills> billsList) {
        int countPaidBills = 0;
        System.out.println("last Three Paid Bills:");
        for (int i = 0; i < billsList.size() && countPaidBills < 3; i++) {
            Bills bill = billsList.get(i);
            if (bill.isPaid()) {
                System.out.println("Due Date: " + bill.getDueDate() + ", Amount: $" + bill.getAmount());
                countPaidBills++;
            }
        }
        if (countPaidBills == 0) {
            System.out.println("No paid bills found.");
        }
    }



    public static void payBill(ArrayList<Bills> billsList, CheckingsAccount c) {
       Bills b =  getNextUnpaidBill(billsList);
        if (c.getBalance()>b.getAmount()){
           c.withdraw(b.getAmount());
           b.setPaid(true);
           System.out.println("Bill paid off bruv.");

       }else{
           System.out.println("insufficient funds mandem.");
       }

    }


    private static void updateFile(ArrayList<Bills> billsList, String billsFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(billsFile))) {
            for (Bills bill : billsList) {
                writer.write(Integer.toString(bill.getDueDate()));
                writer.newLine();
                writer.write(Double.toString(bill.getAmount()));
                writer.newLine();
                writer.write(Boolean.toString(bill.isPaid()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
