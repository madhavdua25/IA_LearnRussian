package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.Question;

public class ScoredQuiz {

    public static void showHBoxExample(Stage stage) {
        VBox bigpane = new VBox();

        //randomly select ten questions from the database
        ArrayList<Question> questions = DB.loadQuestions();
        Collections.shuffle(questions);
        List<Question> quizQuestions = questions.subList(0, Math.min(10, questions.size()));

        //create a "smallpane" for each question and add to the "bigpane"
        for(int i = 0; i < quizQuestions.size(); i++){
            HBox smallpane = new HBox();
            Question q = quizQuestions.get(i);
            Label text = new Label("Question " + i + ": " + q.getText());
            ComboBox answersComboBox = new ComboBox();
            answersComboBox.getItems().addAll(q.getCorrect_answer(), q.getIncorrect1(), q.getIncorrect2(), q.getIncorrect3());
            smallpane.getChildren().addAll(text, answersComboBox);
            bigpane.getChildren().addAll(smallpane);
        }
        
        //buttons
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit quiz");
        bigpane.getChildren().addAll(btSubmit, btMainMenu);
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        //set "bigpane" as the root
        Scene scene = new Scene(bigpane);

        //display
        stage.setScene(scene);
        stage.show();


    }

}