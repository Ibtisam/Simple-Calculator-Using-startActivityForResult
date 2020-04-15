package com.example.simplecalculatorappv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    private TextView num1_tv, num2_tv, ans_tv;
    private float ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //getting the intent that was passed when this activity was started
        Intent intent = getIntent();

        //getting the extra data in the intent
        float num1 = intent.getFloatExtra("NUM1", 0);
        float num2 = intent.getFloatExtra("NUM2", 0);
        String oper = intent.getStringExtra("OPER");

        //getting the references of TextViews
        num1_tv = findViewById(R.id.num1_tv);
        num2_tv = findViewById(R.id.num2_tv);
        ans_tv = findViewById(R.id.ans_tv);

        //setting the text of
        num1_tv.setText("Number 1: " + num1);
        num2_tv.setText("Number 2: " + num2);

        ans = 0;

        if (oper.equalsIgnoreCase("addition")) {
            ans = num1 + num2;
        } else if (oper.equalsIgnoreCase("subtraction")) {
            ans = num1 - num2;
        } else if (oper.equalsIgnoreCase("multiplication")) {
            ans = num1 * num2;
        } else {
            if (num2 != 0) {
                ans = num1 / num2;
            } else {
                Toast.makeText(this, "Divided by zero error", Toast.LENGTH_SHORT).show();
            }
        }
        ans_tv.setText("The answer will be send back to previous activity");
    }

    //this method will be called when send result button will be clicked
    public void send_button(View v) {
        //creating an intent to send result back to main activity
        Intent resIntent = new Intent();
        resIntent.putExtra("RES", ans);
        //setting the result
        setResult(98, resIntent);
        //this method will close the current activity
        finish();
    }
}
