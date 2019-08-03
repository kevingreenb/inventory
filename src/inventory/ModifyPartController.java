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
public class ModifyPartController implements Initializable {
    @FXML
    private TextField modifyPartsID;
    @FXML
    private TextField modifyPartsName;
    @FXML
    private TextField modifyPartsInv;
    @FXML
    private TextField modifyPartsPrice;
    @FXML
    private TextField modifyPartsMin;
    @FXML
    private TextField modifyPartsMax;
    @FXML
    private TextField modifyPartsCompanyName;
    @FXML
    private Label modifyPartCoMaName; 
    @FXML
    private RadioButton modifyInhouse;
    @FXML
    private RadioButton modifyOutsourced;
    
    private int index = MainController.getPartModifyIndex();
    private Boolean inHouse = false;
    MainController main = new MainController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ToggleGroup group = new ToggleGroup();
        modifyInhouse.setToggleGroup(group);
        modifyOutsourced.setToggleGroup(group);
        modifyInhouse.setSelected(true);
        Part part = Inventory.getAllParts().get(getIndex());
        modifyPartsID.setText(Integer.toString(part.getPartID()));
        modifyPartsName.setText(part.getName());
        modifyPartsInv.setText(Integer.toString(part.getInStock()));
        modifyPartsPrice.setText(Double.toString(part.getPrice()));
        modifyPartsMin.setText(Integer.toString(part.getMin()));
        modifyPartsMax.setText(Integer.toString(part.getMax()));
        if (part instanceof InHouse){
            modifyPartsCompanyName.setText(Integer.toString(((InHouse) Inventory.getAllParts().get(getIndex())).getMachineID()));
            modifyInhouse.setSelected(true);
            setInHouse(true);
        } else {
            modifyPartsCompanyName.setText(((Outsourced) Inventory.getAllParts().get(getIndex())).getCompanyName());
            modifyOutsourced.setSelected(true);
        }
        
    }   
    @FXML
    public void modifyPartSave(ActionEvent event) throws IOException
    {
        try {
            String name = modifyPartsName.getText();
            Integer partID = Integer.parseInt(modifyPartsID.getText());
            Double price = Double.parseDouble(modifyPartsPrice.getText());
            Integer inStock = Integer.parseInt(modifyPartsInv.getText());
            Integer min = Integer.parseInt(modifyPartsMin.getText());
            Integer max = Integer.parseInt(modifyPartsMax.getText());
            String companyName = modifyPartsCompanyName.getText();
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
            }   
            else {
                    if (getInHouse() == true){
                       InHouse part = new InHouse(name,partID,price,inStock,min,max,Integer.parseInt(companyName));
                       Inventory.updatePart(getIndex(), part);
                    }
                    else {
                       Outsourced part = new Outsourced(name,partID,price,inStock,min,max,companyName);
                       Inventory.updatePart(getIndex(), part);
                    }               
            }
            main.goToPage(event, "MainView.fxml");
        } catch(NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("");
            alert.setContentText("Invalid data in text-fields!");
            alert.showAndWait();   
        } 
    }
    @FXML
    public void modifyPartCancel(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    
    @FXML
    public void modifyInhouseAction(){
        setInHouse(true);
    }
    @FXML
    public void modifyOutsorcedAction(){
        setInHouse(false);
    }

    public int getIndex() {
        return index;
    }

    public Boolean getInHouse() {
        return inHouse;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setInHouse(Boolean inHouse) {
        this.inHouse = inHouse;
    }
    
}
