package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

        ArrayList<User> users = database.selectAllUsers();
        for (User user : users) {
            String role = user.getRole(), onlineOffline = user.getOnlineOffline();

            if (onlineOffline.equals("online")) {
                switch (role) {
                    case "student":
                        startActivity(new Intent(this, StudentActivity.class));
                        finish();
                        return;
                    case "supervisor":
                        startActivity(new Intent(this, SupervisorActivity.class));
                        finish();
                        return;
                    case "coordinator":
                        startActivity(new Intent(this, CoordinatorActivity.class));
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