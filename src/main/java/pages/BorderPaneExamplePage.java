package pages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneExamplePage {

    public static void showBorderPaneExample(Stage stage) {

        BorderPane pane = new BorderPane();

        Label explanation = new Label("GridPane adds children differently -- check that code.");
        ImageView cardImage = new ImageView("file:image/1.png");
        TextField txtField = new TextField("IB Computer Science");
        Button btMainMenu = new Button("Main Menu");

        // A border pane has exactly 5 regions. You can add exactly one widget to each region.
        pane.setTop(explanation);
        pane.setLeft(cardImage);
        pane.setRight(btMainMenu);
        pane.setCenter(txtField);
        pane.setBottom(new Label("Border layout isn't that interesting by itself..."));

        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}