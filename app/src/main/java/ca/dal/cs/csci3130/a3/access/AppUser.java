package ca.dal.cs.csci3130.a3.access;

import ca.dal.cs.csci3130.a3.config.AppConstants;

public class AppUser {

    public AccessLevel accessLevel;
    String userType;

    public AppUser(String userType) {
        this.userType = userType;
        this.setAccessLevel();
    }

    private void setAccessLevel() {
        switch (this.userType) {
            case AppConstants.STUDENT:
                setAccessLevel(AccessLevel.studentAccessLevel);
                break;

            case AppConstants.TUTOR:
                setAccessLevel(AccessLevel.tutorAccessLevel);
                break;

            case AppConstants.ADMIN:
                setAccessLevel(AccessLevel.adminAccessLevel);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.userType);
        }
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public boolean canSearchForTutor() {
        if (this.accessLevel.equals(AccessLevel.studentAccessLevel)) {
            return true;
        } else return this.accessLevel.equals(AccessLevel.adminAccessLevel);
    }

    public boolean canPlaceOnlineAd() {
        if (this.accessLevel.equals(AccessLevel.tutorAccessLevel)) {
            return true;
        } else return this.accessLevel.equals(AccessLevel.adminAccessLevel);
    }

    public boolean hasAdminAccess() {
        return this.accessLevel.equals(AccessLevel.adminAccessLevel);
    }

    public boolean canGoShopping() {
        if (this.accessLevel.equals(AccessLevel.studentAccessLevel)) {
            return true;
        } else return accessLevel.equals(AccessLevel.tutorAccessLevel);
    }

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }
}
