package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ThirdFindNameByReligionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    //list to hold names of employees of a particular gender
    private List<String> religionList;

    private String religionChoice;
    private int activityNumber = 4;

    Spinner nameSpinner, religionSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_find_name_by_religion);

        //Spinner elements
        religionSpinner = (Spinner) findViewById(R.id.religionSpinner);
        nameSpinner = (Spinner) findViewById(R.id.lastNameSpinner);

        religionSpinner.setOnItemSelectedListener(this);
        nameSpinner.setOnItemSelectedListener(this);


        //Creating adapter for Spinner
        ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.religionCategories));


        // Load spinner from database with employee name
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        religionSpinner.setAdapter(religionAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        religionChoice = String.valueOf(religionSpinner.getSelectedItem());

        switch (parent.getId()) {

            case R.id.religionSpinner:

                //checks for first default value in the list
                if (!(religionChoice.equals("Choose Religion")))
                {

                    //Toast.makeText(this, religionChoice, Toast.LENGTH_SHORT).show();
                    EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);

                    // create a list of employees based on gender
                   religionList = dbHelper.getReligionEmployees(religionChoice);


                    //set up Spinner adapter for list of last names
                    ArrayAdapter<String> nameAdapter;

                    nameAdapter = new ArrayAdapter<String>
                            (this, android.R.layout.simple_spinner_item, religionList);

                    nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                    nameSpinner.setAdapter(nameAdapter);
                }
                break;

            case R.id.lastNameSpinner:

                religionChoice = String.valueOf(nameSpinner.getSelectedItem());

                //Checks for first default value in the list
                if (religionChoice.equals("Employee List")) {
                }
                else
                {
                    //pass name to the next activity which displays employee data
                    //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
                            //" selected", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ThirdFindNameByReligionActivity.this, FourthSetNameActivity.class);
                    intent.putExtra("activity",activityNumber);
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
        startActivity(new Intent (ThirdFindNameByReligionActivity.this,SecondSearchListActivity.class));
    }

}


