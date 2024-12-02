package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DB;
import model.Question;

public class UnscoredQuiz {

    private static PriorityQueue<PriorityQuestion> questionQueue;
    private static List<PriorityQuestion> skippedQuestions;
    private static Random random;

    public static void showUnscoredQuiz(Stage stage) {
        VBox bigPane = new VBox();
        bigPane.setSpacing(10);
        bigPane.setStyle("-fx-padding: 20;");

        
        loadQuestions();
        random = new Random();

        Label questionText = new Label();
        VBox answersPane = new VBox();
        answersPane.setSpacing(10);

        Button nextQuestionButton = new Button("Next Question");
        nextQuestionButton.setDisable(true); 

        loadNextQuestion(questionText, answersPane, nextQuestionButton);

        nextQuestionButton.setOnAction(e -> {
            if (!questionQueue.isEmpty() || !skippedQuestions.isEmpty()) {
                loadNextQuestion(questionText, answersPane, nextQuestionButton);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "You've completed all questions!");
                alert.showAndWait();
                MainFrame.loadMenu(stage);
            }
        });

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> MainFrame.loadMenu(stage));

        // add components to the root pane
        bigPane.getChildren().addAll(questionText, answersPane, nextQuestionButton, mainMenuButton);

        // set up the scene
        Scene scene = new Scene(bigPane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private static void loadQuestions() {
        ArrayList<Question> questions = DB.loadQuestions();
        questionQueue = new PriorityQueue<>();
        skippedQuestions = new ArrayList<>();

        for (Question q : questions) {
            questionQueue.add(new PriorityQuestion(q, 1)); // Default priority
        }
    }

    private static void loadNextQuestion(Label questionText, VBox answersPane, Button nextQuestionButton) {
        // clear previous answers
        answersPane.getChildren().clear();
        nextQuestionButton.setDisable(true);

        // get the next question-- either from the queue or the skipped list
        PriorityQuestion currentQuestion;
        if (!questionQueue.isEmpty() && (random.nextBoolean() || skippedQuestions.isEmpty())) {
            currentQuestion = questionQueue.poll();
        } else {
            currentQuestion = skippedQuestions.remove(random.nextInt(skippedQuestions.size()));
        }

        Question question = currentQuestion.getQuestion();

        questionText.setText("Question: " + question.getText());

        // shuffle and display answer options
        List<String> options = new ArrayList<>();
        options.add(question.getCorrect_answer());
        options.add(question.getIncorrect1());
        options.add(question.getIncorrect2());
        options.add(question.getIncorrect3());
        Collections.shuffle(options);

        for (String option : options) {
            Button answerButton = new Button(option);
            answerButton.setOnAction(e -> {
                // provide feedback
                boolean isCorrect = option.equals(question.getCorrect_answer());
                Alert feedback = new Alert(isCorrect ? AlertType.INFORMATION : AlertType.ERROR,
                        isCorrect ? "Correct!" : "Incorrect. The correct answer is: " + question.getCorrect_answer());
                feedback.showAndWait();

                // the crux of the app-- adjust priority for the question based on the result
                if (isCorrect) {
                    currentQuestion.setPriority(currentQuestion.getPriority() + 10); // decrease priority
                    questionQueue.add(currentQuestion);
                } else {
                    currentQuestion.setPriority(currentQuestion.getPriority() - 1); // increase priority
                    skippedQuestions.add(currentQuestion); // delay reinsertion
                }

                nextQuestionButton.setDisable(false);

                
                answersPane.getChildren().forEach(node -> node.setDisable(true));
            });
            answersPane.getChildren().add(answerButton);
        }
    }
}
