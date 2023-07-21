package morpheus.softwares.projectmanagement.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;

public class StudentSignUpActivity extends AppCompatActivity {
    EditText idNum, pinCode, confirmPinCode, studentName;
    Button createAccount;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        idNum = findViewById(R.id.studentSignupIDNumber);
        pinCode = findViewById(R.id.studentSignupPin);
        confirmPinCode = findViewById(R.id.studentSignupConfirmPin);
        studentName = findViewById(R.id.studentSignupName);
        createAccount = findViewById(R.id.studentSignupCreateAccount);

        database = new Database(StudentSignUpActivity.this);

        String idNumber = String.valueOf(idNum.getText());
        String pin = String.valueOf(pinCode.getText());
        String confirmPin = String.valueOf(confirmPinCode.getText());
        String name = String.valueOf(studentName.getText());

        createAccount.setOnClickListener(v -> {
            if (!pin.equals(confirmPin)) {
                pinCode.setError("Pin mismatch");
                confirmPinCode.setError("Confirm pin mismatch");
            } else {

            }
        });
    }

    /**
     * Sets the status of a user account creation to 'true' or 'false'
     */
    private void setStatus(String status) {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Status", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("status", status);

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
    }
}