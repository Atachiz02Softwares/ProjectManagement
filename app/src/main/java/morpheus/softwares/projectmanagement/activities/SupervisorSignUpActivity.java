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

public class SupervisorSignUpActivity extends AppCompatActivity {
    EditText idNum, pinCode, confirmPinCode, studentName;
    Button createAccount;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_sign_up);

        idNum = findViewById(R.id.supervisorSignupEmail);
        pinCode = findViewById(R.id.supervisorSignupPin);
        confirmPinCode = findViewById(R.id.supervisorSignupConfirmPin);
        studentName = findViewById(R.id.supervisorSignupName);
        createAccount = findViewById(R.id.supervisorSignupCreateAccount);

        database = new Database(SupervisorSignUpActivity.this);

        String role = "supervisor";

        createAccount.setOnClickListener(v -> {
            String idNumber = String.valueOf(idNum.getText()).trim();
            String pin = String.valueOf(pinCode.getText()).trim();
            String confirmPin = String.valueOf(confirmPinCode.getText()).trim();
            String name = String.valueOf(studentName.getText()).trim();

            if (TextUtils.isEmpty(pin) || TextUtils.isEmpty(confirmPin) || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(name)) {
                Toast.makeText(SupervisorSignUpActivity.this, "No field should be empty!", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.equals(pin, confirmPin)) {
                Toast.makeText(SupervisorSignUpActivity.this, "Pins must be the same!", Toast.LENGTH_SHORT).show();
            } else {
                Users user = new Users(idNumber, pin, name, role);
                database.insertUser(user);
                new Links(SupervisorSignUpActivity.this).setStatus(role);
                startActivity(new Intent(SupervisorSignUpActivity.this, SupervisorActivity.class));
                finish();
            }
        });
    }
}