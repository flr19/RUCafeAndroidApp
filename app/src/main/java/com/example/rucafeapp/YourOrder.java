package com.example.rucafeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity class for Your Orders layout
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class YourOrder extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView orderList;

    private Order order = MainActivity.order;

    private StoreOrders storeOrders = MainActivity.storeOrders;

    private ArrayAdapter<String> adapter;

    private EditText subtotal,salesTax,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        orderList = findViewById(R.id.list_of_orders);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, order.toStringList() );
        //orderList.setOnClickListener(this);
        orderList.setAdapter(adapter);

        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);

        subtotal.setText(String.format("%.2f",order.getTotalPrice()));
        salesTax.setText(String.format("%.2f",order.getTotalPrice() * 0.06625));
        total.setText(String.format("%.2f",order.getTotalPrice() +order.getTotalPrice() * 0.06625));

        orderList.setOnItemClickListener(this);

        subtotal.setEnabled(false);
        salesTax.setEnabled(false);
        total.setEnabled(false);

    }

    /**
     * This method opens a new alert once an order is clicked, showing options
     * to remove items from the order
     */

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Order?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String item = (String) orderList.getItemAtPosition(i);
                int currIndex = 5;
                while (item.charAt(currIndex) != ':') {
                    currIndex++;
                }
                order.remove(Integer.parseInt(item.substring(5, currIndex)) - 1);
                adapter.clear();
                adapter.addAll(order.toStringList());
                subtotal.setText(String.format("%.2f",order.getTotalPrice()));
                salesTax.setText(String.format("%.2f",order.getTotalPrice() * 0.06625));
                total.setText(String.format("%.2f",order.getTotalPrice() +order.getTotalPrice() * 0.06625));
                Toast.makeText(getApplicationContext(), "Order Removed", Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Order will not be removed", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Method to place the order and add it to store orders list
     */

    public void placeOrder (View view) {
        if(order.toStringList().isEmpty()) {
            Context context = getApplicationContext();
            CharSequence text = "Please add items to your basket!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast= Toast.makeText(context,text,duration);
            toast.show();
            return;
        }
        storeOrders.add(order.copyOfOrder());
        order.resetOrder();
        adapter.clear();

        subtotal.setText(String.format("%.2f",order.getTotalPrice()));
        salesTax.setText(String.format("%.2f",order.getTotalPrice() * 0.06625));
        total.setText(String.format("%.2f",order.getTotalPrice() +order.getTotalPrice() * 0.06625));

        Context context = getApplicationContext();
        CharSequence text = "Order placed!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast= Toast.makeText(context,text,duration);
        toast.show();


    }
}