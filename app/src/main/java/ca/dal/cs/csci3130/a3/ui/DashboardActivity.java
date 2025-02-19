package ca.dal.cs.csci3130.a3.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import ca.dal.cs.csci3130.a3.R;
import ca.dal.cs.csci3130.a3.access.AppUser;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.loadRoleSpinner();
        this.setupEnterButton();
    }

    protected void loadRoleSpinner() {
        List<String> roles = new ArrayList<>();
        roles.add("Select a role");
        roles.add("Student");
        roles.add("Tutor");
        roles.add("Admin");
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);
    }

    protected String getSelectedRole() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        return roleSpinner.getSelectedItem().toString();
    }

    protected AppUser getAppUser(String role) {
        //buggy method, fix the bug!
        return null;
    }


    protected void setupEnterButton() {
        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(view -> {
            String role = getSelectedRole();
            AppUser user = getAppUser(role);
            manageUserPanel(user);
        });
    }

    public void manageUserPanel(AppUser user) {
        //Incomplete method, add the access control logic
    }

    protected void clearOldPanels() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : manager.getFragments()) {
            transaction.remove(fragment);
        }
        transaction.commit();
    }

    protected FragmentTransaction getFragmentTransaction() {
        FragmentManager manager = getSupportFragmentManager();
        return manager.beginTransaction();
    }

    protected void manageTutorSearchPanelDisplay(boolean hasAccess) {
        FragmentTransaction transaction = getFragmentTransaction();
        SearchTutorFragment searchTutorFragment = new SearchTutorFragment();
        if (hasAccess) {
            showPanel(transaction, R.id.tutorSearchContainer, searchTutorFragment);
        } else {
            removePanel(searchTutorFragment, transaction);
        }
        transaction.commit();
    }

    protected void manageAdPanelDisplay(boolean hasAccess) {
        FragmentTransaction transaction = getFragmentTransaction();
        AdFragment adFragment = new AdFragment();
        if (hasAccess) {
            showPanel(transaction, R.id.adContainer, adFragment);
        } else {
            removePanel(adFragment, transaction);
        }
        transaction.commit();
    }

    protected void manageShoppingPanelDisplay(boolean hasAccess) {
        FragmentTransaction transaction = getFragmentTransaction();
        ShoppingFragment shoppingFragment = new ShoppingFragment();
        if (hasAccess) {
            showPanel(transaction, R.id.shoppingContainer, shoppingFragment);
        } else {
            removePanel(shoppingFragment, transaction);
        }
        transaction.commit();
    }


    protected void manageAdminPanelDisplay(boolean hasAccess) {
        FragmentTransaction transaction = getFragmentTransaction();
        AdminFragment adminFragment = new AdminFragment();
        if (hasAccess) {
            showPanel(transaction, R.id.adminContainer, adminFragment);
        } else {
            removePanel(adminFragment, transaction);
        }
        transaction.commit();
    }

    protected void showPanel(FragmentTransaction transaction, int containerID, Fragment fragment) {
        transaction.replace(containerID, fragment);
    }

    protected void removePanel(Fragment fragment, FragmentTransaction transaction) {
        transaction.remove(fragment);
    }
}