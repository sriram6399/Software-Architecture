package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private Button btnBackToDemoHome5, btnLaunchItem;
    private EditText edtTxtNewItemTitle, edtTxtNewItemPrice, edtTxtNewItemQuantity, edtTxtNewItemDescription;
    private TextView txtViewErrorMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        btnBackToDemoHome5 = findViewById(R.id.btnBackToDemoHome5);
        btnLaunchItem = findViewById(R.id.btnLaunchItem);
        edtTxtNewItemTitle = findViewById(R.id.edtTxtNewItemTitle);
        edtTxtNewItemPrice = findViewById(R.id.edtTxtNewItemPrice);
        edtTxtNewItemQuantity = findViewById(R.id.edtTxtNewItemQuantity);
        edtTxtNewItemDescription = findViewById(R.id.edtTxtNewItemDescription);
        txtViewErrorMessage2 = findViewById(R.id.txtViewErrorMessage2);

        btnBackToDemoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnLaunchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                AppManager.getInstance().writeItemToDatabase(edtTxtNewItemTitle.getText().toString(), edtTxtNewItemDescription.getText().toString(),
                                        Double.parseDouble(edtTxtNewItemPrice.getText().toString()), Integer.parseInt(edtTxtNewItemQuantity.getText().toString()));
                                startActivity(new Intent(AddItemActivity.this, SellersItemsActivity.class));
                                txtViewErrorMessage2.setVisibility(View.GONE);
                            }
                            catch (Exception e) {
                                txtViewErrorMessage2.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    thread.start();
                    /* try {
                        Item newItem = new Item(edtTxtNewItemTitle.getText().toString(), Double.parseDouble(edtTxtNewItemPrice.getText().toString()), edtTxtNewItemDescription.getText().toString());
                        AppManager.getInstance().addItemToSell(newItem);
                        startActivity(new Intent(AddItemActivity.this, SellersItemsActivity.class));
                    }
                    catch (Exception e) {
                        Toast.makeText(AddItemActivity.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                    } */
                }
                else {
                    Toast.makeText(AddItemActivity.this, "At least one field has not been filled in or contains an invalid entry.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateFields() {
        if (edtTxtNewItemTitle.getText().toString().length() == 0 || edtTxtNewItemDescription.getText().toString().length() == 0) {
            return false;
        }
        try {
            double enteredPrice = Double.parseDouble(edtTxtNewItemPrice.getText().toString());
            int enteredQuantity = Integer.parseInt(edtTxtNewItemQuantity.getText().toString());
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}