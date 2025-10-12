package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.FileChooser;

import java.io.File;

public class ImageProcessorView extends BorderPane {
    private ImageView imageView;
    private MenuBar menuBar;
    private Region histogramPlaceholder;
    private IviewListener viewListener;

    private FileChooser fileChooser;
    private Image image = null;

    public ImageProcessorView() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Image files", "*.png", ".jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(filter);

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
        MenuItem loadImageItem = new MenuItem("Open...");
        MenuItem saveImageItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem(("Exit"));
        exitItem.setOnAction(e -> {
            onMenubarExitSelected();
                });
        fileMenu.getItems().add(loadImageItem);
        fileMenu.getItems().add(saveImageItem);
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
        MenuItem sharpenItem = new MenuItem(("Sharpen"));
        sharpenItem.setOnAction(e -> {
            if (viewListener != null) viewListener.onSharpenSelected();
        });
        processMenu.getItems().add(greyScaleItem);
        processMenu.getItems().add(invertItem);
        processMenu.getItems().add(blurItem);
        processMenu.getItems().add(sharpenItem);
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

/*    protected void onOpensImageFile() {
        File imageFile = fileChooser.showOpenDialog(primaryStage);
        image = new Image(imageFile.toURI().toString());
        // ...
    }*/

    private void onMenubarExitSelected() {
        System.exit(0); // Check if user has saved file?
    }




}
