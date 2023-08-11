package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {
    Button login,signup;
    EditText name, email, contact, password, confirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database = openOrCreateDatabase("database_app",MODE_PRIVATE,null);
        String tablequery = "CREATE TABLE IF NOT EXISTS USERS(UID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(100),CONTACT INT(10),EMAIL VARCHAR(100),PASSWORD VARCHAR(20))";
        database.execSQL(tablequery);
        name=findViewById(R.id.signup_user);
        contact=findViewById(R.id.signup_phone);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_pass);
        confirmPassword=findViewById(R.id.signup_cpass);
        login=findViewById(R.id.signup_log);
        signup=findViewById(R.id.signup_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("")) {
                    name.setError("Name Required");
                } else if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid EMail Id");
                } else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact No. Required");
                } else if (contact.getText().toString().trim().length() < 10) {
                    contact.setError("Valid Contact No. Required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 Char Password Required");
                } else if (confirmPassword.getText().toString().trim().equals("")) {
                    confirmPassword.setError("Confirm Password Required");
                } else if (confirmPassword.getText().toString().trim().length() < 6) {
                    confirmPassword.setError("Min. 6 Char Confirm Password Required");
                } else if (!confirmPassword.getText().toString().trim().matches(password.getText().toString().trim())) {
                    confirmPassword.setError("Confirm Password Does Not Match");
                } else {
                    String insertQuery = "INSERT INTO USERS VALUES(NULL,'"+name.getText().toString()+"','"+contact.getText().toString()+"','"+email.getText().toString()+"','"+password.getText().toString()+"')";
                    database.execSQL(insertQuery);
                    new CommonMethod(SignupActivity.this,"Signup Sucessfully");
                    new CommonMethod(view,"Signup Sucessfully");
                    onBackPressed();
                }
            }

        });
    }
}