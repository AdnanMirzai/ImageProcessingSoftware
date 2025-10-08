module se.kth.adnmax.lab4.imageprocessingsoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens se.kth.adnmax.lab4.imageprocessingsoftware to javafx.fxml;
    exports se.kth.adnmax.lab4.imageprocessingsoftware;
}