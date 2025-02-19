package ca.dal.cs.csci3130.a3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.dal.cs.csci3130.a3.access.LogInManager;
import ca.dal.cs.csci3130.a3.config.AppConstants;
import ca.dal.cs.csci3130.a3.firebase.IUserCRUD;

public class LogInManagerTest {

    IUserCRUD studentCRUD;
    IUserCRUD tutorCRUD;
    IUserCRUD adminCRUD;

    @Before
    public void setup() {
        //Implement setup method, and mock the CRUD objects using the Mockito framework
    }

    @Test
    public void testStudentLogIn() {
        LogInManager manager = new LogInManager("student@dal.ca", "pass123");
        Assert.assertEquals(AppConstants.NO_ERRORS_FOUND, manager.validateCredentials(studentCRUD));
        Mockito.verify(studentCRUD, Mockito.times(1)).checkIfEmailIsRegistered("student@dal.ca");
    }

    @Test
    public void testTutorLogIn() {
        LogInManager manager = new LogInManager("tutor@dal.ca", "pass123");
        Assert.assertEquals(AppConstants.NO_ERRORS_FOUND, manager.validateCredentials(tutorCRUD));
        Mockito.verify(tutorCRUD, Mockito.times(1)).checkIfEmailIsRegistered("tutor@dal.ca");
    }

    @Test
    public void testAdminLogIn() {
        LogInManager manager = new LogInManager("admin@dal.ca", "pass123");
        Assert.assertEquals(AppConstants.NO_ERRORS_FOUND, manager.validateCredentials(adminCRUD));
        Mockito.verify(adminCRUD, Mockito.times(1)).checkIfEmailIsRegistered("admin@dal.ca");
    }

    @Test
    public void testUserRole() {
        LogInManager manager = new LogInManager("tutor@dal.ca", "pass123");
        Assert.assertTrue(manager.checkIfUserHasARole(tutorCRUD));
        Mockito.verify(tutorCRUD, Mockito.times(1)).getUserRole("tutor@dal.ca");
    }
}
