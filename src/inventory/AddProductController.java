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
public class AddProductController implements Initializable {
    
    MainController main = new MainController();

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Part> addProductPartsTable;
    @FXML private TableColumn<Part, Integer> addProductPartIDCol;
    @FXML private TableColumn<Part, String> addProductPartNameCol;
    @FXML private TableColumn<Part, Integer> addProductPartInvCol;
    @FXML private TableColumn<Part, Integer> addProductPartPriceCol;            
    
    @FXML private TableView<Part> addProductIncludedPartTable;
    @FXML private TableColumn<Part, Integer> addProductPartIDCol2;
    @FXML private TableColumn<Part, String> addProductPartNameCol2;
    @FXML private TableColumn<Part, Integer> addProductPartInvCol2;
    @FXML private TableColumn<Part, Integer> addProductPartPriceCol2; 
    
    @FXML private TextField addProductPartSearch;
    @FXML private TextField addProductID;
    @FXML private TextField addProductName;
    @FXML private TextField addProductInv;
    @FXML private TextField addProductPrice;
    @FXML private TextField addProductMin;
    @FXML private TextField addProductMax;
    
    private static int addPartIndex;
    private static Part addPart;
    private static ObservableList<Part> includedParts = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addProductID.setText(Integer.toString(Inventory.getProductId()));
        
        addProductPartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        addProductPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProductPartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        addProductPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));        
        addProductPartsTable.setItems(Inventory.getAllParts());
        
        addProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        addProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        addProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));        
        addProductIncludedPartTable.setItems(getIncludedParts());        
    }  
    @FXML
     public void addProductSave(ActionEvent event) throws IOException
    {
        try {
            String name = addProductName.getText();
            Integer productID = Integer.parseInt(addProductID.getText());
            Double price = Double.parseDouble(addProductPrice.getText());
            Integer inStock = Integer.parseInt(addProductInv.getText());
            Integer min = Integer.parseInt(addProductMin.getText());
            Integer max = Integer.parseInt(addProductMax.getText());

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
            }  else if (getIncludedParts().size()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("You must include at least 1 part in the product.");
                alert.showAndWait(); 
            }
            else {
                Inventory.getAllProducts().add(new Product(name,productID,price,inStock,min,max, getIncludedParts())); 
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
    public void addProductCancel(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    @FXML
    public void addProductDelete(ActionEvent event) throws IOException
    {
        Part part = addProductIncludedPartTable.getSelectionModel().getSelectedItem();
        getIncludedParts().remove(part);
        addProductIncludedPartTable.setItems(getIncludedParts());
    }
    @FXML
    public void addProductAdd(ActionEvent event) throws IOException
    {
        setAddPart(addProductPartsTable.getSelectionModel().getSelectedItem());
        setAddPartIndex(Inventory.getAllParts().indexOf(getAddPart()));
        Part part = Inventory.getAllParts().get(getAddPartIndex());
        getIncludedParts().add(part);
        addProductIncludedPartTable.setItems(getIncludedParts());
    }    
    @FXML
    public int addProductSearch(ActionEvent event) throws IOException
    {
        String searchProduct = addProductPartSearch.getText().toLowerCase();
        if (searchProduct.isEmpty()) {
            addProductPartsTable.setItems(Inventory.getAllParts());
            return -1;
        }
        int index = -1;
        String test;
        ObservableList<Part> temp = FXCollections.observableArrayList();
        for(int i = 0; i < Inventory.getAllParts().size(); i++ ){
            test=Inventory.getAllParts().get(i).getName().toLowerCase();
            if(test.contains(searchProduct)){
                Part part = Inventory.getAllParts().get(i);
                temp.add(part);                
            }
        }
        addProductPartsTable.setItems(temp);
        return 1;
    }   
    
    public static void setAddPartIndex(int addPartIndex) {
        AddProductController.addPartIndex = addPartIndex;
    }

    public static void setAddPart(Part addPart) {
        AddProductController.addPart = addPart;
    }

    public static void setIncludedParts(ObservableList<Part> includedParts) {
        AddProductController.includedParts = includedParts;
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
}
