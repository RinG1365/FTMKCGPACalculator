package com.example.ftmkcgpacalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Subjects;
import sqlitesubject.SubjectsDB;

public class SubjectDetailsActivity extends AppCompatActivity {

    EditText edtTxtSubCode, edtTxtSubName,edtTxtSubCredit;
    TextView txtVwCGPA ;
    Spinner spinner;
    SharedPreferences sharedPreferences;
    String username;
    Subjects subjects;
    SubjectsDB subjectsDB;
    ArrayList<Subjects> subjectList;
    Double subjectCredit;
    String subjectGrade;
    public Double totalSubjectCredit=0.00;
    public Double    gradePointer=0.00;
    public Double    subPointer=0.00;
    public Double   totalSubPointer=0.00;
    public Double   CGPA = 0.00;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        sharedPreferences = getApplicationContext().getSharedPreferences("CGPACalculator",0);
        username = sharedPreferences.getString("username",null);


            Toast.makeText(getApplicationContext(), "Welcome Back " + username, Toast.LENGTH_SHORT).show();

            edtTxtSubCode = findViewById(R.id.edtTxtSubCode);
            edtTxtSubName = findViewById(R.id.edtTxtSubName);
            edtTxtSubCredit = findViewById(R.id.edtTxtSubCredit);
            txtVwCGPA = findViewById(R.id.txtVwCGPA);
            spinner = findViewById(R.id.spinner);

            subjectsDB = new SubjectsDB(getApplicationContext());

    }

    public void fnSave(View view)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Are you sure you want to add?");
        alertBuilder.setPositiveButton(getString(R.string.btnPositive), new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        totalSubPointer = 0.00;
                        totalSubjectCredit =0.00;
                        subjects = new Subjects();
                        subjects.setStrSubCode(edtTxtSubCode.getText().toString());
                        subjects.setStrSubName(edtTxtSubName.getText().toString());
                        subjects.setStrSubCredit(Integer.parseInt(edtTxtSubCredit.getText().toString()));
                        subjects.setStrSubGrade(String.valueOf(spinner.getSelectedItem()));

                        subjectsDB.fnInsertSubject(subjects);
                        Toast.makeText(getApplicationContext(),"Subjects Saved",Toast.LENGTH_SHORT).show();

                        subjectList = (ArrayList<Subjects>) subjectsDB.fnGetAllSubjects();

                        for(Subjects sub : subjectList)
                        {
                            subjectCredit = Double.valueOf(sub.getStrSubCredit());
                            subjectGrade = sub.getStrSubGrade();
                            totalSubjectCredit += subjectCredit;


                            if(subjectGrade.equalsIgnoreCase("A"))
                            {
                                gradePointer = 4.00;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("A-"))
                            {
                                gradePointer = 3.70;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("B+"))
                            {
                                gradePointer = 3.30;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("B"))
                            {
                                gradePointer = 3.00;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("B-"))
                            {
                                gradePointer = 2.70;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("C+"))
                            {
                                gradePointer = 2.30;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("C"))
                            {
                                gradePointer = 2.00;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("C-"))
                            {
                                gradePointer = 1.70;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("D+"))
                            {
                                gradePointer = 1.30;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("D"))
                            {
                                gradePointer = 1.00;
                                subPointer = subjectCredit * gradePointer;

                            }
                            else if(subjectGrade.equalsIgnoreCase("E"))
                            {
                                gradePointer = 0.00;
                                subPointer = subjectCredit * gradePointer;

                            }

                            totalSubPointer += subPointer;


                        }

                        CGPA = totalSubPointer / totalSubjectCredit;
                        txtVwCGPA.setText("Your Current CGPA : "+ CGPA);



                    }
                }
        );

        alertBuilder.setNegativeButton(getString(R.string.btnNegative), new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        alertBuilder.show();
    }



}
