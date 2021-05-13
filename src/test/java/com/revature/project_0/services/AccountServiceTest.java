package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidInputException;
import com.revature.project_0.util.LinkedList;
import org.junit.*;

/**
 * test suite for accountservices
 *
 * @author Everett Diuguid
 */
public class AccountServiceTest {

    private AccountService sut;

    @Before
    public void setUp() {
        sut = new AccountService();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void test_transactionLinkedList() {
        LinkedList transactionList = sut.getTransactions(1);
        Assert.assertNotNull(transactionList);
    }

    @Test (expected = InvalidInputException.class)
    public void test_checkInputValid() {
        double input = -20;
        sut.checkInput(input);
    }
}
