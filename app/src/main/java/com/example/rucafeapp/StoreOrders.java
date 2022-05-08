package com.example.rucafeapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An instance of this class keeps a list of orders placed by the user. This class must
 * implement the Customizable interface above to provide the behavior of adding and removing orders.
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class StoreOrders implements Customizable {

    private ArrayList<Order> storeOrders;

    /**
     * Constructor creates an instance of store orders
     */

    public StoreOrders() {
        storeOrders = new ArrayList<Order>();
    }

    /**
     * Adds an order to store orders list
     *
     * @param obj is an instance of Order object
     * @return true if order is added, false otherwise
     */

    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            if (storeOrders.size() > 0) {
                order.setOrderNum(storeOrders.get(storeOrders.size() - 1).getOrderNum() + 1);
                //new order number is 1 + last Order Number
            }
            storeOrders.add(order);
            return true;
        }
        return false;
    }

    /**
     * removes an order from store orders
     *
     * @param obj is the order index
     */

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Integer) {
            int index = (Integer) obj;
            storeOrders.remove(index);
        }
        return false;
    }

    /**
     * Converts the list of orders to a string list format
     */

    public ArrayList<String> toStringList() {
        ArrayList<String> result = new ArrayList<>();

        if (storeOrders.size() == 0) {
            return result;
        }

        for (int i = 0; i < storeOrders.size(); i++) {
            result.add(storeOrders.get(i).toString());

        }
        return result;
    }

    /**
     * Puts the arraylist in String format for exporting orders
     *
     * @param result is the arraylist to be converted
     * @return a string containing list of orders
     */

    public String stringForExportingOrders(ArrayList<String> result) {
        String orderList = " ";
        int j = 2;
        orderList += "Order number 1:" + result.get(0);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i).equals(" ")) {
                orderList += "\n" + "Order number " + j;
                j++;
            }
            orderList += result.get(i);
        }
        return orderList;
    }

    /**
     * gets the orders from list of orders
     *
     * @param orderNum is the number of order you want to get
     * @return Order from the arraylist if it is found, empty arraylist if not
     */

    public ArrayList<String> getOrder(int orderNum) {
        for (int i = 0; i < storeOrders.size(); i++) {
            if (storeOrders.get(i).getOrderNum() == orderNum) {
                return storeOrders.get(i).toStringList();
            }
        }
        return new ArrayList<String>(); //returning an empty list
    }

    /**
     * Returns the order number
     *
     * @return arraylist containing the order numbers
     */

    public ArrayList<Integer> getOrderNumber() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < storeOrders.size(); i++) {
            result.add(storeOrders.get(i).getOrderNum());
        }
        return result;
    }

    /**
     * Gets total price of orders in the arraylist
     *
     * @param orderNum is the number of the order
     * @return total price of the orders
     */

    public double getCost(int orderNum) {
        for (int i = 0; i < storeOrders.size(); i++) {
            if (storeOrders.get(i).getOrderNum() == orderNum) {
                return storeOrders.get(i).getTotalPrice();
            }
        }
        return 0;
    }

    /**
     * Returns the size of the store orders arraylist
     *
     * @return size of arraylist
     */

    public int getSize() {
        return storeOrders.size();
    }

}
