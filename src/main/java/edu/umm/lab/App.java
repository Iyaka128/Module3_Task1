package edu.umm.lab;

import edu.umm.lab.model.OrderItem;
import edu.umm.lab.service.Receipt;

/**
 * Simple demo application for food order receipt (Task 1).
 * Demonstrates:
 * - creating OrderItem objects
 * - adding items to Receipt
 * - setting tax & discount
 * - printing formatted receipt
 */
public class App {
    public static void main(String[] args) {
        Receipt receipt = new Receipt();

        // create some sample items
        OrderItem nasiGoreng = new OrderItem("Nasi Goreng", 2, 20000.0);
        OrderItem tehTawar = new OrderItem("Teh Tawar", 3, 3000.0);
        OrderItem ayamGoreng = new OrderItem("Ayam Goreng", 1, 25000.0);

        // add items
        receipt.addItem(nasiGoreng);
        receipt.addItem(tehTawar);
        receipt.addItem(ayamGoreng);

        // set discount and tax for demo
        receipt.setDiscountPercent(5.0); // 5% discount
        receipt.setTaxPercent(10.0);     // 10% tax

        // print receipt
        System.out.println(receipt.formatReceipt());
    }
}
