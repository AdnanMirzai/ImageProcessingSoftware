package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ImageProcessorView extends BorderPane {
    private ImageView imageView;
    private MenuBar menuBar;
    private Region histogramPlaceholder;
    private IviewListener viewListener;

    public ImageProcessorView() {

        // Meny
        createMenuBar();
        this.setTop(menuBar);

        // Bildvy
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(5));

        // Histogram (Än så länge placeholder)
        histogramPlaceholder = new Region();
        histogramPlaceholder.setPrefSize(300, 500);
        histogramPlaceholder.setStyle("-fx-background: lightgray; -fx-border-color: gray;");

        // Ruta för allt innehåll i mitten. Två rutor brevid varandra.
        HBox centerBox = new HBox(10);
        centerBox.setPadding(new Insets(10));
        centerBox.getChildren().addAll(histogramPlaceholder, imageView);

        // Lägger till i BorderPane
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
        MenuItem blurItem = new MenuItem(("Blur"));
        blurItem.setOnAction(e -> {
            if (viewListener != null) viewListener.onBlurSelected();
        });
        processMenu.getItems().add(greyScaleItem);
        processMenu.getItems().add(invertItem);
        processMenu.getItems().add(blurItem);
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
