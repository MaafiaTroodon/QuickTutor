package ca.dal.cs.csci3130.a3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.Book;
import ca.dal.cs.csci3130.a3.firebase.Clothes;
import ca.dal.cs.csci3130.a3.firebase.Food;
import ca.dal.cs.csci3130.a3.firebase.Item;
import ca.dal.cs.csci3130.a3.firebase.ItemCRUD;

public class ItemTest {

    ItemCRUD crud;
    ArrayList<Item> clothes;
    ArrayList<Item> foods;
    ArrayList<Item> books;

    @Before
    public void setup() {
        crud = new ItemCRUD();
        clothes = crud.collectRetrievedItems(AppConstants.CLOTHES);
        foods = crud.collectRetrievedItems(AppConstants.FOOD);
        books = crud.collectRetrievedItems(AppConstants.BOOK);
    }

    @Test
    public void testItemLocation() {
        Item firstItem = crud.deliverTopItem(clothes);
        Assert.assertEquals("Halifax", firstItem.getLocation());
        Item secondItem = crud.deliverTopItem(foods);
        Assert.assertEquals("Halifax", secondItem.getLocation());
        Item thirdItem = crud.deliverTopItem(books);
        Assert.assertEquals("Halifax", thirdItem.getLocation());
    }

    @Test
    public void testItemName() {
        Item firstItem = crud.deliverTopItem(clothes);
        Assert.assertEquals("T-shirt", firstItem.getName());
        Item secondItem = crud.deliverTopItem(foods);
        Assert.assertEquals("Chicken Kebab", secondItem.getName());
        Item thirdItem = crud.deliverTopItem(books);
        Assert.assertEquals("Design Patterns", thirdItem.getName());
    }

    @Test
    public void testItemIsWoolen() {
        Clothes firstItem = (Clothes) crud.deliverTopItem(clothes);
        Assert.assertFalse(firstItem.isWoolen());
        Clothes secondItem = (Clothes) crud.deliverTopItem(clothes);
        Assert.assertTrue(secondItem.isWoolen());
    }

    @Test
    public void testItemIsPerishable() {
        Food firstItem = (Food) crud.deliverTopItem(foods);
        Assert.assertTrue(firstItem.isPerishable());
        Food secondItem = (Food) crud.deliverTopItem(foods);
        Assert.assertTrue(secondItem.isPerishable());
    }

    @Test
    public void testItemIsAudible() {
        Book firstItem = (Book) crud.deliverTopItem(books);
        Assert.assertFalse(firstItem.isAudible());
        crud.deliverTopItem(books);
        Book thirdItem = (Book) crud.deliverTopItem(books);
        Assert.assertTrue(thirdItem.isAudible());
    }
}
