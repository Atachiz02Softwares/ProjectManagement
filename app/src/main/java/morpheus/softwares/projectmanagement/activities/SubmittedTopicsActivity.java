package morpheus.softwares.projectmanagement.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.adapters.ProjectsAdapter;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Student;

public class SubmittedTopicsActivity extends AppCompatActivity {
    TextView title;
    EditText search;

    ArrayList<Student> students;
    ProjectsAdapter projectsAdapter;
    RecyclerView recyclerView;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_topics);

        title = findViewById(R.id.t);
        search = findViewById(R.id.search);

        database = new Database(this);

        String area = getIntent().getStringExtra("area");

        students = database.selectAllStudents();
        recyclerView = findViewById(R.id.submittedTopicsList);
        projectsAdapter = new ProjectsAdapter(this, students);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(projectsAdapter);

        title.setOnClickListener(v -> finish());

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterStudents(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterStudents(String string) {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        for (Student student : students) {
            if (student.getIdNumber().toLowerCase().contains(string.toLowerCase())) {
                filteredStudent.add(student);
            }
        }

        if (filteredStudent.isEmpty())
            Toast.makeText(this, string + " not found...", Toast.LENGTH_SHORT).show();
        else
            projectsAdapter.filter(filteredStudent);
    }
}