package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReviewCartActivity extends AppCompatActivity {

    public static final String THIS_CONTEXT = "Review Cart Activity";
    private static final double TAX_RATE = 0.1;

    private Button btnBackToDemoHome6, btnCheckout;
    private RecyclerView recViewCartItems;
    private TextView txtViewTotalNumberOfItems, txtViewSubtotal, txtViewTax, txtViewTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);
        btnBackToDemoHome6 = findViewById(R.id.btnBackToDemoHome6);
        btnCheckout = findViewById(R.id.btnCheckout);
        recViewCartItems = findViewById(R.id.recViewCartItems);
        txtViewTotalNumberOfItems = findViewById(R.id.txtViewTotalNumOfItems);
        txtViewSubtotal = findViewById(R.id.txtViewSubtotal);
        txtViewTax = findViewById(R.id.txtViewTax);
        txtViewTotalCost = findViewById(R.id.txtViewTotalCost);

        btnBackToDemoHome6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewCartActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReviewCartActivity.this, CheckoutActivity.class));
            }
        });

        ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsInCart(), this, THIS_CONTEXT);
        recViewCartItems.setAdapter(itemsRecViewAdapter);
        recViewCartItems.setLayoutManager(new GridLayoutManager(this, 1));

        txtViewTotalNumberOfItems.setText("Total Number of Items: " + itemsRecViewAdapter.getItemCount());
        txtViewSubtotal.setText("Subtotal: " + itemsRecViewAdapter.subtotal());
        txtViewTax.setText("Tax: " + (itemsRecViewAdapter.subtotal() * TAX_RATE));
        txtViewTotalCost.setText("Total Cost: " + (itemsRecViewAdapter.subtotal() * (1 + TAX_RATE)));

        /* try {
            ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsInCart(), this, THIS_CONTEXT);
            recViewCartItems.setAdapter(itemsRecViewAdapter);
            recViewCartItems.setLayoutManager(new GridLayoutManager(this, 1));

            txtViewTotalNumberOfItems.setText("Total Number of Items: " + itemsRecViewAdapter.getItemCount());
            txtViewSubtotal.setText("Subtotal: " + itemsRecViewAdapter.subtotal());
            txtViewTax.setText("Tax: " + (itemsRecViewAdapter.subtotal() * TAX_RATE));
            txtViewTotalCost.setText("Total Cost: " + (itemsRecViewAdapter.subtotal() * (1 + TAX_RATE)));
        }
        catch (Exception e) {
            Toast.makeText(ReviewCartActivity.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
        } */
    }
}