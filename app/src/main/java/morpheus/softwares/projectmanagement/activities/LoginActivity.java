package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.User;

public class LoginActivity extends AppCompatActivity {
    EditText id, pin;
    Button login;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.logInID);
        pin = findViewById(R.id.logInPin);
        login = findViewById(R.id.logInLogin);

        database = new Database(this);

        login.setOnClickListener(this::onClick);
    }

    private void onClick(View v) {
        String identifier = id.getText().toString().trim();
        String pinCode = pin.getText().toString().trim();
        ArrayList<User> users = database.selectAllUsers();

        boolean loginSuccessful = false;

        for (User user : users) {
            // Check for empty fields
            if (TextUtils.isEmpty(identifier) || TextUtils.isEmpty(pinCode)) {
                Toast.makeText(this, "No field should be empty!", Toast.LENGTH_SHORT).show();
                return; // Exit the method to prevent further processing
            }

            // Check for valid login
            else if (user.getIdentifier().trim().equalsIgnoreCase(identifier) && user.getPin().trim().equals(pinCode)) {
                loginSuccessful = true;
                String role = user.getRole();

                new Links(this).setProfile(role);

                switch (role) {
                    case "student":
                        startActivity(new Intent(this, StudentActivity.class).putExtra("idNumber", identifier));
                        finish();
                        return; // Exit the method since the login is successful
                    case "supervisor":
                        startActivity(new Intent(this, SupervisorActivity.class).putExtra("idNumber", identifier));
                        finish();
                        return; // Exit the method since the login is successful
                    case "coordinator":
                        startActivity(new Intent(this, CoordinatorActivity.class).putExtra("idNumber", identifier));
                        finish();
                        return; // Exit the method since the login is successful
                }
            }
            break; // Exit the loop since the login is successful
        }

        // This block will execute only if login was not successful
        if (!loginSuccessful) {
            Toast.makeText(this, "Incorrect login details!", Toast.LENGTH_SHORT).show();
        }
    }
}