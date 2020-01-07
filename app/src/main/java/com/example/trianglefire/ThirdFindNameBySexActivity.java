package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ThirdFindNameBySexActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    //list to hold names of employees of a particular gender
    private List<String> genderList;

    private String genderChoice;
    private int activityNumber = 2;

    Spinner nameSpinner, genderSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_find_name_by_sex);

        //Spinner elements
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        nameSpinner = (Spinner) findViewById(R.id.lastNameSpinner);

        genderSpinner.setOnItemSelectedListener(this);
        nameSpinner.setOnItemSelectedListener(this);


        //Creating adapter for Spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.genderCategories));


        // Load spinner from database with employee name
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        genderChoice = String.valueOf(genderSpinner.getSelectedItem());

        switch (parent.getId()) {

            case R.id.genderSpinner:
                genderChoice = String.valueOf(genderSpinner.getSelectedItem());

                //checks for first default value in the list
                if (!(genderChoice.equals("Choose Gender")))
                    {
                    genderChoice = genderChoice.substring(0, 1);

                    //Toast.makeText(this, genderChoice, Toast.LENGTH_SHORT).show();
                    EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);

                    // create a list of employees based on gender
                        genderList = dbHelper.getGenderEmployees(genderChoice);


                    //set up Spinner adapter for list of last names
                    ArrayAdapter<String> nameAdapter;

                    nameAdapter = new ArrayAdapter<String>
                            (this, android.R.layout.simple_spinner_item, genderList);

                    nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                    nameSpinner.setAdapter(nameAdapter);
                }
                break;

            case R.id.lastNameSpinner:

                genderChoice = String.valueOf(nameSpinner.getSelectedItem());

                //Checks for first default value in the list
                if (genderChoice.equals("Employee List")) {
                }
                else
                {
                    //pass name to the next activity which displays employee data
                    //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
                            //" selected", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ThirdFindNameBySexActivity.this, FourthSetNameActivity.class);
                    intent.putExtra("activity", activityNumber);
                    intent.putExtra("data", String.valueOf(parent.getItemAtPosition(position)));
                    startActivity(intent);
                }
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    // go back to the previous screen
    public void previous (View view)
    {
        startActivity(new Intent (ThirdFindNameBySexActivity.this,SecondSearchListActivity.class));
    }

}
