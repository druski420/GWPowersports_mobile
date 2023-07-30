package com.nerdzolutions.inchesconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText inchesEditText;
    Button calculateButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inchesString = inchesEditText.getText().toString();
                if (inchesString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please input value", Toast.LENGTH_SHORT);
                } else {
                    double result = calculateHeight(inchesString);
                    displayResult(result);
                }

            }
        });
    }

    private void findViews() {
        inchesEditText = findViewById(R.id.edit_text_inches);
        calculateButton = findViewById(R.id.button_calculate);
        resultTextView = findViewById(R.id.text_view_result);
    }

    private double calculateHeight(String inchesString) {
        int inches = Integer.parseInt(inchesString);
        return inches / 12;
    }

    private void displayResult(double result) {
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String resultString = myFormatter.format(result);
        resultTextView.setText(resultString + " feet");
    }
}