package UtilitiesAccounts;
import java.util.List;
import java.util.Random;
import Bills.Bills;

public class Utilities {

    int accountNumber;


    private String userName;
    private String password;
    public static int generateRandomID() {
        Random random = new Random();
        int id = 0;
        for (int i = 0; i < 6; i++) {
            id = id * 10 + random.nextInt(10);
        }
        return id;
    }
    public Utilities(String userName, String password){
        this.userName = userName;
        this.password=password;
        this.accountNumber = generateRandomID();
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getaccountNumber(){

        return this.accountNumber;
    }




}
