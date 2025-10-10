package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ImageProcessorView extends BorderPane {
    private ImageView imageView;
    private Button invertButton;
    private Button greyScaleButton;
    private MenuBar menuBar;
    private IviewListener viewListener;

    public ImageProcessorView() {

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        createMenuBar();

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(imageView);
        this.setCenter(centerBox);

    }

    private void createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem(("Exit"));
        exitItem.setOnAction(e -> {
            if(viewListener != null) viewListener.onMenubarExitSelected();
        });
        fileMenu.getItems().add(exitItem);

        Menu processMenu = new Menu("Process");
        MenuItem greyScaleItem = new MenuItem(("Grey Scale"));
        greyScaleItem.setOnAction(e -> {
            if (viewListener != null) viewListener.onGreyScaleSelected();
        });
        MenuItem invertItem = new MenuItem(("Invert colors"));
        invertItem.setOnAction(e -> {
            if (viewListener != null) viewListener.onInvertSelected();
        });
        processMenu.getItems().add(greyScaleItem);
        processMenu.getItems().add(invertItem);
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, processMenu);
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

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
