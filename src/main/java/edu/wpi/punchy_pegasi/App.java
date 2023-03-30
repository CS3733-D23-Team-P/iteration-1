package edu.wpi.punchy_pegasi;

import edu.wpi.punchy_pegasi.controllers.LayoutController;
import edu.wpi.punchy_pegasi.navigation.Navigation;
import edu.wpi.punchy_pegasi.navigation.Screen;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class App extends Application {

    @Setter
    @Getter
    private static Stage primaryStage;
    @Setter
    @Getter
    private static BorderPane viewPane;

    @Override
    public void init() {
        log.info("Starting Up");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /* primaryStage is generally only used if one of your components require the stage to display */
        App.primaryStage = primaryStage;

        final FXMLLoader loader = new FXMLLoader(App.class.getResource("views/Root.fxml"));
        final BorderPane root = loader.load();

        final var layoutLoader = new FXMLLoader(App.class.getResource("views/Layout.fxml"));
        final BorderPane loadedLayout = layoutLoader.load();
        final LayoutController layoutController = layoutLoader.getController();

        root.setCenter(loadedLayout);
        App.viewPane = layoutController.getViewPane();

        final Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        Navigation.navigate(Screen.LOGIN);
        MFXThemeManager.addOn(scene, Themes.DEFAULT);
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
