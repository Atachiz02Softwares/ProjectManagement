package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Coordinator;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;

public class CreateCoordinatorProfileActivity extends AppCompatActivity {
    EditText name, mail, phone;
    Button createProfile;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coordinator_profile);

        name = findViewById(R.id.createCoordinatorProfileName);
        mail = findViewById(R.id.createCoordinatorProfileEmail);
        phone = findViewById(R.id.createCoordinatorProfilePhone);
        createProfile = findViewById(R.id.createCoordinatorProfileCreate);

        database = new Database(CreateCoordinatorProfileActivity.this);

        createProfile.setOnClickListener(v -> {
            String coordinatorName = String.valueOf(name.getText()).trim(),
                    email = String.valueOf(mail.getText()).trim(),
                    phoneNumber = String.valueOf(phone.getText()).trim();

            Coordinator coordinator = new Coordinator(0, coordinatorName, phoneNumber, email);
            database.insertCoordinator(coordinator);

            new Links(CreateCoordinatorProfileActivity.this).setProfile(email);
            Toast.makeText(CreateCoordinatorProfileActivity.this, "Profile created successfully!",
                    Toast.LENGTH_SHORT).show();

            startActivity(new Intent(CreateCoordinatorProfileActivity.this, StudentActivity.class)
                    .putExtra("uid", email));
            finish();
        });
    }
}