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

        GridPane pane = new GridPane();
        ArrayList<Quiz> list = DB.loadQuizzes(MainFrame.currentUser);

        Button btProgressReport = new Button("View Progress Report");
        Button btPastQuizzes = new Button("View past quizzes");
        

        if(list.size() > 0){
            Quiz q = list.get(list.size()-1);
            Label recentQuiz = new Label(q.toString());
            pane.add(recentQuiz, 0,0);
            //pane.add(btPastQuizzes, 0, 1);
            pane.add(btProgressReport, 0, 2);
        } else {
            Label noQuiz = new Label("No scores to report yet - Go practice!");
            pane.add(noQuiz, 0, 0);
        }
        
        Button btMainMenu = new Button("Main Menu");
        
        pane.add(btMainMenu, 0,3);

        
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));
        btProgressReport.setOnAction(e -> ProgressReportPage.showProgressReport(stage));
        btPastQuizzes.setOnAction(e -> PastQuizzes.showPastQuizzes(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}