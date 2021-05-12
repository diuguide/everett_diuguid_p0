package com.revature.project_0.util;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.Transaction;
import org.junit.*;

import java.sql.SQLOutput;

public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_checkSize() {
        // Arrange
        int expectedSize = 3;

        // Act
        sut.add("one");
        sut.add("two");
        sut.add("three");

        //Assert
        Assert.assertEquals(expectedSize, sut.size());
    }

    @Test
    public void test_containsValue() {

        //Act
        sut.add("test");

        // Assert
        Assert.assertTrue("true", sut.contains("test"));
    }

    @Test
    public void test_getByIndex() {

        // Arrange
        String expectedData = "Index 0";

        // Act
        sut.add("Index 0");

        // Assert
        Assert.assertEquals(expectedData, sut.get(0));

    }

    @Test
    public void test_removeByIndex() {
        // Arrange
        int removedIndex = 0;

        //Act
        sut.add("one");
        sut.add("two");
        sut.add("three");

        // Assert
        Assert.assertEquals(removedIndex, sut.removeByIndex(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeByIndexNullElement() {
        //Act
        sut.add("one");
        sut.removeByIndex(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNullIndex() {
        sut.add("one");
        sut.get(2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_addNullValue() {
        sut.add(null);
    }

    @Test
    public void test_transactionLinkedList() {
        AccountService accountService = new AccountService();
        LinkedList transactionList = accountService.getTransactions(1);
        System.out.println(transactionList.size());
        for(int i = 0; i < transactionList.size(); i++) {
            Transaction item = (Transaction) transactionList.get(i);
            System.out.println((i+1) + " " + item.getTransaction_id() + " " + item.getAmount() + " " + item.getType());
        }
        Assert.assertNotNull(transactionList);
    }

}
