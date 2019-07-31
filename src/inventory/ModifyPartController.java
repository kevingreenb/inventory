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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    public void modifyPartSave(ActionEvent event) throws IOException
    {
        String name = modifyPartsName.getText();
        String partID = modifyPartsID.getText();
        String price = modifyPartsPrice.getText();
        String inStock = modifyPartsInv.getText();
        String min = modifyPartsMin.getText();
        String max = modifyPartsMax.getText();
        String companyName = modifyPartsCompanyName.getText();
        
        InHouse part = new InHouse(name, Integer.parseInt(partID),Double.parseDouble(price),Integer.parseInt(inStock),Integer.parseInt(min),Integer.parseInt(max),Integer.parseInt(companyName));
        Inventory.addPart(part);
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void modifyPartCancel(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
}
