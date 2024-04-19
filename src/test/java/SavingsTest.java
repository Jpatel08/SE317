import CheckingAccounts.CheckingsAccount;
import SavingsAccounts.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SavingsTest {
    SavingsAccount s;
    CheckingsAccount c;
    @Before
    public void create(){
         s = new SavingsAccount("bruv", 3000);
         c = new CheckingsAccount("brodie", 1000);
    }
    @Test
    public void testSuccessfulDeposit() {
        s.deposit(1000);
        assertEquals(4000.0, s.getBalance(),1);
    }

    @Test
    public void testDepositOverLimit() {
        s.deposit(6000);
        assertEquals(3000, s.getBalance(),1);
    }

    @Test
    public void testTransfer(){
        s.transfer(c,50);
        assertEquals(2950, s.getBalance(),1);
        assertEquals(1050, c.getBalance(),1);
    }
    @Test
    public void testTransferMax(){
        s.transfer(c,150);
        assertEquals(3000, s.getBalance(),1);
        assertEquals(1000, c.getBalance(),1);
    }
    @Test
    public void testResetLimits() {
        s.deposit(3000);
        s.transfer(c, 100);
        s.newDayBruv();
        assertEquals(0, s.dailyDeposit,1);
        assertEquals(0, s.dailyTransfer,1);
    }

}
