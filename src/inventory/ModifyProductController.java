/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevingreen
 */
public class ModifyProductController implements Initializable {
@FXML private TextField modifyProductID;
@FXML private TextField modifyProductName;
@FXML private TextField modifyProductInv;
@FXML private TextField modifyProductPrice;
@FXML private TextField modifyProductMin;
@FXML private TextField modifyProductMax;
@FXML private TextField modifyProductPartSearch;

@FXML private TableView modifyProductPartTable;
@FXML private TableView modifyProductIncludedPartTable;

@FXML private TableColumn modifyProductPartIDCol;
@FXML private TableColumn modifyProductPartNameCol;
@FXML private TableColumn modifyProductPartInvCol;
@FXML private TableColumn modifyProductPartPriceCol; 
@FXML private TableColumn modifyProductPartIDCol2;
@FXML private TableColumn modifyProductPartNameCol2;
@FXML private TableColumn modifyProductPartInvCol2;
@FXML private TableColumn modifyProductPartPriceCol2;
        
    private int index = MainController.getProductModifyIndex();
    MainController main = new MainController();
    
    private static int addPartIndex;
    private static Part addPart;
    private static ObservableList<Part> includedParts = FXCollections.observableArrayList();
    
    Product product = Inventory.getAllProducts().get(getIndex());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modifyProductID.setText(Integer.toString(product.getProductID()));
        modifyProductName.setText(product.getName());
        modifyProductInv.setText(Integer.toString(product.getInStock()));
        modifyProductPrice.setText(Double.toString(product.getPrice()));
        modifyProductMin.setText(Integer.toString(product.getMin()));
        modifyProductMax.setText(Integer.toString(product.getMax()));
        
        modifyProductPartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modifyProductPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("name"));
        modifyProductPartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modifyProductPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        modifyProductPartTable.setItems(Inventory.getAllParts());
        
        modifyProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modifyProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("name"));
        modifyProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modifyProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        modifyProductIncludedPartTable.setItems(product.getAssociatedParts());

    } 
    @FXML
    public void modifyProductSave(ActionEvent event) throws IOException
    {
        try {
            String name = modifyProductName.getText();
            Integer productID = Integer.parseInt(modifyProductID.getText());
            Double price = Double.parseDouble(modifyProductPrice.getText());
            Integer inStock = Integer.parseInt(modifyProductInv.getText());
            Integer min = Integer.parseInt(modifyProductMin.getText());
            Integer max = Integer.parseInt(modifyProductMax.getText());

            if (max <= min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("Max needs to be greater than Min.");
                alert.showAndWait();
            } else if(inStock > max || inStock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("Inv needs to be less than Max and greater than Min.");
                alert.showAndWait();            
            } else if (product.getAssociatedParts().size()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("You must include at least 1 part in the product.");
                alert.showAndWait();               
            }    
            else {                                       
                Inventory.updateProduct(getIndex(), product); 
                Inventory.incrementProductId();
                main.goToPage(event, "MainView.fxml");
            }             
        } catch(NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("");
            alert.setContentText("Invalid data in text-fields!");
            alert.showAndWait();   
        }                
    }
    @FXML
    public void modifyProductCancel(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    @FXML
    public void modifyProductDelete(ActionEvent event) throws IOException
    {   
        setAddPart((Part) modifyProductIncludedPartTable.getSelectionModel().getSelectedItem());
        setAddPartIndex(product.getAssociatedParts().indexOf(getAddPart()));
        Part part = product.getAssociatedParts().get(getAddPartIndex());
        getProduct().getAssociatedParts().remove(part);
        modifyProductIncludedPartTable.setItems(product.getAssociatedParts());
    }
    @FXML
    public void modifyProductAdd(ActionEvent event) throws IOException
    {
        setAddPart((Part) modifyProductPartTable.getSelectionModel().getSelectedItem());
        setAddPartIndex(Inventory.getAllParts().indexOf(getAddPart()));
        Part part = Inventory.getAllParts().get(getAddPartIndex());
        getProduct().getAssociatedParts().add(part);
        modifyProductIncludedPartTable.setItems(getProduct().getAssociatedParts());
    }   
    @FXML
    public int modfiyProductSearch(ActionEvent event) throws IOException
    {
        String searchProduct = modifyProductPartSearch.getText().toLowerCase();
        if (searchProduct.isEmpty()) {
            modifyProductPartTable.setItems(Inventory.getAllParts());
            return -1;
        }
        setIndex(-1);
        String test;
        ObservableList<Part> temp = FXCollections.observableArrayList();
        for(int i = 0; i < Inventory.getAllParts().size(); i++ ){
            test=Inventory.getAllParts().get(i).getName().toLowerCase();
            if(test.contains(searchProduct)){
                Part part = Inventory.getAllParts().get(i);
                temp.add(part);                
            }
        }
        modifyProductPartTable.setItems(temp);
        return 1;
    }

    public int getIndex() {
        return index;
    }

    public static int getAddPartIndex() {
        return addPartIndex;
    }

    public static Part getAddPart() {
        return addPart;
    }

    public static ObservableList<Part> getIncludedParts() {
        return includedParts;
    }

    public Product getProduct() {
        return product;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static void setAddPartIndex(int addPartIndex) {
        ModifyProductController.addPartIndex = addPartIndex;
    }

    public static void setAddPart(Part addPart) {
        ModifyProductController.addPart = addPart;
    }

    public static void setIncludedParts(ObservableList<Part> includedParts) {
        ModifyProductController.includedParts = includedParts;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
