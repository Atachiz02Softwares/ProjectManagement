package morpheus.softwares.projectmanagement.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.User;

public class MainActivity extends AppCompatActivity {
    Button signup;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.mainSignup);
        database = new Database(this);
        String email = getString(R.string.mail);

        // Check for read permission and request it if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }

        ArrayList<User> users = database.selectAllUsers();
        for (User user : users) {
            String role = user.getRole(), onlineOffline = user.getOnlineOffline(), identifier = user.getEmail();

            if (onlineOffline.equals("online")) {
                switch (role) {
                    case "student":
                        startActivity(new Intent(this, StudentActivity.class).putExtra(email, identifier));
                        finish();
                        return;
                    case "supervisor":
                        startActivity(new Intent(this, SupervisorActivity.class).putExtra(email, identifier));
                        finish();
                        return;
                    case "coordinator":
                        startActivity(new Intent(this, CoordinatorActivity.class).putExtra(email, identifier));
                        finish();
                        return;
                }
                break;
            }
        }

        signup.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        });
    }
}