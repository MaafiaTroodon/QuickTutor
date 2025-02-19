package ca.dal.cs.csci3130.a3.access;

import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.IUserCRUD;
import ca.dal.cs.csci3130.a3.util.PasswordUtility;

public class LogInManager {
    String emailAddress;
    String passwordHash;

    public LogInManager(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.passwordHash = PasswordUtility.makeHash(password);
    }

    public String validateCredentials(IUserCRUD userCRUD) {
        if (userCRUD.checkIfEmailIsRegistered(this.emailAddress)) {
            if (userCRUD.checkIfPasswordHashMatches(this.passwordHash)) {
                return AppConstants.NO_ERRORS_FOUND;
            } else return AppConstants.INCORRECT_PASSWORD;
        } else return AppConstants.UNKNOWN_EMAIL;
    }

    public boolean checkIfUserHasARole(IUserCRUD userCRUD) {
        String role = userCRUD.getUserRole(this.emailAddress);
        return role != null;
    }
}
