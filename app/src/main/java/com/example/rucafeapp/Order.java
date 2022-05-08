package com.example.rucafeapp;

import java.util.ArrayList;

/**
 * An instance of this class has a unique order number and keeps the list of menu items added by
 * the user. This class must implement the Customizable interface, to provide the behavior of adding and
 * removing menu items.
 *
 * @author Prince Rawal
 * @author Farah Lubaba Rouf
 */

public class Order implements Customizable{

    private int orderNum;
    private ArrayList<MenuItem> orders;

    /**
     * Constructor creates an instance of order
     */

    public Order() {
        this.orderNum = 1;
        orders = new ArrayList<MenuItem>();
    }

    /**
     * Sets number of order
     */

    public void setOrderNum(int orderNum){
        this.orderNum = orderNum;
    }


    /**
     * Gets the order number
     * @return  order number
     */

    public int getOrderNum() {
        return orderNum;
    }

    /**
     * Gets total price of all orders
     * @return  total price
     */

    public double getTotalPrice() {
        double totalPrice = 0;
        for(int i = 0; i < orders.size(); i++){
            totalPrice += orders.get(i).itemPrice();
        }
        return totalPrice;
    }

    /**
     * Adds an order object to cart
     * @param obj is the order item to be added
     * @return true if it added, false otherwise
     */

    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            orders.add(item);

        }
        else if(obj instanceof Order){
            Order newOrd = (Order) obj;
            for(int i = 0; i < newOrd.orders.size(); i++){
                orders.add(newOrd.orders.get(i));
            }
        }
        return false;
    }

    /**
     * Removes an order object to cart
     * @param obj is the order item to be removed
     * @return true if it removed, false otherwise
     */

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Integer) {
            int index = (Integer)obj;
            orders.remove(index);
            return true;
        }
        else if(obj instanceof MenuItem){
            MenuItem item = (MenuItem) obj;
            for(int i = 0; i < orders.size(); i++){
                if(orders.get(i).equals(item)){
                    orders.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Method to compare 2 order items
     *
     * @return true if they are the same, false otherwise
     */

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Coffee){
            Order order = (Order)obj;
            if(getOrderNum() == order.getOrderNum()){
                return true;
            }
        }
        return false;
    }

    /**
     *  Puts the orders as a string
     *
     * @return result arraylist containing orders as string
     */

    public ArrayList<String> toStringList(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < orders.size(); i++) {
            result.add("Item " + (i+1) + ": " + orders.get(i).toString() + " ");
        }
        return result;
    }

    public String toString(){
        String result;
        result = "Order #" + orderNum + "\n";
        ArrayList<String> getOrderPrint = toStringList();
        for (int i = 0; i < getOrderPrint.size(); i++){
            result = result + getOrderPrint.get(i) + "\n";
        }
        double tax = getTotalPrice() * 0.06625;
        double total = getTotalPrice() + tax;
        result = result +"Order Total = $" + String.format("%.2f",total) + " \nSubtotal = $" +
                String.format("%.2f",getTotalPrice()) + ", " +
                "\nTax = $" + String.format("%.2f",tax) ;
        return result;
    }

    /**
     *  Sets a new arraylist to put orders in
     */


    public void resetOrder(){
        orders = new ArrayList<MenuItem>();
    }

    /**
     *  Method to duplicate an order
     *
     * @return duplicate of the order
     */

    public Order copyOfOrder(){ //to reset the order in the GUI
        Order dup = new Order();
        dup.orders = orders;
        return dup;
    }

    /**
     *  Method to check if orders arraylist is empty
     *
     * @return true if yes, false otherwise
     */

    public boolean emptyOrder() {
        if (orders.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

}
