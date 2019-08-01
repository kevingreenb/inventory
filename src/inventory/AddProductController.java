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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
     public void addProductSave(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    @FXML
    public void addProductCancel(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    @FXML
    public void addProductDelete(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }
    @FXML
    public void addProductAdd(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    }    
    @FXML
    public void addProductSearch(ActionEvent event) throws IOException
    {
        main.goToPage(event, "MainView.fxml");
    } 
}
