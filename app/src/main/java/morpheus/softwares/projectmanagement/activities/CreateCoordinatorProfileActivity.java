package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Coordinator;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.User;

public class CreateCoordinatorProfileActivity extends AppCompatActivity {
    ImageView back;
    EditText name, mail, phone;
    Button createProfile;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coordinator_profile);

        back = findViewById(R.id.createCoordinatorProfileBack);
        name = findViewById(R.id.createCoordinatorProfileName);
        mail = findViewById(R.id.createCoordinatorProfileEmail);
        phone = findViewById(R.id.createCoordinatorProfilePhone);
        createProfile = findViewById(R.id.createCoordinatorProfileCreate);

        database = new Database(this);

        back.setOnClickListener(v -> finish());

        createProfile.setOnClickListener(v -> {
            String coordinatorName = String.valueOf(name.getText()).trim(),
                    email = String.valueOf(mail.getText()).trim(),
                    phoneNumber = String.valueOf(phone.getText()).trim();

            ArrayList<User> users = database.selectAllUsers();
            for (User user : users)
                if (user.getEmail().equals(email)) {
                    Coordinator coordinator = new Coordinator(0, coordinatorName, phoneNumber, email);
                    database.insertCoordinator(coordinator);
                    database.updateUserStatus(email, getString(R.string.created));
                    Toast.makeText(this, "Profile created successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, CoordinatorActivity.class));
                    finish();
                }
        });
    }
}