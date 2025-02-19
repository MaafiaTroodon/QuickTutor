package ca.dal.cs.csci3130.a3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import ca.dal.cs.csci3130.a3.core.Customer;

public class CustomerTest {

    Customer firstCustomer;
    Customer secondCustomer;

    @Before
    public void setup() {
        firstCustomer = new Customer("Max", "Payne");
        secondCustomer = new Customer("Jane", "Doe");
    }

    @Test
    public void testCustomerName() {
        assertEquals("Max Payne", firstCustomer.getFullName());
        assertEquals("Jane Doe", secondCustomer.getFullName());
    }

    @Test
    public void testCustomerDalCardNumber() {
        firstCustomer.setDalCard("BCS", "B00881213", 100);
        assertEquals("BCS-B00881213", firstCustomer.getDalCardNumber());
        secondCustomer.setDalCard("BACS", "B00562314", 200);
        assertEquals("BACS-B00562314", secondCustomer.getDalCardNumber());
    }

    @Test
    public void testCustomerDalCardPoints() {
        firstCustomer.setDalCard("BCS", "B00881213", 500);
        assertEquals(500, firstCustomer.getDalCardPoints());
        secondCustomer.setDalCard("MACS", "B00567899", 500);
        assertNotEquals(200, secondCustomer.getDalCardPoints());
    }
}
