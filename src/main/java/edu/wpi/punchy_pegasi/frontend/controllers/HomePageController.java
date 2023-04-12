package edu.wpi.punchy_pegasi.frontend.controllers;

import edu.wpi.punchy_pegasi.App;
import edu.wpi.punchy_pegasi.frontend.Screen;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePageController {
    @FXML
    private VBox serviceRequests;
    @FXML
    private VBox otherPages;

    @FXML
    public void initialize() {
        ObservableList<HBox> otherPageButtons = FXCollections.observableArrayList();
        Bindings.bindContent(otherPages.getChildren(), otherPageButtons);

        for (var entry: Screen.values()) {
            if (entry.name().toLowerCase().contains("request")
                    || entry.name().toLowerCase().contains("root")
                    || entry.name().toLowerCase().contains("home")
                    || entry.name().toLowerCase().contains("login"))
                continue;
            LinkControl linkControl = new LinkControl(entry.getReadable(), () -> {
                App.getSingleton().navigate(entry);
            });
            otherPageButtons.add(linkControl);
        }
    }


}