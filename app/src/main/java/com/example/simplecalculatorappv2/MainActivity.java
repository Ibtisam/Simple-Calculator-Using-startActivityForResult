package com.example.simplecalculatorappv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText num1_et, num2_et;
    private RadioGroup oper_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1_et = findViewById(R.id.num1_et);
        num2_et = findViewById(R.id.num2_et);
        oper_rg = findViewById(R.id.oper_rg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            if (resultCode == 98) {
                float result = data.getFloatExtra("RES", 0);
                showAlertDialog("" + result);
            }
        }
    }

    //This method will be used to handle click events of Calculate button
    public void button_calculate(View v) {
        float num_1, num_2;
        try {
            num_1 = Float.parseFloat(num1_et.getText().toString());
            num_2 = Float.parseFloat(num2_et.getText().toString());

            int rb_id = oper_rg.getCheckedRadioButtonId();
            RadioButton rb = findViewById(rb_id);

            //creating an intent
            Intent intent = new Intent();
            intent.setClass(this, CalculatorActivity.class);
            //adding some extra data
            intent.putExtra("NUM1", num_1);
            intent.putExtra("NUM2", num_2);
            intent.putExtra("OPER", rb.getText());

            //starting the activity using startActivityForResult, passing the created intent
            startActivityForResult(intent, 99);

        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAlertDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("OK", null);
        builder.setCancelable(true);
        builder.setTitle("Result from Calculator Activity");
        builder.setMessage("The answer is: " + text);
        // Set other dialog properties

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
