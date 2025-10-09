package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ImageProcessorView extends BorderPane {
    private ImageView imageView;
    private Button invertButton;
    private Button greyScaleButton;
    private IviewListener viewListener;

    public ImageProcessorView() {

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(imageView);

        invertButton = new Button("Invert");
        invertButton.setOnAction(e -> {
            if (viewListener != null) viewListener.onInvertSelected();
        });
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(invertButton);
        this.setCenter(centerBox);

        greyScaleButton = new Button("GreyScale");
        greyScaleButton.setOnAction(e -> {
            if (viewListener != null) viewListener.onGreyScaleSelected();
        });
        HBox buttonBox2 = new HBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().add(greyScaleButton);
        this.setCenter(centerBox);
        //this.setBottom(buttonBox2);
        this.setBottom(buttonBox);
    }

    public void displayImage(Image image) {
        imageView.setImage(image);
    }

    public void setViewListener(IviewListener viewListener) {
        this.viewListener = viewListener;
    }

    public Image getCurrentImage() {
        return imageView.getImage();
    }
}
