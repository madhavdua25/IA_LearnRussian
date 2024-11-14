package pages;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DB;
import model.Quiz;

public class ScoreReport {

    public static void showGridExample(Stage stage) {

        ArrayList<Quiz> list = DB.loadQuizzes(1);
        Quiz q = list.get(list.size()-1);

        GridPane pane = new GridPane();

        Label recentQuiz = new Label(q.toString());
        
        Button btMainMenu = new Button("Main Menu");
        Button btProgressReport = new Button("View Progress Report");

        
        pane.add(recentQuiz, 0,0);
        pane.add(btProgressReport, 0, 2);
        
        pane.add(btMainMenu, 0,3);

        
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));
        btProgressReport.setOnAction(e -> ProgressReportPage.showProgressReport(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}