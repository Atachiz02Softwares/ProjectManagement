package morpheus.softwares.projectmanagement.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.adapters.ProjectsAdapter;
import morpheus.softwares.projectmanagement.adapters.SupervisorAdapter;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Student;

public class SubmittedTopicsActivity extends AppCompatActivity {
    ArrayList<Student> students;
    ProjectsAdapter projectsAdapter;
    RecyclerView recyclerView;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_topics);

        students = new ArrayList<>();
        recyclerView = findViewById(R.id.submittedTopicsList);
        projectsAdapter = new ProjectsAdapter(this, students);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(projectsAdapter);
    }
}