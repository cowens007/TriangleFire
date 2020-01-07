package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FourthSetNameActivity extends AppCompatActivity {

    //create list that stores employee data for the name that was passed
    private List<Employees> EmployeeList;
    private int activityNumber;
    private String activityText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_set_name);


            //create variables for data to be displayed
            TextView firstName = (TextView) findViewById(R.id.firstNameOutputText);
            TextView lastName = (TextView) findViewById(R.id.lastNameOutputText);
            TextView sex = (TextView) findViewById(R.id.sexOutputText);
            TextView age = (TextView) findViewById(R.id.ageOutputNumber);
            TextView religion = (TextView) findViewById(R.id.religionOutputText);

            //setting passed data
            Bundle bundle = getIntent().getExtras();
            String allText = bundle.get("data").toString();
            activityText = bundle.get("activity").toString();


            //create list with all employee info of name that was passed
            EmployeeDBHelper dbHelper = new EmployeeDBHelper(this);
            EmployeeList = dbHelper.getOneEmployee(allText);


            //display employee data from list
            String employeeFirstName = EmployeeList.get(0).getFirstName();

            firstName.setText(employeeFirstName);

            String employeeLastName = EmployeeList.get(0).getLastName();

            lastName.setText(employeeLastName);

            String employeeSex = EmployeeList.get(0).getSex();

            sex.setText(employeeSex);

            String employeeAge = EmployeeList.get(0).getAge() + " ";

            age.setText(employeeAge);

            String  employeeReligion = EmployeeList.get(0).getReligion();

            religion.setText(employeeReligion);


    }

    // go back to the previous screen
    public void previous (View view)
    {
        activityNumber = Integer.parseInt(activityText);

        //check which screen to go back to
        switch (activityNumber)
        {
            case 1:
                startActivity(new Intent(FourthSetNameActivity.this,ThirdFindNameActivity.class));
                break;

            case 2:
                startActivity(new Intent(FourthSetNameActivity.this,ThirdFindNameBySexActivity.class));
                break;

            case 3:
                startActivity(new Intent(FourthSetNameActivity.this,ThirdFindNamebyAgeActivity.class));
                break;

            case 4:
                startActivity(new Intent(FourthSetNameActivity.this,ThirdFindNameByReligionActivity.class));
                break;
            default:
                break;
        }


    }
}

