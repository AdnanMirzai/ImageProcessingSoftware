module se.kth.adnmax.lab.imageprocessingsoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    //requires se.kth.adnmax.lab4.imageprocessingsoftware;


    opens se.kth.adnmax.lab4.imageprocessingsoftware to javafx.fxml;
    exports se.kth.adnmax.lab4.imageprocessingsoftware;
}