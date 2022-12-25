package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SellersItemsActivity extends AppCompatActivity {

    public static final String THIS_CONTEXT = "Seller's Items Activity";

    private Button btnBackToDemoHome4, btnAddItem;
    private RecyclerView recViewItemsBeingSold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_items);
        btnBackToDemoHome4 = findViewById(R.id.btnBackToDemoHome4);
        btnAddItem = findViewById(R.id.btnAddItem);
        recViewItemsBeingSold = findViewById(R.id.recViewItemsBeingSold);

        ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsBeingSold(), this, THIS_CONTEXT);
        recViewItemsBeingSold.setAdapter(itemsRecViewAdapter);
        recViewItemsBeingSold.setLayoutManager(new GridLayoutManager(this, 1));

        /* try {
            ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsBeingSold(), this, THIS_CONTEXT);
            recViewItemsBeingSold.setAdapter(itemsRecViewAdapter);
            recViewItemsBeingSold.setLayoutManager(new GridLayoutManager(this, 1));
        }
        catch (Exception e) {
            Toast.makeText(SellersItemsActivity.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
        } */

        btnBackToDemoHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellersItemsActivity.this, AddItemActivity.class));
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(SellersItemsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}