package com.example.trianglefire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SecondSearchListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_search_list);

        //Create Spinner element
        final Spinner searchSpinner = (Spinner) findViewById(R.id.searchListSpinner);
        //Button searchButton = (Button) findViewById(R.id.nextScreenButton);

        searchSpinner.setOnItemSelectedListener(this);

        //Creating adapter for Spinner
        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.searchCategories));

                //searchCategories);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchSpinner.setAdapter(searchAdapter);


    }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) +
                       // " selected", Toast.LENGTH_LONG).show();

                // checks for value chosen from the screen and launches appropriate activity
                if (!(parent.getItemAtPosition(position).equals("Choose Category"))) {

                    // launch activity to find information by choosing a persons name
                    if (parent.getItemAtPosition(position).equals("Name")) {
                        Intent intent = new Intent(SecondSearchListActivity.this, ThirdFindNameActivity.class);

                        startActivity(intent);

                    // launch activity to find information by choosing a persons gender
                    } else if (parent.getItemAtPosition(position).equals("Gender")) {
                        Intent intent = new Intent(SecondSearchListActivity.this, ThirdFindNameBySexActivity.class);

                        startActivity(intent);

                    //launch activity to find information by choosing a persons age
                    } else if (parent.getItemAtPosition(position).equals("Age")) {
                        Intent intent = new Intent(SecondSearchListActivity.this, ThirdFindNamebyAgeActivity.class);

                        startActivity(intent);

                    //launch activity to find information by choosing a persons religion
                    } else {
                        Intent intent = new Intent(SecondSearchListActivity.this, ThirdFindNameByReligionActivity.class);

                        startActivity(intent);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

}

