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

        // Bildvy (Imagebox)
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.TOP_LEFT);
        imageBox.setPadding(new Insets(5));
        VBox.setVgrow(imageView, Priority.NEVER);
        imageView.fitWidthProperty().bind(imageBox.widthProperty());

        // Grafvy (AnalysisBox)
        histogramView = new HistogramView();
        histogramView.setPrefHeight(400);
        Slider windowSlider = new Slider(0, 255, 0);
        Slider levelSlider = new Slider(0, 255, 0);
        Label windowLabel = new Label("Window");
        Label levelLabel = new Label("Level");
        VBox windowPane = new VBox(5, windowSlider, windowLabel);
        VBox levelPane  = new VBox(5, levelSlider, levelLabel);
        windowSlider.setShowTickMarks(true);
        windowSlider.setShowTickLabels(true);
        levelSlider.setShowTickMarks(true);
        levelSlider.setShowTickLabels(true);
        HBox sliderBox = new HBox(10, windowPane, levelPane);
        sliderBox.setPadding(new Insets(10));
        windowSlider.setPrefWidth(200);
        levelSlider.setPrefWidth(200);
        VBox analysisBox = new VBox(10, histogramView, sliderBox);
        analysisBox.setPadding(new Insets(10));
        analysisBox.setPrefSize(370, 500);
        analysisBox.setMinSize(370, 500);
        analysisBox.setMaxSize(370, 500);

        // Lägger till i BorderPane
        this.setLeft(analysisBox);
        this.setCenter(imageBox);
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
