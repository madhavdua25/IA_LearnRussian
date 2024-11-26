package pages;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.Quiz;

public class PastQuizzes {
    public static void showProgressReport(Stage stage) {
        VBox pane = new VBox();
        ArrayList<Quiz> quizzes = DB.loadQuizzes(MainFrame.currentUser);
        HBox[] smallpanes = new HBox[quizzes.size()];
        for(Quiz q : quizzes) {
            
        }



        stage.show();
    }

}
