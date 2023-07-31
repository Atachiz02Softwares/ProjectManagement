package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Links;

public class SupervisorActivity extends AppCompatActivity {
    Button button;
//    ArrayList<Projects> projects;
//    ProjectsAdapter projectsAdapter;
//    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);

        button = findViewById(R.id.logout);

//        projects = new ArrayList<>();
//        recyclerView = findViewById(R.id.studentList);
//        projectsAdapter = new ProjectsAdapter(StudentActivity.this, projects);
//        recyclerView.setHasFixedSize(true);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(projectsAdapter);

        button.setOnClickListener(v -> {
            new Links(SupervisorActivity.this).removeStatus();
            startActivity(new Intent(SupervisorActivity.this, LoginActivity.class));
            finish();
        });
    }
}