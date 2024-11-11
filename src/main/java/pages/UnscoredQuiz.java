package pages;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.Question;

public class UnscoredQuiz {

    public static void showVBoxExample(Stage stage) {

        HBox pane = new HBox();
        ArrayList<Question> questions = DB.loadQuestions();
        Question q2 = questions.get(1);

        Label explanation = new Label("Unscored Question: " + q2.getText());
        //ImageView cardImage = new ImageView("file:image/1.png");
        TextField txtField = new TextField("Input answer");
        ComboBox answersComboBox = new ComboBox();
        answersComboBox.getItems().addAll(q2.getCorrect_answer(), q2.getIncorrect1(), q2.getIncorrect2(), q2.getIncorrect3());
        Button btMainMenu = new Button("Main Menu");
        Button btSubmit = new Button("Submit answer");

        //txtField.setMaxWidth(200);

        // Add multiple widgets at once to the hbox.
        //pane.getChildren().addAll(explanation, cardImage, txtField);
        //pane.getChildren().addAll(explanation, txtField);
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