package Bills;

import UtilitiesAccounts.Utilities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Bills {


    private int amount;
    private int dueDate;
    private boolean paid;

    public Bills(int amount, int dueDate, boolean paid){
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = paid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
