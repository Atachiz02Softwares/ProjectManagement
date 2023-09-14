package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
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
        String email = getString(R.string.mail);

        login.setOnClickListener(v -> {
            String identifier = id.getText().toString().trim();
            String pinCode = pin.getText().toString().trim();
            ArrayList<User> users = database.selectAllUsers();

            boolean loginSuccessful = false;
            String role = "";

            for (User user : users) {
                String id = user.getEmail(), pin = user.getPin().trim();
                // Check for empty fields
                if (TextUtils.isEmpty(identifier) || TextUtils.isEmpty(pinCode)) {
                    Toast.makeText(this, "No field should be empty!", Toast.LENGTH_SHORT).show();
                    return; // Exit the method to prevent further processing
                } else if (id.equalsIgnoreCase(identifier) && pin.equals(pinCode)) {
                    loginSuccessful = true;
                    role = user.getRole();
                    break;
                }
            }

            database.updateUserOnlineOfflineStatus(identifier, "online");

            switch (role) {
                case "student":
                    startActivity(new Intent(this, StudentActivity.class).putExtra(email, identifier));
                    finish();
                    return; // Exit the method since the login is successful
                case "supervisor":
                    startActivity(new Intent(this, SupervisorActivity.class).putExtra(email, identifier));
                    finish();
                    return;
                case "coordinator":
                    startActivity(new Intent(this, CoordinatorActivity.class).putExtra(email, identifier));
                    finish();
                    return;
            }

            // This block will execute only if login was not successful
            if (!loginSuccessful)
                Toast.makeText(this, "Incorrect login details!", Toast.LENGTH_SHORT).show();
        });
    }
}