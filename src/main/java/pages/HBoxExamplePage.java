package pages;

import java.util.ArrayList;

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
import model.DB;
import model.Question;

public class HBoxExamplePage {

    public static void showHBoxExample(Stage stage) {
        HBox pane = new HBox();

        ArrayList<Question> questions = DB.loadQuestions();
        Question q1 = questions.get(0);

        // int i = (int) ((Math.random()*4)+1);
        // Question q1 = DB.loadQuestion(i);


        Label explanation = new Label("Question " + q1.getQuestion_id() + ": " + q1.getText());
        TextField txtField = new TextField("Input answer");
        ComboBox answersComboBox = new ComboBox();
        answersComboBox.getItems().addAll(q1.getCorrect_answer(), q1.getIncorrect1(), q1.getIncorrect2(), q1.getIncorrect3());
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit answer");
        

        // Add multiple widgets at once to the hbox.
        //pane.getChildren().addAll(explanation, cardImage, txtField);
        pane.getChildren().addAll(explanation, answersComboBox);

        // Or just add a single widget to the hbox
        pane.getChildren().addAll(btSubmit, btMainMenu);

        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();


    }

}