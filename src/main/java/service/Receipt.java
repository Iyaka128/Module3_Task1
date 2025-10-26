package edu.umm.lab.service;

import edu.umm.lab.model.OrderItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Receipt service that aggregates order items and calculates totals,
 * supports tax and discount.
 */
public class Receipt {

    private final List<OrderItem> items = new ArrayList<>();
    private double taxPercent = 0.0;
    private double discountPercent = 0.0;

    /** Add an order item to this receipt. */
    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        items.add(item);
    }

    /** Set tax percent (e.g. 10.0 for 10%). */
    public void setTaxPercent(double taxPercent) {
        if (taxPercent < 0) throw new IllegalArgumentException("taxPercent must be >= 0");
        this.taxPercent = taxPercent;
    }

    /** Set discount percent (e.g. 5.0 for 5%). */
    public void setDiscountPercent(double discountPercent) {
        if (discountPercent < 0) throw new IllegalArgumentException("discountPercent must be >= 0");
        this.discountPercent = discountPercent;
    }

    /** @return unmodifiable list of items */
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /** Calculate subtotal (sum of all item totals). */
    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (OrderItem it : items) {
            subtotal += it.getTotalPrice();
        }
        return subtotal;
    }

    /**
     * Apply discount to a given amount.
     * @param amount amount before discount
     * @return amount after discount applied
     */
    public double applyDiscount(double amount) {
        return amount * (1.0 - discountPercent / 100.0);
    }

    /**
     * Apply tax on given amount (tax percent applied on top of amount).
     * @param amount base amount
     * @return amount after tax
     */
    public double applyTax(double amount) {
        return amount * (1.0 + taxPercent / 100.0);
    }

    /**
     * Calculate grand total: subtotal -> discount -> tax.
     * @return grand total amount
     */
    public double calculateGrandTotal() {
        double subtotal = calculateSubtotal();
        double afterDiscount = applyDiscount(subtotal);
        double afterTax = applyTax(afterDiscount);
        return afterTax;
    }

    /** Print formatted receipt to console. */
    public String formatReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RESTAURANT RECEIPT =====\n");
        for (OrderItem it : items) {
            sb.append(String.format("%-20s %3dx  %10.2f\n", it.getName(), it.getQuantity(), it.getTotalPrice()));
        }
        sb.append("-------------------------------\n");
        double subtotal = calculateSubtotal();
        sb.append(String.format("Subtotal: %28.2f\n", subtotal));
        if (discountPercent > 0) {
            sb.append(String.format("Discount (%.2f%%): %21.2f\n", discountPercent, subtotal - applyDiscount(subtotal)));
        }
        double afterDiscount = applyDiscount(subtotal);
        if (taxPercent > 0) {
            sb.append(String.format("Tax (%.2f%%): %30.2f\n", taxPercent, applyTax(afterDiscount) - afterDiscount));
        }
        sb.append(String.format("GRAND TOTAL: %25.2f\n", calculateGrandTotal()));
        sb.append("===============================\n");
        return sb.toString();
    }
}
