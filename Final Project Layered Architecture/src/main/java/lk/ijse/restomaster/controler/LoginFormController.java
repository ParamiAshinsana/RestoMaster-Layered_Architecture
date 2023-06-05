package lk.ijse.restomaster.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXButton btnlogin;
    public AnchorPane root;
    public JFXTextField txtusername;
    public JFXTextField txtpassword;
    public PasswordField fieldPassword;
    public Label lblError;

    public void btnloginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtusername.getText().equalsIgnoreCase("admin01") & fieldPassword.getText().equalsIgnoreCase("1234")){
            Parent root1 = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
            Scene scene= new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            root.getScene().getWindow().hide();
        }else{
            txtusername.setStyle("-fx-background-color: red");
            fieldPassword.setStyle("-fx-background-color: red");
            txtusername.setText("");
            fieldPassword.setText("");
            lblError.setText("Incorrect Login !");
        }

    }
}
