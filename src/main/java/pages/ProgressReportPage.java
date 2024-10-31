package pages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressReportPage {
    public static void showProgressReport(Stage stage) {
        HBox pane = new HBox();

        Label explanation = new Label("Below is a graph of your scores");
        Button btBack = new Button("Back to scores");
        ImageView graph = new ImageView("file:image/graph.jpeg");
        //ImageView cardImage = new ImageView("file:image/1.png");
        //TextField txtField = new TextField("Input username:");
        // Button btUserA = new Button("User A");
        // Button btUserB = new Button("User B");
        // Button btUserC = new Button("User C");

        // Add multiple widgets at once to the hbox.
        //pane.getChildren().addAll(explanation, cardImage, txtField);
        pane.getChildren().addAll(explanation, btBack, graph);

        btBack.setOnAction(e -> GridExamplePage.showGridExample(stage));
        // Or just add a single widget to the hbox
        //pane.getChildren().addAll(btUserA, btUserB, btUserC);

        // Set what happens when the button is pressed.
        //btUserA.setOnAction(e -> MainFrame.loadMenu(stage));
        //btUserB.setOnAction(e -> MainFrame.loadMenu(stage));
        //btUserC.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show(); 
    }
}
