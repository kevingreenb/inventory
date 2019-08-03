/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kevingreen
 */
public class MainController implements Initializable {
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> inventoryLevelPartColumn;
    @FXML private TableColumn<Part, Integer> pricePartColumn;

    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> inventoryLevelProductColumn;
    @FXML private TableColumn<Product, Integer> priceProductColumn;
    
    @FXML private TextField mainSearchProduct, mainSearchPart;
    
    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;
    
    
    @FXML 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if (Inventory.getPartId() == 0 && Inventory.getAllParts().size() == 0){
           Inventory.addPart(new InHouse("bolts",123,100.00,99,1,100,666));
           Inventory.addPart(new InHouse("nuts",124,200.00,99,1,100,666));
           Inventory.addPart(new InHouse("screws",125,300.00,99,1,100,666));
           Inventory.addPart(new InHouse("wires",126,100.00,99,1,100,666));
           Inventory.addPart(new InHouse("clips",127,200.00,99,1,100,666));
           ObservableList<Part> included = FXCollections.observableArrayList();
           ObservableList<Part> included2 = FXCollections.observableArrayList();
           ObservableList<Part> included3 = FXCollections.observableArrayList();
           ObservableList<Part> included4 = FXCollections.observableArrayList();
           included.add(new InHouse("bolts",123,100.00,99,1,100,666));
           included2.add(new InHouse("bolts",123,100.00,99,1,100,666));
           included3.add(new InHouse("bolts",123,100.00,99,1,100,666));
           included4.add(new InHouse("bolts",123,100.00,99,1,100,666));
           Inventory.addProduct(new Product("Radio", 123, 10000, 50, 0, 100,included));
           Inventory.addProduct(new Product("Computer", 124, 20000, 2, 0, 100, included2));
           Inventory.addProduct(new Product("Phone", 125, 3000, 6, 0, 100, included3));
           Inventory.addProduct(new Product("Server", 126, 10500, 8, 0, 100, included4));
        }
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        inventoryLevelPartColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        pricePartColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        partTableView.setItems(Inventory.getAllParts());
        
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        inventoryLevelProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        productTableView.setItems(Inventory.getAllProducts());        
    } 
    @FXML
    public void mainAddPart(ActionEvent event) throws IOException
    {
        this.goToPage(event, "AddPartView.fxml");
    }
    @FXML
    public void mainModifyPart(ActionEvent event) throws IOException
    {
        try {
            setModifyPart(partTableView.getSelectionModel().getSelectedItem());
            setModifyPartIndex(Inventory.getAllParts().indexOf(modifyPart));        
            this.goToPage(event, "ModifyPartView.fxml");
        } catch(Exception e){
            System.out.println("No modify part selected");
        }
    }
    @FXML
    public void mainDeletePart(ActionEvent event) throws IOException
    {
        try {
            Part part = partTableView.getSelectionModel().getSelectedItem();
            System.out.print(part.toString());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);

            alert.setContentText("Do you want to delete the highlighted part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Inventory.deletePart(part);
            }
        } catch(NullPointerException e) {
            System.out.println("No parts selected");
        }
    }
    @FXML
    public void mainAddProduct(ActionEvent event) throws IOException
    {
        this.goToPage(event, "AddProductView.fxml");
    }
    @FXML
    public void mainModifyProduct(ActionEvent event) throws IOException
    {
        try {
            setModifyProduct(productTableView.getSelectionModel().getSelectedItem());
            setModifyProductIndex(Inventory.getAllProducts().indexOf(getModifyProduct()));        
            this.goToPage(event, "ModifyProductView.fxml");
        } catch(Exception e){

        }
    }
    @FXML
    public void mainDeleteProduct(ActionEvent event) throws IOException
    {
         try {
            Product product = productTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);

            alert.setContentText("Do you want to delete the highlighted product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Inventory.deleteProduct(product);
            }
        } catch(NullPointerException e) {
            
        }      
    }
    @FXML
    public void mainExit(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);

        alert.setContentText("Do you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } 
    }
    @FXML
    public int mainSearchPart(ActionEvent event) throws IOException
    {
        String searchPart = mainSearchPart.getText().toLowerCase();
        if (searchPart.isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
            return -1;
        }
        int index = -1;
        String test;
        ObservableList<Part> temp = FXCollections.observableArrayList();
        for(int i = 0; i < Inventory.getAllParts().size(); i++ ){
            test=Inventory.getAllParts().get(i).getName().toLowerCase();
            if(test.contains(searchPart)){
                Part part = Inventory.getAllParts().get(i);
                temp.add(part);                
            }
        }
        partTableView.setItems(temp);
        return 1;
    }
    @FXML
    public int mainSearchProduct(ActionEvent event) throws IOException
    {
        String searchProduct = mainSearchProduct.getText().toLowerCase();
        if (searchProduct.isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
            return -1;
        }
        int index = -1;
        String test;
        ObservableList<Product> temp = FXCollections.observableArrayList();
        for(int i = 0; i < Inventory.getAllProducts().size(); i++ ){
            test=Inventory.getAllProducts().get(i).getName().toLowerCase();
            if(test.contains(searchProduct)){
                Product product = Inventory.getAllProducts().get(i);
                temp.add(product);                
            }
        }
        productTableView.setItems(temp);
        return 1;
    }   
    
    public static int getPartModifyIndex(){
        return modifyPartIndex;
    }
    public static int getProductModifyIndex(){
        return modifyProductIndex;
    }   
    
    public void goToPage(ActionEvent event, String page) throws IOException{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource(page));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();        
            window.setScene(tableViewScene);
            window.show();   
    }

    public static void setModifyPart(Part modifyPart) {
        MainController.modifyPart = modifyPart;
    }

    public static void setModifyPartIndex(int modifyPartIndex) {
        MainController.modifyPartIndex = modifyPartIndex;
    }

    public static void setModifyProduct(Product modifyProduct) {
        MainController.modifyProduct = modifyProduct;
    }

    public static void setModifyProductIndex(int modifyProductIndex) {
        MainController.modifyProductIndex = modifyProductIndex;
    }

    public static Part getModifyPart() {
        return modifyPart;
    }

    public static int getModifyPartIndex() {
        return modifyPartIndex;
    }

    public static Product getModifyProduct() {
        return modifyProduct;
    }
    
}
