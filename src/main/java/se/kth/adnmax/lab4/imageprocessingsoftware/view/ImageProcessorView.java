package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;
import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;

public class ImageProcessorView extends BorderPane {
    private HistogramView histogramView;
    private MenuBar menuBar;
    private ImageView imageView;
    private IviewListener viewListener;
    private Slider levelSlider;
    private Slider windowSlider;

    private FileChooser fileChooser;

    private final Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

    public ImageProcessorView() {

        // Meny
        createMenuBar();
        this.setTop(menuBar);

        // Bildvy (Imagebox)
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
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
        windowSlider = new Slider(0, 255, 0);
        levelSlider = new Slider(0, 255, 0);
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
        loadImageItem.setOnAction(e-> {
            viewListener.onLoadImageSelected();
        });
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
        MenuItem windowLevelItem = new MenuItem(("Window/Level"));
        windowLevelItem.setOnAction(e -> {
            if (viewListener != null) viewListener.onWindowLevelSelected();
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
        processMenu.getItems().addAll(greyScaleItem, windowLevelItem, invertItem, blurItem, sharpenItem);
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

    public double getWindow() {
        return windowSlider.getValue();
    }

    public double getLevel() {
        return levelSlider.getValue();
    }

    private void onMenubarExitSelected() {
        System.exit(0); // Check if user has saved file?
    }

    public void showAlertInfo(String message) {
        alertInfo.setWidth(200);
        alertInfo.setHeight(300);
        alertInfo.setTitle("Information");
        alertInfo.setHeaderText("Note!");
        alertInfo.setContentText(message);
        alertInfo.show();
    }

    public void clearHistogram() {
        histogramView.clear();
    }

    public void updateHistogram(int[][] histogramValues) {
        histogramView.updateView(histogramValues);
    }
}
