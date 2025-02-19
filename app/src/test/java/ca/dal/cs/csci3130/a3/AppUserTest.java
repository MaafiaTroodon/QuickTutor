package ca.dal.cs.csci3130.a3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.dal.cs.csci3130.a3.access.AccessLevel;
import ca.dal.cs.csci3130.a3.access.AppUser;
import ca.dal.cs.csci3130.a3.config.AppConstants;


public class AppUserTest {

    @Test
    public void testStudentAccess() {
        /*AppUser student=new Student()*/
        AppUser appUser = new AppUser(AppConstants.STUDENT);
        assertEquals(AccessLevel.studentAccessLevel, appUser.getAccessLevel());
        assertTrue(appUser.canSearchForTutor());
        assertTrue(appUser.canGoShopping());
        assertFalse(appUser.hasAdminAccess());
        assertFalse(appUser.canPlaceOnlineAd());
    }

    @Test
    public void testTutorAccess() {
        /*AppUser tutor=new Tutor()*/
        AppUser appUser = new AppUser(AppConstants.TUTOR);
        assertEquals(AccessLevel.tutorAccessLevel, appUser.getAccessLevel());
        assertTrue(appUser.canPlaceOnlineAd());
        assertTrue(appUser.canGoShopping());
        assertFalse(appUser.hasAdminAccess());
        assertFalse(appUser.canSearchForTutor());
    }

    @Test
    public void testAdminAccess() {
        /*AppUser admin=new Admin()*/
        AppUser appUser = new AppUser(AppConstants.ADMIN);
        assertEquals(AccessLevel.adminAccessLevel, appUser.getAccessLevel());
        assertTrue(appUser.hasAdminAccess());
        assertTrue(appUser.canPlaceOnlineAd());
        assertTrue(appUser.canSearchForTutor());
        assertFalse(appUser.canGoShopping());
    }

    @Test
    public void testAllUserAccess() {
        AppUser appUser1 = new AppUser(AppConstants.STUDENT);
        AppUser appUser2 = new AppUser(AppConstants.TUTOR);
        AppUser appUser3 = new AppUser(AppConstants.ADMIN);
        assertFalse(appUser1.canPlaceOnlineAd());
        assertFalse(appUser2.canSearchForTutor());
        assertTrue(appUser3.hasAdminAccess());
    }
}
