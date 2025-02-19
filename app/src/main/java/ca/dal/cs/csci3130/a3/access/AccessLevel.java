package ca.dal.cs.csci3130.a3.access;

import ca.dal.cs.csci3130.a3.config.AppConstants;

public class AccessLevel {

    public static final AccessLevel studentAccessLevel = new AccessLevel(AppConstants.STUDENT);
    public static final AccessLevel tutorAccessLevel = new AccessLevel(AppConstants.TUTOR);
    public static final AccessLevel adminAccessLevel = new AccessLevel(AppConstants.ADMIN);
    String userAccessLevel;

    public AccessLevel(String userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }
}
