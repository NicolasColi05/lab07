package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;


    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() { 
        assertEquals(0.0 , bankAccount.getBalance());
        assertEquals(mRossi , bankAccount.getAccountHolder());
        assertEquals(0 , bankAccount.getTransactionsCount());

    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(bankAccount.getAccountHolder().getUserID(), 100);
        assertEquals(1, bankAccount.getTransactionsCount());
        assertEquals(100, bankAccount.getBalance());
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(100 - 0.1 - 5 , bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        int count = 0;
        try {
            bankAccount.withdraw(mRossi.getUserID(), -1000);
        } catch (IllegalArgumentException e) {
            count++;
        }
        assertEquals(1, count);
       
       
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        int count = 0;
        try {
            bankAccount.withdraw(mRossi.getUserID(), 1000);
        } catch (IllegalArgumentException e) {
            count++;
        }
        assertEquals(1, count);
    }
}
