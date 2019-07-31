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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void addPartSave(ActionEvent event) throws IOException
    {
        String name = addPartsName.getText();
        String partID = addPartsName.getText();
        String price = addPartsPrice.getText();
        String inStock = addPartsInv.getText();
        String min = addPartsMin.getText();
        String max = addPartsMax.getText();
        String companyName = addPartsCompanyName.getText();
        
        InHouse part = new InHouse(name, Integer.parseInt(partID),Double.parseDouble(price),Integer.parseInt(inStock),Integer.parseInt(min),Integer.parseInt(max),Integer.parseInt(companyName));
        Inventory.addPart(part);
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();  
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void addPartCancel(ActionEvent event) throws IOException
    {
        
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
    }
    
}
