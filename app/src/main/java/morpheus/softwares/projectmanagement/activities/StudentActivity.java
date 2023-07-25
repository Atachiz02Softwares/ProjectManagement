package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Student;
import morpheus.softwares.projectmanagement.models.User;

public class StudentActivity extends AppCompatActivity {
    private final String[] AREAS = new Links(StudentActivity.this).getAreas();

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView studentName, studentID, studentNavName, studentNavEmail, firstProject, firstArea,
            firstSupervisor, firstStatus, secondProject, secondArea, secondSupervisor,
            secondStatus, thirdProject, thirdArea, thirdSupervisor, thirdStatus;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        appBarLayout = findViewById(R.id.studentAppBar);
        toolbar = findViewById(R.id.studentToolbar);
        collapsingToolbarLayout = findViewById(R.id.studentCollapsingToolnar);
        studentName = findViewById(R.id.studentName);
        studentID = findViewById(R.id.studentID);
        firstProject = findViewById(R.id.studentFirstTopic);
        firstArea = findViewById(R.id.studentFirstArea);
        firstSupervisor = findViewById(R.id.studentFirstSupervisor);
        firstStatus = findViewById(R.id.studentFirstStatus);
        secondProject = findViewById(R.id.studentSecondTopic);
        secondArea = findViewById(R.id.studentSecondArea);
        secondSupervisor = findViewById(R.id.studentSecondSupervisor);
        secondStatus = findViewById(R.id.studentSecondStatus);
        thirdProject = findViewById(R.id.studentThirdTopic);
        thirdArea = findViewById(R.id.studentThirdArea);
        thirdSupervisor = findViewById(R.id.studentThirdSupervisor);
        thirdStatus = findViewById(R.id.studentThirdStatus);
        drawerLayout = findViewById(R.id.studentDrawer);
        navigationView = findViewById(R.id.studentNavigator);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        database = new Database(StudentActivity.this);

//        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitle);

        // NavigationView items
        header = navigationView.getHeaderView(0);
        studentNavName = header.findViewById(R.id.studentNavName);
        studentNavEmail = header.findViewById(R.id.studentNavID);

        SharedPreferences sharedPreferences = getSharedPreferences("Profile", MODE_PRIVATE);
        String profile = sharedPreferences.getString("profile", "");
        String nil = "Create profile...";

        ArrayList<User> users = database.selectAllUsers();
        for (User user : users) {
            if (user.getIdentifier().equals(profile)) {
                String name = user.getName();
                studentName.setText(name);
                studentNavName.setText(name);
            } else {
                studentName.setText(nil);
                studentNavName.setText(nil);
            }
        }

        ArrayList<Student> students = database.selectAllStudents();
        for (Student student : students) {
            if (student.getIdNumber().equals(profile)) {
                String status = student.getStatus(), areaOne = student.getFirstArea(),
                        areaTwo = student.getSecondArea(), areaThree = student.getThirdArea(),
                        supervisorOne = new Links(StudentActivity.this).matchSupervisors(areaOne),
                        supervisorTwo = new Links(StudentActivity.this).matchSupervisors(areaTwo),
                        supervisorThree = new Links(StudentActivity.this).matchSupervisors(areaThree);
                studentID.setText(profile);
                studentNavEmail.setText(student.getEmail());
                firstProject.setText(student.getFirstProject());
                firstArea.setText(areaOne);
                firstSupervisor.setText(supervisorOne);
                firstStatus.setText(status);
                secondProject.setText(student.getSecondProject());
                secondArea.setText(areaTwo);
                secondSupervisor.setText(supervisorTwo);
                secondStatus.setText(status);
                thirdProject.setText(student.getThirdProject());
                thirdArea.setText(areaThree);
                thirdSupervisor.setText(supervisorThree);
                thirdStatus.setText(status);
            } else {
                studentName.setText(nil);
                studentNavName.setText(nil);
            }
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.createProfile) {
                String email = String.valueOf(studentNavEmail.getText()).trim();
                if (new Links(StudentActivity.this).checkProfile(email))
                    Toast.makeText(StudentActivity.this, "You can't create multiple profiles...", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(StudentActivity.this, CreateProfileActivity.class));
            } else if (item.getItemId() == R.id.viewApprovedTopic)
                Toast.makeText(StudentActivity.this, "View Approved Topic", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.complain)
                Toast.makeText(StudentActivity.this, "Complain", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.about)
                Toast.makeText(StudentActivity.this, "About", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.logout) {
                new Links(StudentActivity.this).removeStatus();
                startActivity(new Intent(StudentActivity.this, LoginActivity.class));
                finish();
            } else if (item.getItemId() == R.id.exit) finishAffinity();

            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }
}