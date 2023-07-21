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
import morpheus.softwares.projectmanagement.models.Users;

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

        database = new Database(LoginActivity.this);

        login.setOnClickListener(v -> {
            String identifier = String.valueOf(id.getText()).trim(),
                    pinCode = String.valueOf(pin.getText()).trim();
            ArrayList<Users> users = database.selectAllUsers();

            for (Users user : users) {
                if (TextUtils.isEmpty(identifier) || TextUtils.isEmpty(pinCode)) {
                    Toast.makeText(LoginActivity.this, "No field should be empty!", Toast.LENGTH_SHORT).show();
                } else if ((!TextUtils.equals(user.getIdentifier(), identifier)) && (!TextUtils.equals(user.getPin(), pinCode))) {
                    Toast.makeText(LoginActivity.this, "Incorrect login details!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    String role = user.getRole();

                    switch (role) {
                        case "student":
                            startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                            finish();
                            break;
                        case "supervisor":
                            startActivity(new Intent(LoginActivity.this, SupervisorActivity.class));
                            finish();
                            break;
                        case "coordinator":
                            startActivity(new Intent(LoginActivity.this, CoordinatorActivity.class));
                            finish();
                            break;
                    }
                }
            }
        });
    }
}