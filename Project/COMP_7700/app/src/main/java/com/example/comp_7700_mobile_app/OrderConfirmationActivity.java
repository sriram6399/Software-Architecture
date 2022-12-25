package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

// TODO: add network- and/or server-related functionality if there should be any.
public class OrderConfirmationActivity extends AppCompatActivity {

    private Button btnBackToDemoHome3;
    private TextView txtViewOrderConfirmationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        txtViewOrderConfirmationMessage = findViewById(R.id.txtViewOrderConfirmationMessage);
        txtViewOrderConfirmationMessage.setText("Your order has been placed. Your confirmation number for this order is " + generateOrderConfirmationNumber() + ".");
        btnBackToDemoHome3 = findViewById(R.id.btnBackToDemoHome3);
        btnBackToDemoHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private String generateOrderConfirmationNumber() {
        String orderConfirmationNumber = "";
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < 10; i++) {
            Random characterOrDigitDecider = new Random();
            Random characterToUseDecider = new Random();
            Random digitToUseDecider = new Random();
            int characterOrDigit = characterOrDigitDecider.nextInt(2);
            if (characterOrDigit == 0) {
                orderConfirmationNumber += characters[characterToUseDecider.nextInt(characters.length)];
            }
            else {
                orderConfirmationNumber += digitToUseDecider.nextInt(10);
            }
        }
        return orderConfirmationNumber;
    }
}