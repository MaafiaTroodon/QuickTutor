package ca.dal.cs.csci3130.a3.core;

public class DalCard {
    private final String programCode;
    private final String bannerID;
    private int points;

    public DalCard(String programCode, String bannerID, int points) {
        this.programCode = programCode;
        this.bannerID = bannerID;
        this.points = points;
    }

    public String getCardNumber() {
        return this.programCode + "-" + this.bannerID;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
