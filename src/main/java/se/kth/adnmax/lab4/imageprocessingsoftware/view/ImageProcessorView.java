package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;

public class ImageProcessorView extends BorderPane {
    private HistogramView histogramView;
    private MenuBar menuBar;
    //private Region histogramView;
    private ImageView imageView;
    private VBox analysisBox;
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
        imageBox.setAlignment(Pos.TOP_LEFT);
        imageBox.setPadding(new Insets(5));
        VBox.setVgrow(imageView, Priority.NEVER);
        imageView.fitWidthProperty().bind(imageBox.widthProperty());

        // Histogram (Än så länge placeholder)
/*        histogramPlaceholder = new Region();
        histogramPlaceholder.setPrefSize(300, 500);
        histogramPlaceholder.setStyle("-fx-background: lightgray; -fx-border-color: gray;");*/

        // Ruta för allt till vänster med histogram och sliders
        histogramView = new HistogramView();
        histogramView.setPrefHeight(400);
        Slider windowSlider = new Slider(0, 255, 10);
        Slider levelSlider = new Slider(0, 255, 10);
        windowSlider.setShowTickMarks(true);
        windowSlider.setShowTickLabels(true);

        HBox sliderBox = new HBox(5, windowSlider, levelSlider);
        sliderBox.setPadding(new Insets(10));
        HBox.setHgrow(windowSlider, Priority.ALWAYS);
        HBox.setHgrow(levelSlider, Priority.ALWAYS);
        windowSlider.setMaxWidth(Double.MAX_VALUE);
        levelSlider.setMaxWidth(Double.MAX_VALUE);

        VBox analysisBox = new VBox(10, histogramView, sliderBox);
        analysisBox.setPadding(new Insets(10));
        analysisBox.setPrefSize(370, 500);
        analysisBox.setMinSize(370, 500);
        analysisBox.setMaxSize(370, 500);
        // Ruta för allt innehåll i mitten. Två rutor brevid varandra.
        HBox centerBox = new HBox(10);
        centerBox.setPadding(new Insets(10));
        centerBox.getChildren().addAll(imageBox);


        // Lägger till i BorderPane
        this.setLeft(analysisBox);
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
