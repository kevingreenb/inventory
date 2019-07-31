/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author kevingreen
 */
public abstract class Part {
    private String name;
    private int partID;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Part(String name, int partID, double price, int inStock, int min, int max) {
        this.name = name;
        this.partID = partID;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public int getPartID() {
        return partID;
    }

    public double getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }   
}
