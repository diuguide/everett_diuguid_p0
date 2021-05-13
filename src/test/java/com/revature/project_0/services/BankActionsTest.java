package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidInputException;
import com.revature.project_0.models.AppUser;
import org.junit.*;
import static org.mockito.Mockito.*;

public class BankActionsTest {

    private BankActions sut;
    private AppUser mockUser = mock(AppUser.class);

    @Before
    public void setUp() { sut = new BankActions(mockUser); }

    @After
    public void tearDown() { sut = null; }

    @Test
    public void test_currencyFormatting() {
        String expected = "$12.34";
        Assert.assertEquals(expected, sut.formatBalance(12.34));
    }

}
