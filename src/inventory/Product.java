/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kevingreen
 */
public class Product {
    private ObservableList<Part> associatedParts;
    private String name;
    private int productID;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Product(String name, int productID, double price, int inStock, int min, int max, ObservableList<Part> included) {
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.associatedParts = included;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
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
    
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
    
}
