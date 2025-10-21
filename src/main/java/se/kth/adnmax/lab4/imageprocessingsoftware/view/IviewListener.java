package se.kth.adnmax.lab4.imageprocessingsoftware.view;

public interface IviewListener {
    void onInvertSelected();
    void onGreyScaleSelected();
    void onBlurSelected();
    void onSharpenSelected();
    void onWindowLevelSelected();
}