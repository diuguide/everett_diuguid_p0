package com.revature.project_0.services;

import com.revature.project_0.util.LinkedList;
import org.junit.*;

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
}
