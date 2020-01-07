package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ThirdFindNamebyAgeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    //list to hold names of employees of a particular gender
    private List<String> ageList;

    private String ageChoice, name,sendName;
    private int lowRange, highRange, index;
    private int activityNumber = 3;

    Spinner nameSpinner, ageSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_find_nameby_age);


        //Create Spinner element
        ageSpinner = (Spinner) findViewById(R.id.ageListSpinner);

        nameSpinner = (Spinner) findViewById(R.id.lastNameSpinner);


        //Button searchButton = (Button) findViewById(R.id.nextScreenButton);

        ageSpinner.setOnItemSelectedListener(this);
        nameSpinner.setOnItemSelectedListener(this);

        //Creating adapter for Spinner
        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.ageRanges));

        //searchCategories);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ageSpinner.setAdapter(searchAdapter);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
               // " selected", Toast.LENGTH_LONG).show();

        // checks for value chosen from the screen and launches appropriate activity


            ageChoice = String.valueOf(ageSpinner.getSelectedItem());

            switch (parent.getId()) {

                case R.id.ageListSpinner:
                    ageChoice = String.valueOf(ageSpinner.getSelectedItem());

                    //checks for first default value in the list
                    if (ageChoice.equals("Choose Age Range")) {
                        // do nothing

                        // check if female and create a list of only female employees
                    } else {
                        lowRange = Integer.parseInt(ageChoice.substring(0, 2));
                        highRange = Integer.parseInt(ageChoice.substring(6));


                        //loadSpinnerData(agechoice);

                        //Toast.makeText(this, ageChoice, Toast.LENGTH_SHORT).show();
                        EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);

                        //create a list with last names of chosen age range
                        ageList = dbHelper.getAgeEmployees(lowRange, highRange);
                        ArrayAdapter<String> nameAdapter;

                        //set up Spinner adapter for list of last names
                        nameAdapter = new ArrayAdapter<String>
                                (this, android.R.layout.simple_spinner_item, ageList);

                        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                        nameSpinner.setAdapter(nameAdapter);

                    }


                    break;


                case R.id.lastNameSpinner:

                    ageChoice = String.valueOf(nameSpinner.getSelectedItem());

                    //Checks for first default value in the list
                    if (ageChoice.equals("Employee List")) {
                    }
                    else
                    {
                        //pass name to the next activity which displays employee data
                        //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
                                //" selected", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ThirdFindNamebyAgeActivity.this, FourthSetNameActivity.class);
                        name = String.valueOf(parent.getItemAtPosition(position));
                        index = name.indexOf("age =");
                        sendName = name.substring(0,index-2);
                        intent.putExtra("activity",activityNumber);
                        intent.putExtra("data", sendName);
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
        startActivity(new Intent (ThirdFindNamebyAgeActivity.this,SecondSearchListActivity.class));
    }


}




