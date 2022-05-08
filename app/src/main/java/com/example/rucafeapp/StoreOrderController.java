package com.example.rucafeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity class for Store Orders layout
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class StoreOrderController extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView storeOrdersList;

    private Order order = MainActivity.order;

    private StoreOrders storeOrders = MainActivity.storeOrders;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);

        //orderList.setOnClickListener(this);
        storeOrdersList=findViewById(R.id.lst_storeOrders);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                storeOrders.toStringList() );
        storeOrdersList.setAdapter(adapter);
        storeOrdersList.setOnItemClickListener(this);
    }

    /**
     * This method opens a new alert once an order is clicked, showing options
     * to remove the order
     */

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cancel Order?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                storeOrders.remove(i);

                adapter.clear();
                adapter.addAll(storeOrders.toStringList());
                Toast.makeText(getApplicationContext(), "Order Removed", Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Order will not be removed",
                        Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}