package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;

public class MainActivity extends AppCompatActivity {
    Button student, supervisor, coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = findViewById(R.id.mainStudent);
        supervisor = findViewById(R.id.mainSupervisor);
        coordinator = findViewById(R.id.mainCoordinator);

        SharedPreferences sharedPreferences = getSharedPreferences("Status", MODE_PRIVATE);
        String status = sharedPreferences.getString("status", "");

        switch (status) {
            case "student":
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
                finish();
                break;
            case "supervisor":
                startActivity(new Intent(MainActivity.this, SupervisorActivity.class));
                finish();
                break;
            case "coordinator":
                startActivity(new Intent(MainActivity.this, CoordinatorActivity.class));
                finish();
                break;
            default:
//                throw new IllegalStateException("Unexpected value: " + status);
        }

        student.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StudentSignUpActivity.class));
        });
        supervisor.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SupervisorSignUpActivity.class));
        });
        coordinator.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CoordinatorSignUpActivity.class));
        });
    }
}