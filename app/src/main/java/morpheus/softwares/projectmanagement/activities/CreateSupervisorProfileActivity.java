package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Supervisor;

public class CreateSupervisorProfileActivity extends AppCompatActivity {
    String[] AREAS = new Links(CreateSupervisorProfileActivity.this).getAreas();
    ImageView back;
    EditText name, mail, phone;

    AutoCompleteTextView area;
    ArrayAdapter<String> areaAdapter;
    Button createProfile;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_supervisor_profile);

        back = findViewById(R.id.createSupervisorProfileBack);
        name = findViewById(R.id.createSupervisorProfileName);
        mail = findViewById(R.id.createSupervisorProfileEmail);
        phone = findViewById(R.id.createSupervisorProfilePhone);
        area = findViewById(R.id.createSupervisorProfileArea);
        createProfile = findViewById(R.id.createSupervisorProfileCreate);

        areaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        area.setAdapter(areaAdapter);

        database = new Database(this);

        back.setOnClickListener(v -> finish());

        createProfile.setOnClickListener(v -> {
            String supervisorName = String.valueOf(name.getText()).trim(),
                    email = String.valueOf(mail.getText()).trim(),
                    phoneNumber = String.valueOf(phone.getText()).trim(),
                    areaOfExpertise = String.valueOf(area.getText()).trim();

            Supervisor supervisor = new Supervisor(0, supervisorName, phoneNumber, email, areaOfExpertise);
            database.insertSupervisor(supervisor);

            new Links(this).setID(email);
            Toast.makeText(this, "Profile created successfully!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, SupervisorActivity.class).putExtra("uid", email));
            finish();
        });
    }
}