import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bills {
    @Id
    int id;

    private int amount;
    private int dueDate;
    private boolean paid;

    public Bills(int amount, int dueDate){
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = false;
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
