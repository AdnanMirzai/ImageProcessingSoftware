package se.kth.adnmax.lab4.imageprocessingsoftware;

import javafx.application.Application;
import javafx.scene.Scene;
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
        ImageProcessorController controller = new ImageProcessorController(stage, view, model);
        Scene scene = new Scene(view, 1050, 560);
        stage.setTitle("Image Processor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
