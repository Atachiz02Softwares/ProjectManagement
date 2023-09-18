package morpheus.softwares.projectmanagement.activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.FileUtils;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Student;
import morpheus.softwares.projectmanagement.models.User;

public class StudentActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 10;
    private static final String[] FILE_TYPES = {"application/pdf", "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "text/plain"};
    private static final String[] FILE_EXTENSIONS = {".pdf", ".doc", ".docx", ".txt"};
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
            } else if (item.getItemId() == R.id.viewFiles)
                startActivity(new Intent(this, ViewFilesActivity.class));
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
            button.setOnClickListener(v1 -> chooseFile());
        else {
            button.setText(getString(R.string.submit));
            button.setOnClickListener(v12 -> {
                if (selectedFilePath != null) {
                    // Get the file extension
                    String fileExtension = selectedFilePath.substring(selectedFilePath.lastIndexOf("."));

                    // Generate a unique resource ID based on the file name
                    int resourceId = getResources().getIdentifier(
                            String.valueOf(studentID.getText()) + System.currentTimeMillis(),
                            "raw", getPackageName()
                    );

                    // Get the file's InputStream and copy it to raw resources
                    try {
                        InputStream inputStream = Files.newInputStream(Paths.get(selectedFilePath));
                        OutputStream outputStream = getResources().openRawResourceFd(resourceId).createOutputStream();

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }

                        inputStream.close();
                        outputStream.close();

                        Toast.makeText(this, "File saved to raw folder", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            });

        }

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }

//    /**
//     * Convert file to Byte Array
//     */
//    private byte[] getBytesFromAttachment(File file) throws IOException {
//        FileInputStream fis = new FileInputStream(file);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//
//        while ((bytesRead = fis.read(buffer)) != -1) {
//            bos.write(buffer, 0, bytesRead);
//        }
//
//        fis.close();
//        return bos.toByteArray();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                String filePath = FileUtils.getPath(this, uri);
                if (filePath != null) {
                    String fileName = new File(filePath).getName();
                    if (isValidFileType(fileName)) {
                        openFile(filePath);
                    } else {
                        Toast.makeText(this, "Invalid file type! Supported types: pdf, doc, docx," +
                                " txt...", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Log the URI and filePath for debugging
                    Log.e("FileUtils", "URI: " + uri);
                    Log.e("FileUtils", "FilePath is null");
                    Toast.makeText(this, "Error getting file path", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
    }

    /**
     * Checks permissions
     */
    private void checkFilePermission() {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_FILE_REQUEST_CODE);
        else chooseFile();
    }

    /**
     * Chooses a file from device storage
     */
    private void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");  // All file types
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    // Allows for file selection only when required permissions are granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_FILE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseFile();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @return true if the current uri contains the valid acceptable file extensions, otherwise false
     */
    private boolean isValidFileType(String fileName) {
        for (String extension : FILE_EXTENSIONS) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates an intent to open the selected file using an intent chooser. It sets the data and
     * type of the file and then creates a chooser intent. If there are no apps to open the file,
     * we catch the ActivityNotFoundException and show a toast message.
     */
    private void openFile(String filePath) {
        File file = new File(filePath);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), getMimeType(filePath));
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // In case there are no apps to open the file, handle the exception
            Toast.makeText(this, "No app to open this file,", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Used to determine the MIME type of the file based on its extension. This is important for
     * specifying the type when creating the intent.
     *
     * @return The MIME type of the file or null if there is none.
     */
    private String getMimeType(String filePath) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
    }

}