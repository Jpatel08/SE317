import CheckingAccounts.CheckingsAccount;
import SavingsAccounts.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CheckingsTest {
        SavingsAccount s;
        CheckingsAccount c;

        @Before
        public void create() {
            s = new SavingsAccount("bruv", 3000);
            c = new CheckingsAccount("brodie", 1000);
        }


        @Test
        public void testSuccessfulDeposit() {
            c.deposit(3000);
            assertEquals(4000, c.getBalance(),1);
        }

        @Test
        public void testDepositOverLimit() {
            c.deposit(6000);
            assertEquals(1000, c.getBalance(), 1);
        }

        @Test
        public void testSuccessfulWithdrawal() {
            c.withdraw(300);
            assertEquals(700, c.getBalance(), 1);
        }

        @Test
        public void testWithdrawalOverLimit() {
            c.withdraw(600);  // First withdraw within limit

            assertEquals(1000, c.getBalance(), 1);
        }

        @Test
        public void testWithdrawalExceedsBalance() {
            c.withdraw(400);
            c.withdraw(400);
            assertEquals(600, c.getBalance(), 1);
        }

    @Test
    public void testTransfer() {
        c.transfer(s, 200);
        assertEquals(800, c.getBalance(),1);
        assertEquals(3200,s.getBalance(),1);

    }
    @Test
    public void testResetLimits() {
        c.deposit(1000);
        c.withdraw(200);
        c.newDayBruv();
        assertEquals(0, c.dailyDeposit,1);
        assertEquals(0, c.dailyWithdraw, 1);
    }


    }


