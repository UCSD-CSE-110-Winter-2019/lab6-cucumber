package edu.ucsd.cse110.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static edu.ucsd.cse110.calculator.Utils.toIntNullsafe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button equalsBtn = findViewById(R.id.btn_plus);
        equalsBtn.setOnClickListener(v -> {
            EditText num1View = findViewById(R.id.number_1);
            EditText num2View = findViewById(R.id.number_2);

            String num1Text = num1View.getText().toString();
            String num2Text = num2View.getText().toString();

            int num1 = toIntNullsafe(num1Text);
            int num2 = toIntNullsafe(num2Text);

            int answer = num1 + num2;

            TextView answerView = findViewById(R.id.answer);
            answerView.setText(String.valueOf(answer));
        });
    }
}
