package pages;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DB;
import model.Question;

public class UnscoredQuiz {

    public static void showVBoxExample(Stage stage) {

        HBox pane = new HBox();
        ArrayList<Question> questions = DB.loadQuestions();
        Collections.shuffle(questions);
        Question q = questions.get(0);

        Label explanation = new Label("Unscored Question: " + q.getText());
        
        TextField txtField = new TextField("Input answer");
        ComboBox answersComboBox = new ComboBox();
        answersComboBox.getItems().addAll(q.getCorrect_answer(), q.getIncorrect1(), q.getIncorrect2(), q.getIncorrect3());
        TextField inputAnswer = new TextField();
        inputAnswer.setPromptText("Type answer exactly");
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit answer");
        Button btCorrect = new Button("Correct-new question?");

        
        pane.getChildren().addAll(explanation, answersComboBox, inputAnswer, btSubmit, btMainMenu);

        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));
        btSubmit.setOnAction(e -> {
            String answer = inputAnswer.getText();
            if(answer.equals(q.getCorrect_answer())){
                pane.getChildren().addAll(btCorrect);
            } else {
                inputAnswer.clear();
                inputAnswer.setPromptText("Incorrect-try again");
            }
        });
        btCorrect.setOnAction(e -> showVBoxExample(stage));

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

}