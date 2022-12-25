package com.example.comp_7700_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnBuyer, btnSeller;
    private TextView txtViewErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewErrorMessage = findViewById(R.id.txtViewErrorMessage);
        if (!AppManager.hasDatabaseBeenLoaded()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppManager.getInstance().initializeDatabase();
                        AppManager.getInstance().readAllItemsInDatabase();
                        txtViewErrorMessage.setVisibility(View.GONE);
                        // AppManager.getInstance().deleteItemFromDatabase();
                    }
                    catch (Exception e) {
                        txtViewErrorMessage.setVisibility(View.VISIBLE);
                    }
                }
            });
            thread.start();
            AppManager.invertDatabaseLoadedStatus();
        }
        btnBuyer = findViewById(R.id.btnBuyer);
        btnBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ItemsActivity.class));
            }
        });
        btnSeller = findViewById(R.id.btnSeller);
        btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SellersItemsActivity.class));
            }
        });
    }
}