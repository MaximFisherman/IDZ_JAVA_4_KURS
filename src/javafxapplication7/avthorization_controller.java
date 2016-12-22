/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Maks_
 */
public class avthorization_controller {
    @FXML
    public Button authorization_button;
    @FXML
    public TextField login_field;
    @FXML
    public PasswordField pass_field;
    
    @FXML
    public Label warning;
     
    @FXML
    private void initialize() {
        
    authorization_button.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        if(login_field.getText().equals("Max")&&pass_field.getText().equals("123")){ 
            System.out.println(login_field.getText() +" "+ pass_field.getText());
        try {     
            //Закрытие окна 
            Stage stage = (Stage) authorization_button.getScene().getWindow();
            stage.close();
            
            //Открытие окна Main.fxml
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);   
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(avthorization_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   else 
        {
            warning.setText("Your password or login incorect");
        }
    }
});
    }
}
