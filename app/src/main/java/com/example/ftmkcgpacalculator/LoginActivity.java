package com.example.ftmkcgpacalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edtTxtUser, edtTxtMatric;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getApplicationContext().getSharedPreferences("CGPACalculator",0);
        editor = sharedPreferences.edit();

        edtTxtUser = findViewById(R.id.edtTxtUser);
        edtTxtMatric = findViewById(R.id.edtTxtMatric);

        username = sharedPreferences.getString("username","");

        if(!(username.equalsIgnoreCase("")))
        {
            Intent intent = new Intent(this, SubjectDetailsActivity.class);
            startActivity(intent);
        }





    }

    public void fnLogin(View view)
    {
        editor.putString("username",edtTxtUser.getText().toString());
        editor.putString("matricno",edtTxtMatric.getText().toString());
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, SubjectDetailsActivity.class);
        startActivity(intent);

    }
}
