package pages;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.Quiz;
import model.Question;

public class PastQuizzes {
    public static void showPastQuizzes(Stage stage) {
        HBox pane = new HBox();
        ArrayList<Quiz> quizzes = DB.loadQuizzes(MainFrame.currentUser);
        VBox[] smallpanes = new VBox[quizzes.size()];
        for(int i = 0; i < quizzes.size(); i++) {
            smallpanes[i] = new VBox();
            Label heading = new Label("Quiz");
            smallpanes[i].getChildren().addAll(heading);
            ArrayList<Question> questions = DB.loadQuestionsfromQuiz(quizzes.get(i).getQuiz_id());
            for(Question q : questions){
                smallpanes[i].getChildren().addAll(new Label(q.getText()), new Label(q.getCorrect_answer()));
            }
            pane.getChildren().addAll(smallpanes[i]);
        }

         // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}
