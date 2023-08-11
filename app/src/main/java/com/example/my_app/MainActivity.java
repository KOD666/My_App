package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    EditText email,password;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = openOrCreateDatabase("database_app",MODE_PRIVATE,null);
        String tablequery = "CREATE TABLE IF NOT EXISTS USERS(UID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(100),CONTACT INT(10),EMAIL VARCHAR(100),PASSWORD VARCHAR(20))";
        database.execSQL(tablequery);
        email=findViewById(R.id.main_email);
        password=findViewById(R.id.main_pass);
        login=findViewById(R.id.main_login);
        signup=findViewById(R.id.main_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new CommonMethod(MainActivity.this,SignupActivity.class);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")) {
                    email.setError("Email Required");
                }
                else if(!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Invalid EMail Id");
                }
                else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                }
                else if (password.getText().toString().trim().length()<6) {
                    password.setError("Min. 6 Char Password Required");

            } else {
                    String selectquery = "SELECT * FROM USERS WHERE EMAIL='"+email.getText().toString()+"'AND PASSWORD='"+password.getText().toString()+"'";
                    Cursor cursor = database.rawQuery(selectquery,null);
                    if(cursor.getCount()>0){
                        while(cursor.moveToNext()){
                            String userid = cursor.getString(0);
                            String usernane = cursor.getString(1);
                            String usercontact = cursor.getString(2);
                            String useremail = cursor.getString(3);
                            String userpass = cursor.getString(4);
                        }
                  new CommonMethod(MainActivity.this,"Login Sucessfully");
                  new CommonMethod(view,"Login Sucessfully");
                  new CommonMethod(MainActivity.this,HomeActivity.class);
                }
                    else {
                        new CommonMethod(MainActivity.this,"Login Sucessfully");
                    }
                }
            }

        });
    }
}
