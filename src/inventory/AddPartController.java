/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevingreen
 */
public class AddPartController implements Initializable {
    
    @FXML
    private TextField addPartsID;
    @FXML
    private TextField addPartsName;
    @FXML
    private TextField addPartsInv;
    @FXML
    private TextField addPartsPrice;
    @FXML
    private TextField addPartsMin;
    @FXML
    private TextField addPartsMax;
    @FXML
    private TextField addPartsCompanyName;
    @FXML
    private Label addPartCoMaName; 
    @FXML
    private RadioButton addInHouse;
    @FXML
    private RadioButton addOutsourced;
    
    private Boolean inHouse = true;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ToggleGroup group = new ToggleGroup();
        addInHouse.setToggleGroup(group);
        addOutsourced.setToggleGroup(group);
        addInHouse.setSelected(true);
        addPartsID.setText(Integer.toString(Inventory.getPartId()));
    }  
    
    @FXML
    public void addPartSave(ActionEvent event) throws IOException
    {   
        try {
            String name = addPartsName.getText();
            Integer partID = Integer.parseInt(addPartsID.getText());
            Double price = Double.parseDouble(addPartsPrice.getText());
            Integer inStock = Integer.parseInt(addPartsInv.getText());
            Integer min = Integer.parseInt(addPartsMin.getText());
            Integer max = Integer.parseInt(addPartsMax.getText());
            String companyName = addPartsCompanyName.getText();
            if (max <= min){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("Max needs to be greater than Min.");
                alert.showAndWait();
            } else if(inStock > max || inStock < min) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("");
                alert.setContentText("Inv needs to be less than Max and greater than Min.");
                alert.showAndWait();            
            }    
            else {
                if (getInhouse() == true){
                     InHouse part = new InHouse(name,partID,price,inStock,min,max,Integer.parseInt(companyName));
                     Inventory.addPart(part);
                } else {
                    Outsourced part = new Outsourced(name,partID,price,inStock,min,max,companyName);
                    Inventory.addPart(part); 
                }   
                Inventory.incrementPartId();
                goToMainScreen(event);
            }             
        } catch(NumberFormatException nfe) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("");
            alert.setContentText("Invalid data in text-fields!");
            alert.showAndWait();   
        }        
    }
    @FXML
    public void addPartCancel(ActionEvent event) throws IOException
    {
        goToMainScreen(event);
    }
    
    @FXML
    public void addInhouseAction(ActionEvent event) {
        addInHouse.setSelected(true);
        setInhouse(true);
        addPartCoMaName.setText("Machine ID");
    }
    @FXML
    public void addOutsourcedAction(ActionEvent event) {
        addOutsourced.setSelected(true);
        setInhouse(false);
        addPartCoMaName.setText("Company Name");
    }
    public void goToMainScreen(ActionEvent event) throws IOException{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();  
            window.setScene(tableViewScene);
            window.show();
    }
    
    public void setInhouse(Boolean bool){
        inHouse = bool;
    }
    public boolean getInhouse(){
        return inHouse;
    }    
}
