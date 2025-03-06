package ca.dal.cs.csci3130.a3.firebase;

import java.util.ArrayList;

public class ItemCRUD {

    ArrayList<Item> items;

    public ItemCRUD() {
        this.items = new ArrayList<>();
        this.loadSampleItems();
    }

    protected void loadSampleItems() {
        this.loadClothes();
        this.loadFood();
        this.loadBooks();
    }

    protected void loadClothes() {

        Clothes shirt = new Clothes();
        shirt.setName("T-shirt");
        shirt.setPrice(30);
        shirt.setLocation("Halifax");
        shirt.setAvailable(true);
        shirt.setWoolen(false);

        Clothes mitten = new Clothes();
        mitten.setName("Mitten");
        mitten.setPrice(20);
        mitten.setLocation("Halifax");
        mitten.setAvailable(true);
        mitten.setWoolen(true);

        Clothes sweater = new Clothes();
        sweater.setName("Sweater");
        sweater.setPrice(50);
        sweater.setLocation("Halifax");
        sweater.setAvailable(true);
        sweater.setWoolen(true);

        Clothes jacket = new Clothes();
        jacket.setName("Winter Jacket");
        jacket.setPrice(350);
        jacket.setLocation("Halifax");
        jacket.setAvailable(true);
        jacket.setWoolen(false);

        items.add(shirt);
        items.add(mitten);
        items.add(sweater);
        items.add(jacket);
    }

    protected void loadFood() {

        Food kebab = new Food();
        kebab.setName("Chicken Kebab");
        kebab.setPrice(25);
        kebab.setLocation("Halifax");
        kebab.setAvailable(true);
        kebab.setPerishable(true);

        Food juice = new Food();
        juice.setName("Mango Juice");
        juice.setPrice(5);
        juice.setLocation("Halifax");
        juice.setAvailable(true);
        juice.setPerishable(true);

        Food burger = new Food();
        burger.setName("Beef Burger");
        burger.setPrice(15);
        burger.setLocation("Halifax");
        burger.setAvailable(true);
        burger.setPerishable(true);

        this.items.add(kebab);
        this.items.add(juice);
        this.items.add(burger);

    }

    protected void loadBooks() {
        Book designPattern = new Book();
        designPattern.setName("Design Patterns");
        designPattern.setPrice(90);
        designPattern.setLocation("Halifax");
        designPattern.setAvailable(true);
        designPattern.setAudible(false);

        Book scrum = new Book();
        scrum.setName("Essential Scrum");
        scrum.setPrice(100);
        scrum.setLocation("Halifax");
        scrum.setAvailable(true);
        scrum.setAudible(true);

        Book cleanCode = new Book();
        cleanCode.setName("Clean Code");
        cleanCode.setPrice(75);
        cleanCode.setLocation("Halifax");
        cleanCode.setAvailable(true);
        cleanCode.setAudible(true);

        this.items.add(designPattern);
        this.items.add(scrum);
        this.items.add(cleanCode);
    }

    public ArrayList<Item> collectRetrievedItems(String category) {
        ArrayList<Item> retrieved = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getCategory().equals(category)) {
                retrieved.add(item);
            }
        }
        return retrieved;
    }

    public ArrayList<String> collectItemHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Item");
        headers.add("Price");
        return headers;
    }

    public Item deliverTopItem(ArrayList<Item> items) {
        if (!items.isEmpty()) {
            Item topItem = items.get(0);
            items.remove(topItem);
            return topItem;
        } else return null;
    }

    public ArrayList<Item> getItemsByCategory(String category) {
        ArrayList<Item> retrieved = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getCategory().equals(category)) {
                retrieved.add(item);
            }
        }
        return retrieved;
    }

}
