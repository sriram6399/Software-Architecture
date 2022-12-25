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

public class ItemsActivity extends AppCompatActivity {

    public static final String THIS_CONTEXT = "Items Activity";

    private Button btnBackToDemoHome, btnReviewCart;
    private RecyclerView recViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        btnBackToDemoHome = findViewById(R.id.btnBackToDemoHome);
        btnReviewCart = findViewById(R.id.btnReviewCart);
        recViewItems = findViewById(R.id.recViewItems);
        btnBackToDemoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnReviewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemsActivity.this, ReviewCartActivity.class));
            }
        });


        ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsBeingSold(), this, THIS_CONTEXT);
        recViewItems.setAdapter(itemsRecViewAdapter);
        recViewItems.setLayoutManager(new GridLayoutManager(this, 1));

        /* try {
            ItemsRecViewAdapter itemsRecViewAdapter = new ItemsRecViewAdapter(AppManager.getInstance().getItemsBeingSold(), this, THIS_CONTEXT);
            recViewItems.setAdapter(itemsRecViewAdapter);
            recViewItems.setLayoutManager(new GridLayoutManager(this, 1));
        }
        catch (Exception e) {
            Toast.makeText(ItemsActivity.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
        } */
    }
}