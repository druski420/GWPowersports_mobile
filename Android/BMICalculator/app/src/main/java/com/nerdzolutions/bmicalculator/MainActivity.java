package com.nerdzolutions.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables also called 'Fields'
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                }else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi() {
        // Setting variable values
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        // Converting the number 'Strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        // Height in inches
        int totalInches = (feet * 12) + inches;

        // Height in meters
        double heightInMeters = totalInches * 0.0254;

        // BMI Formula = weight * 703 divided by height in inches squared
        return (703 * weight) / (totalInches * totalInches);
    }

    private void displayResult(Double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            // Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            // Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        } else {
            // Display healthy
            fullResultString = bmiTextResult + " - You are a healthy weight";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()) {
            // Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            // Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for the healthy range for girls";
        } else {
            // Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for healthy range";
        }

        resultText.setText(fullResultString);
    }
}