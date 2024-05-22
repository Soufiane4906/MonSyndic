package com.example.monsyndic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monsyndic.fragements.ContactsFragment;
import com.example.monsyndic.fragements.HomeFragment;
import com.example.monsyndic.fragements.ProfileFragment;
import com.example.monsyndic.fragements.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Acceuil extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        auth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.menu_home) {
                    loadFragment(new HomeFragment(), false);
                    return true;
                } else if (itemId == R.id.menu_signout) {
                    showSignOutConfirmation();
                    return true;
                } else if (itemId == R.id.menu_settings) {
                    loadFragment(new SettingsFragment(), false);
                    return true;
                } else if (itemId == R.id.menu_contact) {
                    loadFragment(new ContactsFragment(), false);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Load the default fragment
        loadFragment(new HomeFragment(), true);
    }



    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }

    private void showSignOutConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes", (dialog, id) -> signOut())
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void signOut() {
        auth.signOut();
        Intent intent = new Intent(Acceuil.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
