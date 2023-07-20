package morpheus.softwares.projectmanagement.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Links;

public class StudentActivity extends AppCompatActivity {
    private final String[] AREAS = new Links().getAreas();
    AutoCompleteTextView firstArea;
    ArrayAdapter<String> firstAreaAdapter;
    AutoCompleteTextView secondArea;
    ArrayAdapter<String> secondAreaAdapter;
    AutoCompleteTextView thirdArea;
    ArrayAdapter<String> thirdAreaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        firstAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        firstArea.setAdapter(firstAreaAdapter);

        secondAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        secondArea.setAdapter(secondAreaAdapter);

        thirdAreaAdapter = new ArrayAdapter<>(this, R.layout.list_items, AREAS);
        thirdArea.setAdapter(thirdAreaAdapter);
    }
}