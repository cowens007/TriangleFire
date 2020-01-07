package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

//public class ThirdFindNameActivity extends AppCompatActivity
//{
public class ThirdFindNameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //create list to display names of all employees
    private List<String> lastNameList;
    private int activityNumber = 1;

    //create Spinner object
    Spinner nameSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_find_name);

        //Spinner element
        nameSpinner = (Spinner) findViewById(R.id.lastNameSpinner);
        nameSpinner.setOnItemSelectedListener(this);
        //nameButton = (Button) findViewById(R.id.backButton);

        // Load spinner from database with employee name

        EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);
        lastNameList = dbHelper.getLastNameAllEmployees();

        ArrayAdapter<String> nameAdapter;

        //create a list with just the last names

        nameAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, lastNameList);


        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        nameSpinner.setAdapter(nameAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //check if not default value and launch activity to display the chosen name
        if (!(parent.getItemAtPosition(position).equals("Employee List")) ) {

            //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
                    //" selected", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ThirdFindNameActivity.this,FourthSetNameActivity.class);
            intent.putExtra("activity",activityNumber);
            intent.putExtra("data", String.valueOf(parent.getItemAtPosition(position)));
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    // go back to the previous screen
    public void previous (View view)
    {
        startActivity(new Intent (ThirdFindNameActivity.this,SecondSearchListActivity.class));
    }
}








