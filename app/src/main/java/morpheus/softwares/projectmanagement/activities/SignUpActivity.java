package morpheus.softwares.projectmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Users;

public class SignUpActivity extends AppCompatActivity {
    private final String[] ROLES = new Links(SignUpActivity.this).getRoles();
    EditText idNum, pinCode, confirmPinCode, studentName;
    AutoCompleteTextView role;
    ArrayAdapter<String> roleAdapter;
    TextView login;
    Button createAccount;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        idNum = findViewById(R.id.signUpID);
        pinCode = findViewById(R.id.signUpPin);
        confirmPinCode = findViewById(R.id.signUpConfirmPin);
        studentName = findViewById(R.id.signUpName);
        role = findViewById(R.id.signUpAs);
        login = findViewById(R.id.signUpLogin);
        createAccount = findViewById(R.id.signUpCreateAccount);

        roleAdapter = new ArrayAdapter<>(this, R.layout.list_items, ROLES);
        role.setAdapter(roleAdapter);

        database = new Database(SignUpActivity.this);

        login.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this, LoginActivity.class)));

        createAccount.setOnClickListener(v -> {
            String idNumber = String.valueOf(idNum.getText()).trim(),
                    pin = String.valueOf(pinCode.getText()).trim(),
                    confirmPin = String.valueOf(confirmPinCode.getText()).trim(),
                    name = String.valueOf(studentName.getText()).trim(),
                    signUpAs = String.valueOf(role.getText());

            if (TextUtils.isEmpty(pin) || TextUtils.isEmpty(confirmPin) || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(name)) {
                Toast.makeText(SignUpActivity.this, "No field should be empty!", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.equals(pin, confirmPin)) {
                Toast.makeText(SignUpActivity.this, "Pins must be the same!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUpActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                Users user = new Users(idNumber, pin, name, signUpAs);
                database.insertUser(user);
                new Links(SignUpActivity.this).setStatus(signUpAs);

                switch (signUpAs) {
                    case "student":
                        startActivity(new Intent(SignUpActivity.this, StudentActivity.class));
                        finish();
                        break;
                    case "supervisor":
                        startActivity(new Intent(SignUpActivity.this, SupervisorActivity.class));
                        finish();
                        break;
                    case "coordinator":
                        startActivity(new Intent(SignUpActivity.this, CoordinatorActivity.class));
                        finish();
                        break;
                }
            }
        });
    }
}