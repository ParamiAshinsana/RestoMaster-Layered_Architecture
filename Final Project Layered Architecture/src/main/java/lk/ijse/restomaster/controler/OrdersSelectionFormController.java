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
    }

    public void traditionalselectionclicked(MouseEvent mouseEvent) throws IOException {
    }

    public void btnselectionbackonaction(ActionEvent actionEvent) throws IOException {
        st.setVisible(true);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/food_selection_form.fxml"));
        AnchorPane anchorPane = loader.load();
        ManageOrderFormController controller = loader.getController();
        controller.setControllArea(st);
        st.getChildren().removeAll();
        st.getChildren().setAll(anchorPane);
    }

    public void tfselectiononaction(MouseEvent mouseEvent) throws IOException {
    }

    public void sfselectiononclicked(MouseEvent mouseEvent) throws IOException {
    }

    public void beverageselectiononclicked(MouseEvent mouseEvent) throws IOException {
    }

    public void setControllArea(StackPane controllarea) {
        this.st = controllarea ;
    }
}

