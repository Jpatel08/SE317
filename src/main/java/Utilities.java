import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;

@Entity
public class Utilities {
    @Id
    int accountNumber;

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






}
