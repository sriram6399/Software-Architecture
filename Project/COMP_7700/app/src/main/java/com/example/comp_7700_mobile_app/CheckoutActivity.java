package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private Button btnBackToDemoHome2, btnPlaceOrder;
    private EditText edtTxtCardNumber, edtTxtCSC, edtTxtNameOnCard;
    private Spinner spinnerMonths, spinnerYears;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        btnBackToDemoHome2 = findViewById(R.id.btnBackToDemoHome2);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        edtTxtCardNumber = findViewById(R.id.edtTxtCardNumber);
        edtTxtCSC = findViewById(R.id.edtTxtCSC);
        edtTxtNameOnCard = findViewById(R.id.edtTxtNameOnCard);
        spinnerMonths = findViewById(R.id.spinnerMonths);
        spinnerYears = findViewById(R.id.spinnerYears);

        btnBackToDemoHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEnteredInformation()) {
                    startActivity(new Intent(CheckoutActivity.this, OrderConfirmationActivity.class));
                }
                else {
                    Toast.makeText(CheckoutActivity.this, "At least one field has not been filled in or contains an invalid entry.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateEnteredInformation() {
        if (edtTxtCardNumber.getText().toString().length() != 16) {
            return false;
        } else {
            int i;
            try {
                for(i = 0; i < 16; i += 4) {
                    int segmentNumber = Integer.parseInt(edtTxtCardNumber.getText().toString().substring(i, i + 4));
                    if (segmentNumber < 0) {
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                return false;
            }

            if (edtTxtCSC.getText().toString().length() != 3) {
                return false;
            } else {
                try {
                    i = Integer.parseInt(edtTxtCSC.getText().toString());
                } catch (NumberFormatException e) {
                    return false;
                }

                return edtTxtNameOnCard.getText().toString().length() != 0;
            }
        }
    }
}