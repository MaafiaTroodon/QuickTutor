package ca.dal.cs.csci3130.a3.core;

public class Customer {
    private final String firstName;
    private final String lastName;
    private DalCard dalCard;  // Delegation of DalCard responsibility

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setDalCard(String programCode, String bannerID, int points) {
        this.dalCard = new DalCard(programCode, bannerID, points);
    }

    public String getDalCardNumber() {
        return (dalCard != null) ? dalCard.getCardNumber() : "No DalCard assigned";
    }

    public int getDalCardPoints() {
        return (dalCard != null) ? dalCard.getPoints() : 0;
    }
}
