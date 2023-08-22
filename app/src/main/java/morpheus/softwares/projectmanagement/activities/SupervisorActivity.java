package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.adapters.SupervisorAdapter;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Projects;
import morpheus.softwares.projectmanagement.models.Supervisor;
import morpheus.softwares.projectmanagement.models.User;

public class SupervisorActivity extends AppCompatActivity {
    TextView supervisorName, supervisorEmail, supervisorNavName, supervisorNavEmail, supervisorNavRole;
    Button viewSubmittedTopics;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<Projects> projects;
    SupervisorAdapter supervisorAdapter;
    RecyclerView recyclerView;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);

        appBarLayout = findViewById(R.id.supervisorAppBar);
        toolbar = findViewById(R.id.supervisorToolbar);
        collapsingToolbarLayout = findViewById(R.id.supervisorCollapsingToolnar);
        drawerLayout = findViewById(R.id.supervisorDrawer);
        navigationView = findViewById(R.id.supervisorNavigator);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        supervisorName = findViewById(R.id.supervisorName);
        supervisorEmail = findViewById(R.id.supervisorEmail);
        viewSubmittedTopics = findViewById(R.id.supervisorSubmittedProjects);

        database = new Database(this);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitle);

        header = navigationView.getHeaderView(0);
        supervisorNavName = header.findViewById(R.id.navName);
        supervisorNavEmail = header.findViewById(R.id.navEmail);
        supervisorNavRole = header.findViewById(R.id.navRole);
        supervisorNavRole.setText(R.string.supervisor);

        projects = new ArrayList<>();
        recyclerView = findViewById(R.id.supervisorList);
        supervisorAdapter = new SupervisorAdapter(this, projects);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(supervisorAdapter);

        SharedPreferences prefID = getSharedPreferences("ID", MODE_PRIVATE);
        String status = prefID.getString("id", null),
                nil = "Create profile...", id = getIntent().getStringExtra("email");

        supervisorName.setText(nil);
        supervisorNavName.setText(nil);
        supervisorEmail.setText("");
        supervisorNavEmail.setText("");

        ArrayList<Supervisor> supervisors = database.selectAllSupervisors();

        for (Supervisor supervisor : supervisors) {
            String name = supervisor.getName(), mail = supervisor.getEmail();
            if (mail.equals(id) || mail.equals(status)) {
                supervisorName.setText(name);
                supervisorNavName.setText(name);
                supervisorEmail.setText(mail);
                supervisorNavEmail.setText(mail);
                break;
            }
        }

        ArrayList<User> users = database.selectAllUsers();
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.createProfile) {
                boolean foundDesiredUser = false;

                for (User user : users) {
                    String email = user.getEmail(), stat = user.getStatus();

                    if ((email.equals(status) || email.equals(id)) && stat.equals(getString(R.string.created))) {
                        Toast.makeText(this, "You can't create multiple profiles...", Toast.LENGTH_SHORT).show();
                        foundDesiredUser = true;
                        break;
                    }
                }

                if (!foundDesiredUser)
                    startActivity(new Intent(this, CreateSupervisorProfileActivity.class));
            } else if (item.getItemId() == R.id.viewApprovedTopics)
                Toast.makeText(this, "View Approved Topic", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.complain)
                Toast.makeText(this, "Complain", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.about)
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.logout) {
                database.updateUserOnlineOfflineStatus(String.valueOf(supervisorEmail.getText()), "offline");
                finishAffinity();
            } else if (item.getItemId() == R.id.exit) finishAffinity();

            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });

        viewSubmittedTopics.setOnClickListener(v -> startActivity(new Intent(this, SubmittedTopicsActivity.class)));
    }
}