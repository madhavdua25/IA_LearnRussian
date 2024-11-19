package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        Alert incorrect = new Alert(AlertType.INFORMATION, "Incorrect! Try again", ButtonType.CLOSE);


        Label explanation = new Label("Unscored Question: " + q.getText());
        
        ComboBox answersComboBox = new ComboBox();
        List<String> a = Arrays.asList(q.getCorrect_answer(), q.getIncorrect1(), q.getIncorrect2(), q.getIncorrect3());
        Collections.shuffle(a);
        answersComboBox.getItems().addAll(a);
        //TextField inputAnswer = new TextField();
        //inputAnswer.setPromptText("Type answer exactly");
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit answer");
        Button btCorrect = new Button("Correct-new question?");
        //Label incorrect = new Label("Incorrect-try again");

        
        pane.getChildren().addAll(explanation, answersComboBox, btSubmit, btMainMenu);

        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));
        btSubmit.setOnAction(e -> {
            String answer = (String) answersComboBox.getValue();
            if(answer.equals(q.getCorrect_answer())){
                answersComboBox.setEditable(false);
                pane.getChildren().addAll(btCorrect);
            } else {
                incorrect.show();
            }
        });

        btCorrect.setOnAction(e -> showVBoxExample(stage));

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

}