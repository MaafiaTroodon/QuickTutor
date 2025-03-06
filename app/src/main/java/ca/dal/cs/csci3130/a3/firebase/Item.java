package ca.dal.cs.csci3130.a3.firebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String name;
    private String category;
    private double price;
    private boolean isAudible;  // 🔹 Move isAudible here
    private boolean woolen;
    private boolean perishable;
    private String location;
    private boolean isAvailable;

    public Item() {}

    // 🔹 Modify Parcel Constructor to include isAudible
    protected Item(Parcel in) {
        name = in.readString();
        category = in.readString();
        price = in.readDouble();
        isAudible = in.readByte() != 0;  // 🔹 Read isAudible from Parcel
        woolen = in.readByte() != 0;
        perishable = in.readByte() != 0;
        location = in.readString();
        isAvailable = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeDouble(price);
        dest.writeByte((byte) (isAudible ? 1 : 0));  // 🔹 Save isAudible to Parcel
        dest.writeByte((byte) (woolen ? 1 : 0));
        dest.writeByte((byte) (perishable ? 1 : 0));
        dest.writeString(location);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    // Getters and setters...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isAudible() { return isAudible; }  // 🔹 Ensure Audible is here
    public void setAudible(boolean audible) { this.isAudible = audible; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public boolean isWoolen() { return woolen; }
    public void setWoolen(boolean woolen) { this.woolen = woolen; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isPerishable() { return perishable; }
    public void setPerishable(boolean perishable) { this.perishable = perishable; }
}
