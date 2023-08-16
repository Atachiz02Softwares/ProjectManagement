package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;

public class MainActivity extends AppCompatActivity {
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.mainSignup);

        SharedPreferences sharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        String profile = sharedPreferences.getString("profile", "");

        switch (profile) {
            case "student":
                startActivity(new Intent(this, StudentActivity.class));
                finish();
                break;
            case "supervisor":
                startActivity(new Intent(this, SupervisorActivity.class));
                finish();
                break;
            case "coordinator":
                startActivity(new Intent(this, CoordinatorActivity.class));
                finish();
                break;
        }

        signup.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        });
    }
}