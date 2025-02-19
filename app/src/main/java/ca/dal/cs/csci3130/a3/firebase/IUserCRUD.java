package ca.dal.cs.csci3130.a3.firebase;

public interface IUserCRUD {
    public boolean checkIfEmailIsRegistered(String emailAddress);

    public boolean checkIfPasswordHashMatches(String passwordHash);

    public String getUserRole(String emailAddress);
}
