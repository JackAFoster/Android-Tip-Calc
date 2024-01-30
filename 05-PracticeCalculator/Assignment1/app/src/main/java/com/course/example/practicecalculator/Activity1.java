package com.course.example.practicecalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Activity1 extends AppCompatActivity {
    //Storing values for later
    EditText amount, people,tip_percentage;
    TextView total_bill,total_per_person,total_tip,tip_total_per_person;
    Button go,mapButton,dialButton, webButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//type casting
        go=findViewById(R.id.go);
        amount=findViewById(R.id.amount);
        people =findViewById(R.id.people);
        tip_percentage=findViewById(R.id.tip);
        total_bill=findViewById(R.id.total_bill);
        total_per_person=findViewById(R.id.total_per_person);
        total_tip=findViewById(R.id.total_tip);
        tip_total_per_person=findViewById(R.id.tip_per_person);
        mapButton = findViewById(R.id.mapButton);
        dialButton = findViewById(R.id.dialButton);
        webButton = findViewById(R.id.webButton);
//New things

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Uri from an intent string. Use the GEO URI scheme
                // for a map intent
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Bentley+campus");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:7818912000"));
                startActivity(dialIntent);
            }
        });

        webButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this, WebLookup.class);
                int WEB_LOOKUP_REQUEST_CODE = 0;
                startActivityForResult(intent, WEB_LOOKUP_REQUEST_CODE);

            }
        });

//Allows the button to be clicked
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//getting user input for calculations
                String a=amount.getText().toString();
                String b= people.getText().toString();
                String c=tip_percentage.getText().toString();

                float money=Float.parseFloat(a);
                float people=Float.parseFloat(b);
                float tax=Float.parseFloat(c);
//Formatting data into final answers and formatting for 2 decimal places
                    float data1=money+((money*tax)/100);
                    String ShortData1 = String.format("%.02f", data1);
                    float data2=data1/people;
                    String ShortData2 = String.format("%.02f", data2);
                    float data3=(money*tax)/100;
                    String ShortData3 = String.format("%.02f", data3);
                    float data4=data3/people;
                    String ShortData4 = String.format("%.02f", data4);


//Final display
                    total_bill.setText("$"+String.valueOf(ShortData1));
                    total_per_person.setText("$"+String.valueOf(ShortData2));
                    total_tip.setText("$"+String.valueOf(ShortData3));
                    tip_total_per_person.setText("$"+String.valueOf(ShortData4));
                }




        });

    }
}