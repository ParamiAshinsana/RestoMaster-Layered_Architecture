package lk.ijse.restomaster.controler;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {


    public StackPane controllarea;
    public AnchorPane rootdashboard;
    public StackPane selectionpane;
    public JFXButton btnmanagestock;
    public JFXButton btnmanagesupplier;
    public JFXButton btnmanagemenuitem;
    public JFXButton btnmanageemployee;
    public JFXButton btncustomer;
    public JFXButton btnsuperviseitemprice;
    public JFXButton btngeneratereports;
    public JFXButton btndecideprofit;
    public Label lbldate;
    public Label lbltime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(String.valueOf(LocalDate.now()));
        controllarea.setVisible(false);
        TimeNow();
        //selectionpane.setVisible(false);
    }

    public void btnmanagecustomer(ActionEvent actionEvent) throws IOException {
           controllarea.setVisible(true);
           Parent fxml = FXMLLoader.load(getClass().getResource("/view/manage_customer_form.fxml"));
           controllarea.getChildren().removeAll();
           controllarea.getChildren().setAll(fxml);
           //selectionpane.setVisible(false);
    }

    public void btnonactionmanageorders(ActionEvent actionEvent) throws IOException {
//        controllarea.setVisible(true);
//        //selectionpane.setVisible(true);
//        Parent fxml = FXMLLoader.load(getClass().getResource("/view/selection_form.fxml"));
//        controllarea.getChildren().removeAll();
//        controllarea.getChildren().setAll(fxml);

        controllarea.setVisible(true);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/selection_form.fxml"));
        AnchorPane anchorPane = loader.load();
        OrdersSelectionFormController controller = loader.getController();
        controller.setControllArea(controllarea);
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(anchorPane);

    }

    public void logoutonmouseclicked(MouseEvent mouseEvent) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.show();
           rootdashboard.getScene().getWindow().hide();
    }

    public void btnmanagestockonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/manage_stocks_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btnmanagesupplieronaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/manage_suppliers_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btnmanagemenuitemonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/manage_menu_items_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btnmanageemployeeonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/manage_employees_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btnsuperviseitempriceonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/supervise_item_price_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btngeneratereportsonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/generate_reports_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    public void btndecideprofitonaction(ActionEvent actionEvent) throws IOException {
        controllarea.setVisible(true);
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/decide_profit_percentage_form.fxml"));
        controllarea.getChildren().removeAll();
        controllarea.getChildren().setAll(fxml);
        //selectionpane.setVisible(false);
    }

    private void TimeNow(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while (!false){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    lbltime.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
