package bca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxExamplePage {

    public static void showHBoxExample(Stage stage) {
        HBox pane = new HBox();

        Label explanation = new Label("Question 1: How do you say <<cat>> in Russian?");
        //ImageView cardImage = new ImageView("file:image/1.png");
        TextField txtField = new TextField("Input answer");
        ComboBox answersComboBox = new ComboBox();
        answersComboBox.getItems().addAll("Da", "Nyet", "Mojet Beet");
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit answer");
        

        // Add multiple widgets at once to the hbox.
        //pane.getChildren().addAll(explanation, cardImage, txtField);
        pane.getChildren().addAll(explanation, answersComboBox);

        // Or just add a single widget to the hbox
        pane.getChildren().addAll(btMainMenu, btSubmit);

        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();


    }

}