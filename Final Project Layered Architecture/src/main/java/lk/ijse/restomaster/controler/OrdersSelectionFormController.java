package lk.ijse.restomaster.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdersSelectionFormController implements Initializable {

    public AnchorPane rootselection;
    public Button btnselectionback;
    public Label lblId;
    public StackPane st;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        lblId.setText("The central feature of Sri Lankan cuisine is boiled or steamed rice,\n" +
//                " served with a curry of fish or meat, along with other curries made\n" +
//                " with vegetables, lentils, or fruits. Dishes are accompanied \n" +
//                "by pickled fruits or vegetables, chutneys, and sambols.");
    }

    public void traditionalselectionclicked(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/manage_orders_form.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//        rootselection.getScene().getWindow().hide();
    }

    public void btnselectionbackonaction(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//        rootselection.getScene().getWindow().hide();

//        st.setVisible(true);
//        Parent fxml = FXMLLoader.load(getClass().getResource("/view/food_selection_form.fxml"));
//        st.getChildren().removeAll();
//        st.getChildren().setAll(fxml);

        st.setVisible(true);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/food_selection_form.fxml"));
        AnchorPane anchorPane = loader.load();
        ManageOrderFormController controller = loader.getController();
        controller.setControllArea(st);
        st.getChildren().removeAll();
        st.getChildren().setAll(anchorPane);


    }

    public void tfselectiononaction(MouseEvent mouseEvent) throws IOException {
        //................................................
//        Parent root = FXMLLoader.load(getClass().getResource("/view/traditionalfood_form.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
        //.................................
        //rootselection.getScene().getWindow().hide();

//        DashboardFormController.controllarea.setVisible(true);
//        //selectionpane.setVisible(true);
//        Parent fxml = FXMLLoader.load(getClass().getResource("/view/selection_form.fxml"));
//        DashboardFormController.controllarea.getChildren().removeAll();
//        DashboardFormController.controllarea.getChildren().setAll(fxml);

//        st.setVisible(true);
//        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/traditionalfood_form.fxml"));
//        AnchorPane anchorPane = loader.load();
//        SelectionFormController controller = loader.getController();
//        controller.setControllArea(st);
//        st.getChildren().removeAll();
//        st.getChildren().setAll(anchorPane);
    }

    public void sfselectiononclicked(MouseEvent mouseEvent) throws IOException {
        //............................................
//        Parent root = FXMLLoader.load(getClass().getResource("/view/seafood_form.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
        //....................................
        //rootselection.getScene().getWindow().hide();
    }

    public void beverageselectiononclicked(MouseEvent mouseEvent) throws IOException {
        //....................................
//        Parent root = FXMLLoader.load(getClass().getResource("/view/beverage_form.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
        //....................................
        //rootselection.getScene().getWindow().hide();
    }

    public void setControllArea(StackPane controllarea) {
        this.st = controllarea ;

    }
}

