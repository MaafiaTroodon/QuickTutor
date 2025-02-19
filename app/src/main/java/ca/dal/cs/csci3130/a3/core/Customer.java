package ca.dal.cs.csci3130.a3.core;

public class Customer {
    private final String firstName;
    private final String lastName;
    private String programCode;
    private String bannerID;
    private int points;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setDalCard(String programCode, String bannerID, int points) {
        this.programCode = programCode;
        this.bannerID = bannerID;
        this.points = points;
    }

    public String getDalCardNumber() {
        return this.programCode + "-" + this.bannerID;
    }

    public int getDalCardPoints() {
        return this.points;
    }
}
