/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kevingreen
 */
public class Inventory extends Application {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partId = 0;
    private static int productId = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product Product){
        allProducts.add(Product);
    }    
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }  
    public static void updateProduct(int index, Product Product){
        allProducts.set(index, Product);
    }      
    public static void deletePart(Part part) {
        allParts.remove(part);
    }  
    public static void deleteProduct(Product Product) {
        allProducts.remove(Product);
    } 
    public static void incrementPartId(){
        partId++;
    }
    public static void incrementProductId(){
        productId++;
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    public static int getPartId(){
        return partId;
    }

    public static int getProductId(){
        return productId;
    }  
    
    public static void setAllParts(ObservableList<Part> allParts) {
        Inventory.allParts = allParts;
    }

    public static void setAllProducts(ObservableList<Product> allProducts) {
        Inventory.allProducts = allProducts;
    }

    public static void setPartId(int partId) {
        Inventory.partId = partId;
    }

    public static void setProductId(int productId) {
        Inventory.productId = productId;
    }    
}
