package edu.umm.lab.model;

/**
 * Represents an item ordered in the restaurant.
 */
public class OrderItem {
    private final String name;
    private final int quantity;
    private final double unitPrice; // in IDR

    /**
     * Create a new order item.
     * @param name item name
     * @param quantity quantity ordered (must be >= 0)
     * @param unitPrice price per item in IDR (must be >= 0)
     */
    public OrderItem(String name, int quantity, double unitPrice) {
        if (quantity < 0) throw new IllegalArgumentException("quantity must be >= 0");
        if (unitPrice < 0) throw new IllegalArgumentException("unitPrice must be >= 0");
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /** @return item name */
    public String getName() { return name; }

    /** @return quantity */
    public int getQuantity() { return quantity; }

    /** @return unit price in IDR */
    public double getUnitPrice() { return unitPrice; }

    /** @return total price for this item (quantity * unitPrice) */
    public double getTotalPrice() { return quantity * unitPrice; }

    @Override
    public String toString() {
        return String.format("%s x%d @ %.2f => %.2f", name, quantity, unitPrice, getTotalPrice());
    }
}
