package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Attachment;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Student;
import morpheus.softwares.projectmanagement.models.User;

public class StudentActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 10;
    private static String selectedFilePath;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    CardView first, second, third;

    TextView studentID, studentEmail, studentNavID, studentNavEmail, studentNavRole, firstProject,
            firstArea, firstSupervisor, firstStatus, secondProject, secondArea, secondSupervisor,
            secondStatus, thirdProject, thirdArea, thirdSupervisor, thirdStatus;
    AlertDialog alertDialog;
    MaterialAlertDialogBuilder builder;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        appBarLayout = findViewById(R.id.studentAppBar);
        toolbar = findViewById(R.id.studentToolbar);
        collapsingToolbarLayout = findViewById(R.id.studentCollapsingToolbar);

        first = findViewById(R.id.studentFirst);
        second = findViewById(R.id.studentSecond);
        third = findViewById(R.id.studentThird);

        studentID = findViewById(R.id.studentName);
        studentEmail = findViewById(R.id.studentID);
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
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        database = new Database(this);

//        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitle);

        // NavigationView items
        header = navigationView.getHeaderView(0);
        studentNavID = header.findViewById(R.id.navName);
        studentNavEmail = header.findViewById(R.id.navEmail);
        studentNavRole = header.findViewById(R.id.navRole);
        studentNavRole.setText(R.string.stdent);

        SharedPreferences prefID = getSharedPreferences("ID", MODE_PRIVATE);
        String status = prefID.getString("id", null),
                nil = "Create profile...", id = getIntent().getStringExtra(getString(R.string.mail));

        studentID.setText(nil);
        studentNavID.setText(nil);
        studentEmail.setText("");
        studentNavEmail.setText("");

        ArrayList<Student> students = database.selectAllStudents();
        for (Student student : students) {
            String email = student.getEmail();
            if (email.equals(status) || email.equals(id)) {
                first.setVisibility(View.VISIBLE);
                second.setVisibility(View.VISIBLE);
                third.setVisibility(View.VISIBLE);

                String idNumber = student.getIdNumber(),
                        status1 = student.getFirstStatus(), status2 = student.getSecondStatus(),
                        status3 = student.getThirdStatus(), areaOne = student.getFirstArea(),
                        areaTwo = student.getSecondArea(), areaThree = student.getThirdArea(),
                        supervisorOne = new Links(this).matchSupervisors(areaOne),
                        supervisorTwo = new Links(this).matchSupervisors(areaTwo),
                        supervisorThree = new Links(this).matchSupervisors(areaThree);
                studentID.setText(idNumber);
                studentNavID.setText(idNumber);
                studentEmail.setText(email);
                studentNavEmail.setText(email);
                firstProject.setText(student.getFirstProject());
                firstArea.setText(areaOne);
                firstSupervisor.setText(supervisorOne);
                firstStatus.setText(status1);
                secondProject.setText(student.getSecondProject());
                secondArea.setText(areaTwo);
                secondSupervisor.setText(supervisorTwo);
                secondStatus.setText(status2);
                thirdProject.setText(student.getThirdProject());
                thirdArea.setText(areaThree);
                thirdSupervisor.setText(supervisorThree);
                thirdStatus.setText(status3);
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
                    startActivity(new Intent(this, CreateStudentProfileActivity.class));
            } else if (item.getItemId() == R.id.viewApprovedTopics)
                Toast.makeText(this, "View Approved Topic", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.complain)
                Toast.makeText(this, "Complain", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.about)
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            else if (item.getItemId() == R.id.logout) {
                String email = String.valueOf(studentEmail.getText()), idNum = String.valueOf(studentID.getText());

                if (!idNum.equals(nil)) {
                    database.updateUserOnlineOfflineStatus(email, "offline");
                    finishAffinity();
                } else
                    Toast.makeText(this, "Create profile before logging out", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.exit) finishAffinity();

            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });

        first.setOnClickListener(v -> {
            if (String.valueOf(firstStatus.getText()).equals(getString(R.string.approved))) {
                showDialog();
            } else {
                Toast.makeText(this, "Topic not approved...", Toast.LENGTH_SHORT).show();
            }
        });

        second.setOnClickListener(v -> {
            if (String.valueOf(secondStatus.getText()).equals(getString(R.string.approved))) {
                showDialog();
            } else {
                Toast.makeText(this, "Topic not approved...", Toast.LENGTH_SHORT).show();
            }
        });

        third.setOnClickListener(v -> {
            if (String.valueOf(thirdStatus.getText()).equals(getString(R.string.approved))) {
                showDialog();
            } else {
                Toast.makeText(this, "Topic not approved...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        builder = new MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialogRounded);
        View view = getLayoutInflater().inflate(R.layout.import_dialog, null);
        Button button = view.findViewById(R.id.selectFileButton);
        TextView fileName = view.findViewById(R.id.selectedFileName);

        if (selectedFilePath != null)
            fileName.setText(selectedFilePath);

        if (String.valueOf(button.getText()).equals(getString(R.string.select)))
            button.setOnClickListener(v1 -> {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // All file types
                startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
            });
        else {
            button.setText(getString(R.string.submit));
            button.setOnClickListener(v12 -> {
                if (selectedFilePath != null) {
                    try {
                        database.insertAttachment(new Attachment(0, String.valueOf(studentEmail.getText()), selectedFilePath,
                                getBytesFromAttachment(new File(selectedFilePath))));
                    } catch (IOException e) {
                        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }
                } else
                    Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            });
        }

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Convert file to Byte Array
     */
    private byte[] getBytesFromAttachment(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        fis.close();
        return bos.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();

            // Get the selected file's name/path
            selectedFilePath = selectedFileUri.getPath();
//            View view = getLayoutInflater().inflate(R.layout.import_dialog, null);
//            TextView fileName = view.findViewById(R.id.selectedFileName);
//            fileName.setText(selectedFilePath);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
    }
}