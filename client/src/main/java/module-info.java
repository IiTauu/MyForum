module myforum.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.codec.memcache;
    requires java.desktop;


    opens myforum.ui.controller to javafx.fxml;
    exports myforum.ui.controller;
    opens myforum.ui to javafx.fxml;
    exports myforum.ui;
    exports myforum.client;
    exports myforum.ui.utils;
    opens myforum.ui.utils to javafx.fxml;
}