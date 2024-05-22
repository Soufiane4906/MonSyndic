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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monsyndic.adapters.ReclamationAdapter;
import com.example.monsyndic.fragements.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reclamation extends AppCompatActivity {
    private Button retourButton;
    private Button submitButton;
    private EditText editReclamation;
    private RecyclerView reclamationRecyclerView;
    private ReclamationAdapter reclamationAdapter;
    private List<String> reclamationList;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        // Initialize views
        retourButton = findViewById(R.id.retourR);
        submitButton = findViewById(R.id.button_submit);
        editReclamation = findViewById(R.id.edit_reclamation);
        reclamationRecyclerView = findViewById(R.id.recyclerview_reclamations);

        // Initialize Firebase Auth and Firestore
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        // Initialize reclamation list and adapter
        reclamationList = new ArrayList<>();
        reclamationAdapter = new ReclamationAdapter(reclamationList);
        reclamationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reclamationRecyclerView.setAdapter(reclamationAdapter);

        if (currentUser == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
            return;
        }

        // Set onClick listener for the return button
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Reclamation.this, HomeFragment.class));
            }
        });

        // Set onClick listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reclamationText = editReclamation.getText().toString().trim();
                if (TextUtils.isEmpty(reclamationText)) {
                    Toast.makeText(Reclamation.this, "Please enter a reclamation.", Toast.LENGTH_SHORT).show();
                } else {
                    addReclamationToFirestore(reclamationText);
                }
            }
        });

        // Load and display user's reclamations
        loadUserReclamations();
    }

    private void addReclamationToFirestore(String reclamationText) {
        // Get the current user's UID
        String userId = currentUser.getUid();

        // Create a new reclamation map
        Map<String, Object> reclamation = new HashMap<>();
        reclamation.put("reclamation", reclamationText);
        reclamation.put("userId", userId);

        // Add the reclamation to Firestore
        CollectionReference reclamationsRef = firestore.collection("reclamations");
        reclamationsRef.add(reclamation).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Reclamation.this, "Reclamation submitted", Toast.LENGTH_SHORT).show();
                editReclamation.setText(""); // Clear the input field
                loadUserReclamations(); // Reload the user's reclamations
            } else {
                Toast.makeText(Reclamation.this, "Failed to submit reclamation: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserReclamations() {
        // Get the current user's UID
        String userId = currentUser.getUid();

        // Query Firestore to get the reclamations of the current user
        firestore.collection("reclamations")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        reclamationList.clear(); // Clear the existing list
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String reclamation = document.getString("reclamation");
                            reclamationList.add(reclamation);
                        }
                        reclamationAdapter.notifyDataSetChanged(); // Notify adapter about data changes
                    } else {
                        Toast.makeText(Reclamation.this, "Failed to load reclamations: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
