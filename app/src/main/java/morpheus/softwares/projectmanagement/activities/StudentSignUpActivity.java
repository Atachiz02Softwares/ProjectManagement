package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Users;

public class StudentSignUpActivity extends AppCompatActivity {
    EditText idNum, pinCode, confirmPinCode, studentName;
    Button createAccount;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        idNum = findViewById(R.id.studentSignupID);
        pinCode = findViewById(R.id.studentSignupPin);
        confirmPinCode = findViewById(R.id.studentSignupConfirmPin);
        studentName = findViewById(R.id.studentSignupName);
        createAccount = findViewById(R.id.studentSignupCreateAccount);

        database = new Database(StudentSignUpActivity.this);

        String role = "student";

        createAccount.setOnClickListener(v -> {
            String idNumber = String.valueOf(idNum.getText()).trim();
            String pin = String.valueOf(pinCode.getText()).trim();
            String confirmPin = String.valueOf(confirmPinCode.getText()).trim();
            String name = String.valueOf(studentName.getText()).trim();

            if (TextUtils.isEmpty(pin) || TextUtils.isEmpty(confirmPin) || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(name)) {
                Toast.makeText(StudentSignUpActivity.this, "No field should be empty!", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.equals(pin, confirmPin)) {
                Toast.makeText(StudentSignUpActivity.this, "Pins must be the same!", Toast.LENGTH_SHORT).show();
            } else {
                Users user = new Users(idNumber, pin, name, role);
                database.insertUser(user);
                new Links(StudentSignUpActivity.this).setStatus(role);
                startActivity(new Intent(StudentSignUpActivity.this, StudentActivity.class));
                finish();
            }
        });
    }
}