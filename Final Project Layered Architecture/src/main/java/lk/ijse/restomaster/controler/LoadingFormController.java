package lk.ijse.restomaster.controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoadingFormController implements Initializable {
    public ImageView imageView;
    public AnchorPane LoadingForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setImage(new Image("/img/Logo1.png"));
        imageView.setCache(true);

        Timeline timeline = new Timeline();
        //KeyFrame keyFrame = new KeyFrame(Duration.millis(10000), actionEvent -> {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(800), actionEvent -> {
            System.out.println("Initializing...");
        });

        timeline.getKeyFrames().addAll(keyFrame);
        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            try{
                LoadingForm.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        });
    }
}
