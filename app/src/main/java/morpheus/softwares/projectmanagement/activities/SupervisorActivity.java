package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Links;

public class SupervisorActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);

        button = findViewById(R.id.logout);

        button.setOnClickListener(v -> {
            new Links(SupervisorActivity.this).removeStatus();
            startActivity(new Intent(SupervisorActivity.this, LoginActivity.class));
            finish();
        });
    }
}