package com.example.monsyndic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monsyndic.entities.UserInfo;
import com.example.monsyndic.fragements.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MonProfil extends AppCompatActivity {

    private EditText editUserName, editUserEmail, editUserAddress, editUserContactNumber, editImmeubleId;
    private Button saveButton;

    // Firebase Database and Auth instance and reference
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    // UserInfo object
    private UserInfo userInfo;
    private Button Retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_profil);
        Retour=findViewById(R.id.retourP);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MonProfil.this, HomeFragment.class));
            }
        });

        // Initialize views
        editUserName = findViewById(R.id.edit_user_name);
        editUserEmail = findViewById(R.id.edit_user_email);
        editUserAddress = findViewById(R.id.edit_user_address);
        editUserContactNumber = findViewById(R.id.edit_user_contact_number);
        editImmeubleId = findViewById(R.id.edit_immeuble_id);
        saveButton = findViewById(R.id.save_button);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null) {
            // Handle the case when the user is not authenticated
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
            return;
        }

        // Initialize Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserInfo");

        // Initialize UserInfo object
        userInfo = new UserInfo();

        // Set onClick listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input values
                String userName = editUserName.getText().toString().trim();
                String userEmail = editUserEmail.getText().toString().trim();
                String userAddress = editUserAddress.getText().toString().trim();
                String userContactNumber = editUserContactNumber.getText().toString().trim();
                String immeubleId = editImmeubleId.getText().toString().trim();

                // Validate the input fields
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userAddress) || TextUtils.isEmpty(userContactNumber) || TextUtils.isEmpty(immeubleId)) {
                    Toast.makeText(MonProfil.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    // Add data to Firebase
                    addDatatoFirebase(userName, userEmail, userAddress, userContactNumber, immeubleId);
                }
            }
        });

        // Retrieve user information from Firebase
        retrieveUserInfoFromFirebase();
    }

    private void addDatatoFirebase(String userName, String userEmail, String userAddress, String userContactNumber, String immeubleId) {
        // Set data in UserInfo object
        userInfo.setUserName(userName);
        userInfo.setEmail(userEmail);
        userInfo.setAddress(userAddress);
        userInfo.setContactNumber(userContactNumber);
        userInfo.setImmeuble(immeubleId);

        // Add value event listener to send data to Firebase
        databaseReference.child(currentUser.getUid()).setValue(userInfo).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MonProfil.this, "Data added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MonProfil.this, "Failed to add data: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrieveUserInfoFromFirebase() {
        // Retrieve data from Firebase
        databaseReference.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    UserInfo retrievedUserInfo = snapshot.getValue(UserInfo.class);
                    if (retrievedUserInfo != null) {
                        editUserName.setText(retrievedUserInfo.getUserName());
                        editUserEmail.setText(retrievedUserInfo.getEmail());
                        editUserAddress.setText(retrievedUserInfo.getAddress());
                        editImmeubleId.setText(retrievedUserInfo.getImmeuble());
                    }
                } else {
                    Toast.makeText(MonProfil.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MonProfil.this, "Failed to retrieve data: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
