package se.kth.adnmax.lab4.imageprocessingsoftware.view;

import javafx.stage.Stage;

public interface IviewListener {
    void onInvertSelected();
    void onGreyScaleSelected();
    void onBlurSelected();
    void onSharpenSelected();
    void onWindowLevelSelected();
    void onResetSelected();
    void onLoadImageSelected();
    void onSaveImageSelected();
}