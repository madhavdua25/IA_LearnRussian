package bca;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxExamplePage {

    public static void showHBoxExample(Stage stage) {
        HBox pane = new HBox();

        Label explanation = new Label("An HBox organizes widgets horizontally.");
        ImageView cardImage = new ImageView("file:image/1.png");
        TextField txtField = new TextField("IB Computer Science");
        Button btMainMenu = new Button("Main Menu");

        // Add multiple widgets at once to the hbox.
        pane.getChildren().addAll(explanation, cardImage, txtField);

        // Or just add a single widget to the hbox
        pane.getChildren().add(btMainMenu);

        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}