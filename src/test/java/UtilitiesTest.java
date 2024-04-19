import Bills.Bills;
import CheckingAccounts.CheckingsAccount;
import SavingsAccounts.SavingsAccount;
import UtilitiesAccounts.Utilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UtilitiesTest {
CheckingsAccount c;

Bills b1;
Bills b2;
Bills b3;
ArrayList<Bills> billsList;
@Mock
Main m;

    @Before
    public void setup() {
    c = new CheckingsAccount("test",500);
    b1 = new Bills(122,1,false);
    b2 =new Bills(21,2,false);
    b3  =new Bills(32,3,false);
    billsList = new ArrayList<Bills>();
    billsList.add(b1);
    billsList.add(b2);
    billsList.add(b3);

    }


    @Test
    public void payOneBill(){
       Bills testBill =  b1;
        Main.payBill(billsList,c);
        assertEquals(true, billsList.get(0).isPaid());

    }
    @Test
    public void balanceAfterBill(){
        Bills testBill =  b1;
        Main.payBill(billsList,c);
        System.out.println(c.getBalance());
        assertEquals(378.0,c.getBalance(),0);

    }
    @Test
    public void testInsufficientBalance(){
        c.withdraw(400);
        Main.payBill(billsList,c);
        assertEquals("insufficient funds mandem.","insufficient funds mandem.");

    }
    @Test
    public void testLastBill(){

        Main.payBill(billsList,c);
        Main.lastThreeBills(billsList);
       assertEquals(("Due Date: " + 1 + ", Amount: $" + 122), ("Due Date: " + b1.getDueDate() + ", Amount: $" + b1.getAmount()));
    }





}
