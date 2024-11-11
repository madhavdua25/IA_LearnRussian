package pages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScoreReport {

    public static void showGridExample(Stage stage) {

        GridPane pane = new GridPane();

        Label explanation = new Label("Recent quiz score: x/10");
        Label next_thing = new Label("Recent quiz date: MM/DD/YY");
        //Label after_that = new Label("View progress chart");
        //ImageView cardImage = new ImageView("file:image/1.png");
        //TextField txtField = new TextField("IB Computer Science");
        Button btMainMenu = new Button("Main Menu");
        Button btProgressReport = new Button("View Progress Report");

        // For a grid pane, you need to give the x,y position of the widget.
        pane.add(explanation, 0,0);
        pane.add(next_thing, 0, 1);
        pane.add(btProgressReport, 0, 2);
        //pane.add(cardImage, 1,0);
        //pane.add(txtField, 1,1);
        pane.add(btMainMenu, 0,3);

        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));
        btProgressReport.setOnAction(e -> ProgressReportPage.showProgressReport(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}