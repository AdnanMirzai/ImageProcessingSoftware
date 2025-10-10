package se.kth.adnmax.lab4.imageprocessingsoftware;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.adnmax.lab4.imageprocessingsoftware.controller.ImageProcessorController;
import se.kth.adnmax.lab4.imageprocessingsoftware.model.ImageProcessorFacade;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.ImageProcessorView;


import java.io.IOException;

/**
 * App launcher, we create our model, view and controller.
 * And connect them, controller does rest of the communication.
 */

public class ImageProcessorApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ImageProcessorFacade model = new ImageProcessorFacade();
        ImageProcessorView view = new ImageProcessorView();
        ImageProcessorController controller = new ImageProcessorController(view, model);

        //loading manually for now, will implement file chooser later
        Image originalImage = new Image(this.getClass().getResource("devil.png").toString());
        controller.setInitialImage(originalImage); //let controller talk to view!

        Scene scene = new Scene(view, 900, 600);
        stage.setTitle("Image Processor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
