package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Activity class for Main Activity layout
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class MainActivity extends AppCompatActivity {

    protected static Order order;

    protected static StoreOrders storeOrders;

    private ImageButton bttn_donut, bttn_coffee, bttn_yourOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        order = new Order();
        storeOrders = new StoreOrders();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launches order donuts menu
     *
     * @param view the current view
     */

    public void openDonutOrder(View view) {
        Intent intent = new Intent(MainActivity.this, OrderDonuts.class);
        startActivity(intent);
    }

    /**
     * Launches order coffee menu
     *
     * @param view the current view
     */

    public void openCoffeeOrder(View view) {
        Intent intent = new Intent(MainActivity.this, OrderCoffee.class);
        startActivity(intent);
    }

    /**
     * Launches your orders menu
     *
     * @param view the current view
     */

    public void openYourOrder(View view) {
        Intent intent = new Intent(MainActivity.this, YourOrder.class);
        startActivity(intent);
    }

    /**
     * Launches store orders menu
     *
     * @param view the current view
     */

    public void openStoreOrder(View view) {
        Intent intent = new Intent(MainActivity.this, StoreOrderController.class);
        startActivity(intent);
    }
}