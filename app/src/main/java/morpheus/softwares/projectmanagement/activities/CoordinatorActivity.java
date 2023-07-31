package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import morpheus.softwares.projectmanagement.adapters.CoodinatorAdapter;
import morpheus.softwares.projectmanagement.models.Coordinator;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Student;

public class CoordinatorActivity extends AppCompatActivity {
    TextView coordinatorName, coordinatorEmail, coordinatorNavName, coordinatorNavEmail;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<Student> students;
    CoodinatorAdapter coodinatorAdapter;
    RecyclerView recyclerView;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        appBarLayout = findViewById(R.id.coordinatorAppBar);
        toolbar = findViewById(R.id.coordinatorToolbar);
        collapsingToolbarLayout = findViewById(R.id.coordinatorCollapsingToolnar);
        drawerLayout = findViewById(R.id.coordinatorDrawer);
        navigationView = findViewById(R.id.coordinatorNavigator);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        coordinatorName = findViewById(R.id.coordinatorName);
        coordinatorEmail = findViewById(R.id.coordinatorEmail);

        database = new Database(CoordinatorActivity.this);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitle);

        header = navigationView.getHeaderView(0);
        coordinatorNavName = header.findViewById(R.id.coordinatorNavName);
        coordinatorNavEmail = header.findViewById(R.id.coordinatorNavEmail);

        students = new ArrayList<>();
        recyclerView = findViewById(R.id.projectsList);
        coodinatorAdapter = new CoodinatorAdapter(CoordinatorActivity.this, students);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(coodinatorAdapter);

        ArrayList<Coordinator> coordinators = database.selectAllCoordinators();

        for (Coordinator coordinator : coordinators) {
            String name = coordinator.getName(), email = coordinator.getEmail();

            coordinatorName.setText(name);
            coordinatorNavName.setText(name);
            coordinatorEmail.setText(email);
            coordinatorNavEmail.setText(email);
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.createProfile) {
                String email = String.valueOf(coordinatorNavEmail.getText()).trim();
                if (new Links(CoordinatorActivity.this).checkProfile(email))
                    Toast.makeText(CoordinatorActivity.this, "You can't create multiple profiles." +
                            "..", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(CoordinatorActivity.this, CreateStudentProfileActivity.class));
            } else if (item.getItemId() == R.id.viewApprovedTopic)
                Toast.makeText(CoordinatorActivity.this, "View Approved Topic", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.complain)
                Toast.makeText(CoordinatorActivity.this, "Complain", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.about)
                Toast.makeText(CoordinatorActivity.this, "About", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.logout) {
                new Links(CoordinatorActivity.this).removeStatus();
                startActivity(new Intent(CoordinatorActivity.this, LoginActivity.class));
                finish();
            } else if (item.getItemId() == R.id.exit) finishAffinity();

            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }
}