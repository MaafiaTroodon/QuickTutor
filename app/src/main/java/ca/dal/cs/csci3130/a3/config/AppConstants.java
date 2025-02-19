package ca.dal.cs.csci3130.a3.config;

import ca.dal.cs.csci3130.a3.util.PasswordUtility;

public class AppConstants {

    public static final String UNKNOWN_EMAIL = "Unknown e-mail address";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String PASSWORD_HASH = PasswordUtility.makeHash("pass123");
    public static final String NO_ERRORS_FOUND = "";

    public static final String STUDENT = "Student";
    public static final String TUTOR = "Tutor";
    public static final String ADMIN = "Admin";

    public static final String WOOLEN = "Woolen";
    public static final String PERISHABLE = "Perishable";
    public static final String AUDIBLE = "Audible";

    public static final String CLOTHES = "Clothes";
    public static final String FOOD = "Food";
    public static final String BOOK = "Book";

    public static final double LONGITUDE = -63.58;
    public static final double LATITUDE = 44.65;
}

