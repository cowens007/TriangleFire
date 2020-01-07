// Triangle Fire Main Program for app
// Author:  Chris Owens
// Date:  10/3/19
// Version: 1.0

package com.example.trianglefire;

// imports
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Main class - starts mainActivity screen
// When start button is clicked goes to secondActivity screen
public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attempts to launch SecondSearchListActivity within the app
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent startIntent = new Intent(MainActivity.this,SecondSearchListActivity.class);

                startActivity(startIntent);

            }
        });

    }
}
