module com.example.watch_it {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.watch_it to javafx.fxml;
    exports com.example.watch_it;
}