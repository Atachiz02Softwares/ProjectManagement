package morpheus.softwares.projectmanagement.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Students;

public class CreateProfileActivity extends AppCompatActivity {
    String[] AREAS = new Links(CreateProfileActivity.this).getAreas();
    EditText id, mail, firstTopic, secondTopic, thirdTopic;

    AutoCompleteTextView firstArea;
    ArrayAdapter<String> firstAreaAdapter;
    AutoCompleteTextView secondArea;
    ArrayAdapter<String> secondAreaAdapter;
    AutoCompleteTextView thirdArea;
    ArrayAdapter<String> thirdAreaAdapter;

    RadioGroup grouping;
    RadioButton alone, group;
    Button createProfile;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        id = findViewById(R.id.createProfileEmail);
        mail = findViewById(R.id.createProfileEmail);
        firstTopic = findViewById(R.id.createProfileFirstProjectName);
        secondTopic = findViewById(R.id.createProfileSecondProjectName);
        thirdTopic = findViewById(R.id.createProfileThirdProjectName);
        firstArea = findViewById(R.id.createProfileFirstProjectArea);
        secondArea = findViewById(R.id.createProfileSecondProjectArea);
        thirdArea = findViewById(R.id.createProfileThirdProjectArea);
        grouping = findViewById(R.id.createProfileGrouping);
        alone = findViewById(R.id.createProfileAlone);
        group = findViewById(R.id.createProfileGroup);
        createProfile = findViewById(R.id.createProfileCreate);

        firstAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        firstArea.setAdapter(firstAreaAdapter);

        secondAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        secondArea.setAdapter(secondAreaAdapter);

        thirdAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        thirdArea.setAdapter(thirdAreaAdapter);

        database = new Database(CreateProfileActivity.this);

        createProfile.setOnClickListener(v -> {
            String idNumber = String.valueOf(id.getText()).trim(),
                    email = String.valueOf(mail.getText()).trim(),
                    firstProject = String.valueOf(firstTopic.getText()).trim(),
                    areaOne = String.valueOf(firstArea.getText()).trim(),
                    secondProject = String.valueOf(secondTopic.getText()).trim(),
                    areaTwo = String.valueOf(secondArea.getText()).trim(),
                    thirdProject = String.valueOf(thirdTopic.getText()).trim(),
                    areaThree = String.valueOf(thirdArea.getText()).trim(),
                    aloneGroup = alone.isChecked() ? String.valueOf(alone.getText()).trim() :
                            group.isChecked() ? String.valueOf(group.getText()).trim() : null;

            Students student = new Students(0, idNumber, email, firstProject, secondProject,
                    thirdProject, areaOne, areaTwo, areaThree, aloneGroup);
            database.insertStudent(student);
            Toast.makeText(CreateProfileActivity.this, "Profile created successfully!", Toast.LENGTH_SHORT).show();
        });
    }
}